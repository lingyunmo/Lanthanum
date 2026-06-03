package cn.bzlom.lanthanum.block.entity;

import cn.bzlom.lanthanum.item.UpgradeCard;
import cn.bzlom.lanthanum.recipe.MachineRecipe;
import cn.bzlom.lanthanum.recipe.ModRecipes;
import cn.bzlom.lanthanum.screen.CrusherScreenHandler;
import earth.terrarium.botarium.common.energy.impl.SimpleEnergyContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Shared base for all processing machines.
 * Subclasses only need to define machine-specific constants:
 * energy capacity, process time, energy per tick, and display name.
 */
public abstract class BaseMachineBlockEntity extends BlockEntity
        implements MenuProvider, ImplementedInventory {

    public static final int SLOT_INPUT = 0;
    public static final int SLOT_UPGRADE = 1;
    public static final int SLOT_OUTPUT = 2;
    public static final int INVENTORY_SIZE = 3;

    private final NonNullList<ItemStack> items = NonNullList.withSize(INVENTORY_SIZE, ItemStack.EMPTY);
    public final SimpleEnergyContainer energyStorage;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress;

    // ── Subclass-provided constants ─────────────────────
    protected abstract long getEnergyCapacity();
    protected abstract long getEnergyMaxIO();
    protected abstract int getBaseProcessTime();
    protected abstract long getBaseEnergyPerTick();

    protected BaseMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.energyStorage = new SimpleEnergyContainer(getEnergyCapacity(), getEnergyMaxIO(), getEnergyMaxIO());
        this.maxProgress = getBaseProcessTime();

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    case 2 -> (int) energyStorage.getStoredEnergy();
                    case 3 -> (int) energyStorage.getMaxCapacity();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> progress = value;
                    case 1 -> maxProgress = value;
                    case 2 -> energyStorage.setEnergy(value);
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    // ── Inventory ──────────────────────────────────────
    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    private static boolean isFuelItem(ItemStack stack) {
        return stack.getItem() == Items.REDSTONE
                || stack.getItem() == Items.REDSTONE_BLOCK
                || stack.getItem() == Items.REDSTONE_TORCH;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return switch (slot) {
            case SLOT_INPUT -> true;
            case SLOT_UPGRADE -> stack.getItem() instanceof UpgradeCard || isFuelItem(stack);
            case SLOT_OUTPUT -> false;
            default -> false;
        };
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction side) {
        // Only allow input slot insertion from any face; upgrade slot is manual-only
        return slot == SLOT_INPUT && canPlaceItem(slot, stack);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        // Bottom = output extraction only, sides = input, top = input
        return switch (side) {
            case DOWN -> new int[]{SLOT_OUTPUT};
            default -> new int[]{SLOT_INPUT};
        };
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
        return slot == SLOT_OUTPUT && side == Direction.DOWN;
    }

    // ── Menu ───────────────────────────────────────────
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new CrusherScreenHandler(containerId, playerInventory, this, this.data);
    }

    // ── NBT ────────────────────────────────────────────
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ContainerHelper.loadAllItems(tag, items);
        progress = tag.getInt("Progress");
        maxProgress = tag.getInt("MaxProgress");
        if (maxProgress <= 0) maxProgress = getBaseProcessTime();
        energyStorage.setEnergy(tag.getLong("Energy"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, items);
        tag.putInt("Progress", progress);
        tag.putInt("MaxProgress", maxProgress);
        tag.putLong("Energy", energyStorage.getStoredEnergy());
    }

    // ── Client sync ────────────────────────────────────
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    // ── Upgrade bonuses ────────────────────────────────
    private float getSpeedMultiplier() {
        return UpgradeCard.getSpeedBonus(items.get(SLOT_UPGRADE));
    }

    private float getEfficiencyMultiplier() {
        return UpgradeCard.getEfficiencyBonus(items.get(SLOT_UPGRADE));
    }

    private float getOutputBonusChance() {
        return UpgradeCard.getOutputBonus(items.get(SLOT_UPGRADE));
    }

    private long getEffectiveEnergyPerTick() {
        return (long) (getBaseEnergyPerTick() * getEfficiencyMultiplier());
    }

    private int getEffectiveMaxProgress() {
        return (int) (getBaseProcessTime() * getSpeedMultiplier());
    }

    // ── Tick ───────────────────────────────────────────
    public static void tick(Level level, BlockPos pos, BlockState state, BaseMachineBlockEntity entity) {
        if (level.isClientSide()) return;

        entity.maxProgress = entity.getEffectiveMaxProgress();

        Optional<MachineRecipe> recipe = getCurrentRecipe(entity);
        boolean hasEnergy = entity.energyStorage.getStoredEnergy() >= entity.getEffectiveEnergyPerTick();
        boolean canFitOutput = recipe.isPresent() && canFitOutputInSlot(entity, recipe.get());
        boolean canProcess = recipe.isPresent() && hasEnergy && canFitOutput;

        // Fuel: only consume if it won't overflow, and a recipe exists
        ItemStack fuelStack = entity.items.get(SLOT_UPGRADE);
        if (isFuelItem(fuelStack) && recipe.isPresent()) {
            long fuelValue = 1600;
            if (fuelStack.getItem() == Items.REDSTONE_BLOCK) fuelValue = 14400;
            else if (fuelStack.getItem() == Items.REDSTONE_TORCH) fuelValue = 3200;
            long current = entity.energyStorage.getStoredEnergy();
            long capacity = entity.energyStorage.getMaxCapacity();
            if (current + fuelValue <= capacity) {
                entity.energyStorage.setEnergy(current + fuelValue);
                fuelStack.shrink(1);
            }
        }

        if (canProcess) {
            entity.progress++;
            long currentEnergy = entity.energyStorage.getStoredEnergy();
            entity.energyStorage.setEnergy(Math.max(0, currentEnergy - entity.getEffectiveEnergyPerTick()));
            setChanged(level, pos, state);

            if (entity.progress >= entity.maxProgress) {
                craftItem(entity, recipe.get());
            }
        } else if (!recipe.isPresent() || !canFitOutput) {
            // Only reset progress when there's no recipe or output is full.
            // Energy shortage just pauses progress — doesn't reset it.
            entity.resetProgress();
            setChanged(level, pos, state);
        }
        // else: energy shortage while recipe exists → pause, don't reset
    }

    private static Optional<MachineRecipe> getCurrentRecipe(BaseMachineBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.items.size());
        for (int i = 0; i < entity.items.size(); i++) {
            inventory.setItem(i, entity.items.get(i));
        }
        if (entity.level == null) return Optional.empty();
        return entity.level.getRecipeManager()
                .getRecipeFor(ModRecipes.MACHINE_RECIPE_TYPE.get(), inventory, entity.level);
    }

    /**
     * Check whether the output slot can accept the recipe result,
     * accounting for the potential output bonus (+1).
     * Prevents silently losing items or exceeding max stack size.
     */
    private static boolean canFitOutputInSlot(BaseMachineBlockEntity entity, MachineRecipe recipe) {
        ItemStack outputSlot = entity.items.get(SLOT_OUTPUT);
        if (outputSlot.isEmpty()) return true;

        ItemStack result = recipe.getResultItem(entity.level != null ? entity.level.registryAccess() : null);
        int maxOutput = result.getCount() + 1; // +1 for potential output bonus
        return outputSlot.getItem() == result.getItem()
                && outputSlot.getCount() + maxOutput <= outputSlot.getMaxStackSize();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(BaseMachineBlockEntity entity, MachineRecipe recipe) {
        entity.items.get(SLOT_INPUT).shrink(1);

        ItemStack result = recipe.getResultItem(
                entity.level != null ? entity.level.registryAccess() : null);
        ItemStack outputSlot = entity.items.get(SLOT_OUTPUT);

        int outputCount = result.getCount();
        float bonusChance = entity.getOutputBonusChance();
        if (bonusChance > 0 && entity.level != null
                && entity.level.random.nextFloat() < bonusChance) {
            outputCount++;
        }

        if (outputSlot.isEmpty()) {
            ItemStack newOutput = result.copy();
            newOutput.setCount(Math.min(outputCount, newOutput.getMaxStackSize()));
            entity.items.set(SLOT_OUTPUT, newOutput);
        } else {
            outputSlot.grow(outputCount);
        }

        entity.resetProgress();
    }
}
