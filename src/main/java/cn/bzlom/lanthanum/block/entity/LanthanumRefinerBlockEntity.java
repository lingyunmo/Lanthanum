package cn.bzlom.lanthanum.block.entity;

import cn.bzlom.lanthanum.recipe.LanthanumRefinerRecipe;
import cn.bzlom.lanthanum.screen.LanthanumRefinerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LanthanumRefinerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

//    public ItemStack getRendererStack(){
//        if(this.getStack(2).isEmpty()){
//            return this.getStack(1);
//        }else{
//            return this.getStack(2);
//        }
//    }
//
//    public void setInventory(DefaultedList<ItemStack> inventory){
//        for(int i=0;i<inventory.size();i++){
//            this.inventory.set(i,inventory.get(i));
//        }
//    }
//
//    @Override
//    public void markDirty() {
//        if(!world.isClient){
//            PacketByteBuf data = PacketByteBufs.create();
//            data.writeInt(inventory.size());
//            for(int i=0;i<inventory.size();i++){
//                data.writeItemStack(inventory.get(i));
//            }
//            data.writeBlockPos(getPos());
//            for(ServerPlayerEntity  serverPlayerEntity : PlayerLookup.tracking((ServerWorld) world,getPos())){
//                ServerPlayNetworking.send(serverPlayerEntity,ModMessage.ITEM_SYNC,data);
//            }
//        }
//        super.markDirty();
//    }
//
//    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(30000,32,32){
//        @Override
//        protected void onFinalCommit() {
//            markDirty();
//            sendEnergyPacket();
//        }
//    };
//    // 能量发包
//    private void sendEnergyPacket(){
//        PacketByteBuf data = PacketByteBufs.create();
//        data.writeLong(energyStorage.amount);
//        data.writeBlockPos(getPos());
//
//        for(ServerPlayerEntity player:PlayerLookup.tracking((ServerWorld) world,getPos())){
//            ServerPlayNetworking.send(player,ModMessage.ENERGY_SYNC,data);
//        }
//    }
//    // 流体存储的tank
//    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<FluidVariant>() {
//        // 流体的类型
//        @Override
//        protected FluidVariant getBlankVariant() {
//            return FluidVariant.blank();
//        }
//        // 容量
//        @Override
//        protected long getCapacity(FluidVariant variant) {
//            // 存储20捅
//            return FluidStack.convertDropletsToMb(FluidConstants.BUCKET) * 20;
//        }
//        // 当存储的内容改变的时候发包
//        @Override
//        protected void onFinalCommit() {
//            markDirty();
//            if(!world.isClient){
//                sendFluidPacket();
//            }
//        }
//    };
//    // 发送流体包
//    private void sendFluidPacket(){
//        PacketByteBuf data = PacketByteBufs.create();
//        fluidStorage.variant.toPacket(data);
//        data.writeLong(fluidStorage.amount);
//        data.writeBlockPos(getPos());
//
//        for(ServerPlayerEntity player:PlayerLookup.tracking((ServerWorld) world,getPos())){
//            ServerPlayNetworking.send(player,ModMessage.FLUID_SYNC,data);
//        }
//    }

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 70;

    public LanthanumRefinerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LANTHANUM_REFINER_BLOCK, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0:
                        return LanthanumRefinerBlockEntity.this.progress;
                    case 1:
                        return LanthanumRefinerBlockEntity.this.maxProgress;
                    default:
                        return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        LanthanumRefinerBlockEntity.this.progress = value;
                        break;
                    case 1:
                        LanthanumRefinerBlockEntity.this.maxProgress = value;
                        break;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    //    public void setEnergyStorage(long energyLevel){
//        this.energyStorage.amount = energyLevel;
//    }
//    // 设置流体
//    public void setFluidLevel(FluidVariant fluidVariant,long fluidLevel){
//        this.fluidStorage.variant = fluidVariant;
//        this.fluidStorage.amount = fluidLevel;
//    }
//
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Lanthanum Refiner");
    }

    @Nullable
    // 打开menu时候和client 数据同步
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
//        sendEnergyPacket();
//        sendFluidPacket();
        return new LanthanumRefinerScreenHandler(syncId, playerInventory, this, this, this.propertyDelegate);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("lanthanum_refiner_block.progress");
//        energyStorage.amount = nbt.getLong("gem_infusing_station.energy");
//        fluidStorage.variant = FluidVariant.fromNbt((NbtCompound) nbt.get("gem_infusing_station.variant"));
//        fluidStorage.amount = nbt.getLong("gem_infusing_station.fluid");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("lanthanum_refiner_block.progress", progress);
//        nbt.putLong("gem_infusing_station.energy", energyStorage.amount);
//        nbt.put("gem_infusing_station.variant", fluidStorage.variant.toNbt());
//        nbt.putLong("gem_infusing_station.fluid", fluidStorage.amount);
    }

    private void resetProgress() {
        this.progress = 0;
    }

