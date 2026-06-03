package cn.bzlom.lanthanum.screen;

import cn.bzlom.lanthanum.item.UpgradeCard;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;

public class CrusherScreenHandler extends AbstractContainerMenu {

    public static final int SLOT_INPUT = 0;
    public static final int SLOT_UPGRADE = 1;
    public static final int SLOT_OUTPUT = 2;

    private final Container inventory;
    private final ContainerData data;
    public final BlockEntity blockEntity;

    // Client constructor (called from MenuType)
    public CrusherScreenHandler(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(3), new SimpleContainerData(4));
    }

    // Server constructor
    public CrusherScreenHandler(int containerId, Inventory playerInventory, BlockEntity entity, ContainerData data) {
        this(containerId, playerInventory,
                entity instanceof Container c ? c : new SimpleContainer(3), data);
    }

    private CrusherScreenHandler(int containerId, Inventory playerInventory, Container inventory, ContainerData data) {
        super(ModScreenHandlers.CRUSHER_SCREEN_HANDLER.get(), containerId);
        this.inventory = inventory;
        this.data = data;
        this.blockEntity = inventory instanceof BlockEntity be ? be : null;

        checkContainerSize(inventory, 3);
        inventory.startOpen(playerInventory.player);

        // Machine slots — aligned with arrow center y=43
        this.addSlot(new Slot(inventory, SLOT_INPUT, 44, 24));         // Input
        this.addSlot(new UpgradeSlot(inventory, SLOT_UPGRADE, 44, 44)); // Upgrade
        this.addSlot(new OutputSlot(inventory, SLOT_OUTPUT, 116, 34));  // Output

        // Player inventory (3 rows × 9)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9,
                        8 + col * 18, 84 + row * 18));
            }
        }

        // Player hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }

        addDataSlots(data);
    }

    public int getEnergy() {
        return data.get(2);
    }

    public int getMaxEnergy() {
        return data.get(3);
    }

    public int getScaledEnergy(int height) {
        int energy = getEnergy();
        int maxEnergy = getMaxEnergy();
        if (maxEnergy == 0) return 0;
        return (int) ((long) energy * height / maxEnergy);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress(int width) {
        int progress = data.get(0);
        int maxProgress = data.get(1);
        if (maxProgress == 0) return 0;
        return progress * width / maxProgress;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();

            if (invSlot < inventory.getContainerSize()) {
                // From machine to player inventory
                if (!this.moveItemStackTo(originalStack, inventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack, 0, SLOT_OUTPUT, false)) {
                // From player to machine (only to input/upgrade, not output)
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return inventory.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        inventory.stopOpen(player);
    }

    public Container getInventory() {
        return inventory;
    }

    // ── Custom slots ────────────────────────────────────

    /** Slot that only accepts upgrade cards and redstone */
    private static class UpgradeSlot extends Slot {
        public UpgradeSlot(Container container, int slot, int x, int y) {
            super(container, slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return stack.getItem() instanceof UpgradeCard
                    || stack.getItem() == Items.REDSTONE
                    || stack.getItem() == Items.REDSTONE_BLOCK
                    || stack.getItem() == Items.REDSTONE_TORCH;
        }

        @Override
        public int getMaxStackSize() {
            return getItem().getItem() instanceof UpgradeCard ? 1 : 64;
        }
    }

    /** Output slot — cannot insert items */
    private static class OutputSlot extends Slot {
        public OutputSlot(Container container, int slot, int x, int y) {
            super(container, slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}