//    @Override
//    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
//        Direction localDir = this.world.getBlockState(pos).get(GemInfusingStationBlock.FACING);
//
//        if(side == Direction.UP || side == Direction.DOWN) {
//            return false;
//        }
//
//        // top 1
//        // right 1
//        // left 0
//        return switch (localDir){
//            default ->
//                    side.getOpposite() == Direction.NORTH && slot == 1||
//                            side.getOpposite() == Direction.EAST && slot == 1 ||
//                            side.getOpposite() == Direction.WEST && slot == 0;
//
//            case EAST ->
//                    side.rotateYClockwise() == Direction.NORTH && slot == 1||
//                            side.rotateYClockwise() == Direction.EAST && slot == 1 ||
//                            side.rotateYClockwise() == Direction.WEST && slot == 0;
//
//            case SOUTH ->
//                    side == Direction.NORTH && slot == 1||
//                            side == Direction.EAST && slot == 1 ||
//                            side == Direction.WEST && slot == 0;
//            case WEST ->
//                    side.rotateYCounterclockwise() == Direction.NORTH && slot == 1||
//                            side.rotateYCounterclockwise() == Direction.EAST && slot == 1||
//                            side.rotateYCounterclockwise() == Direction.WEST && slot == 1;
//        };
//    }
//
//    @Override
//    public boolean canExtract(int slot, ItemStack stack, @Nullable Direction side) {
//        Direction localDir = this.world.getBlockState(this.pos).get(GemInfusingStationBlock.FACING);
//
//        if(side == Direction.UP){
//            return false;
//        }
//
//        if (side == Direction.DOWN){
//            return slot == 2;
//        }
//
//        // bottom extract 2
//        // right extract 2
//        return switch (localDir){
//            default -> side.getOpposite() == Direction.SOUTH && slot == 2||
//                    side.getOpposite() ==Direction.EAST && slot == 2;
//            case EAST -> side.rotateYClockwise() == Direction.SOUTH && slot == 2||
//                    side.rotateYClockwise() == Direction.EAST && slot==2;
//            case SOUTH -> side == Direction.SOUTH && slot ==2 ||
//                    side == Direction.EAST && slot ==2;
//            case WEST -> side.rotateYCounterclockwise() == Direction.SOUTH || slot ==2 ||
//                    side.rotateYCounterclockwise() == Direction.EAST && slot ==2 ;
//        };
//    }

    public static void tick(World world, BlockPos pos, BlockState state, LanthanumRefinerBlockEntity entity) {
        if (world.isClient) {
            return;
        }

        if (hasRecipe(entity)) {
            //zh_cn:以下为无能量写法
            entity.progress++;
            markDirty(world, pos, state);
            System.out.println(entity.progress);
            if (entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, pos, state);

//            try (Transaction transaction = Transaction.openOuter()) {
//                entity.energyStorage.insert(16, transaction);
//                transaction.commit();
//            }
        }
//
//        if (hasRecipe(entity) && hasEnoughEnergy(entity) && hasEnoughFluid(entity)) {
//            entity.progress++;
//            extractEnergy(entity);
//            markDirty(world, pos, state);
//            if (entity.progress >= entity.maxProgress) {
//                craftItem(entity);
//                extractFluid(entity);
//            }
//        } else {
//            entity.resetProgress();
//            markDirty(world, pos, state);
//        }
//
//        if (hasFluidSourceInSlot(entity)) {
//            transferFluidToFluidStorage(entity);
//        }
    }

//    private static void extractFluid(LanthanumRefinerBlockEntity entity) {
//        try (Transaction transaction = Transaction.openOuter()) {
//            entity.fluidStorage.extract(FluidVariant.of(ModFluids.STILL_SOAP_WATER),
//                    500, transaction);
//            transaction.commit();
//        }
//    }
//
//    private static void transferFluidToFluidStorage(LanthanumRefinerBlockEntity entity) {
//        try (Transaction transaction = Transaction.openOuter()) {
//            entity.fluidStorage.insert(FluidVariant.of(ModFluids.STILL_SOAP_WATER),
//                    FluidStack.convertDropletsToMb(FluidConstants.BUCKET), transaction);
//            transaction.commit();
//            entity.setStack(0, new ItemStack(Items.BUCKET));
//        }
//    }

//    private static boolean hasFluidSourceInSlot(LanthanumRefinerBlockEntity entity) {
//        return entity.getStack(0).getItem() == ModFluids.SOAP_WATER_BUCKET;
//    }
//
//    private static boolean hasEnoughFluid(LanthanumRefinerBlockEntity entity) {
//        return entity.fluidStorage.amount >= 500;
//    }
//
//
//    private static void extractEnergy(LanthanumRefinerBlockEntity entity) {
//        try (Transaction transaction = Transaction.openOuter()) {
//            entity.energyStorage.extract(32, transaction);
//            transaction.commit();
//        }
//    }
//
//    private static boolean hasEnoughEnergy(LanthanumRefinerBlockEntity entity) {
//        return entity.energyStorage.amount >= 32 * (entity.maxProgress - entity.progress);
//    }
//
//    private static boolean hasEnergyItem(LanthanumRefinerBlockEntity entity) {
//        return entity.getStack(0).getItem() == ModItems.RUBY;
//    }

    private static boolean hasRecipe(LanthanumRefinerBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<LanthanumRefinerRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(LanthanumRefinerRecipe.Type.INSTANCE, inventory, entity.getWorld());

        return match.isPresent() && canInsertAmountIntOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput(entity.world.getRegistryManager())
                .getItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(2).getItem() == output || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }

    private static void craftItem(LanthanumRefinerBlockEntity entity) {
        SimpleInventory inventory1 = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory1.setStack(i, entity.getStack(i));
        }

        if (hasRecipe(entity)) {
            Optional<LanthanumRefinerRecipe> match = entity.getWorld().getRecipeManager()
                    .getFirstMatch(LanthanumRefinerRecipe.Type.INSTANCE, inventory1, entity.getWorld());

            entity.removeStack(1, 1);

            entity.setStack(2, new ItemStack(match.get().getOutput(entity.world.getRegistryManager()).getItem(),
                    entity.getStack(2).getCount() + 2));
            entity.resetProgress();
        }
    }
}
