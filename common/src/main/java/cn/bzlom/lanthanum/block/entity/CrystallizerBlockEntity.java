package cn.bzlom.lanthanum.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CrystallizerBlockEntity extends BaseMachineBlockEntity {

    private static final long ENERGY_CAPACITY = 40000;
    private static final long ENERGY_MAX_IO = 64;
    private static final int BASE_PROCESS_TIME = 300;    // 15s
    private static final long BASE_ENERGY_PER_TICK = 64;

    public CrystallizerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRYSTALLIZER.get(), pos, state);
    }

    @Override
    protected long getEnergyCapacity() { return ENERGY_CAPACITY; }

    @Override
    protected long getEnergyMaxIO() { return ENERGY_MAX_IO; }

    @Override
    protected int getBaseProcessTime() { return BASE_PROCESS_TIME; }

    @Override
    protected long getBaseEnergyPerTick() { return BASE_ENERGY_PER_TICK; }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.lanthanum.crystallizer");
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CrystallizerBlockEntity entity) {
        BaseMachineBlockEntity.tick(level, pos, state, entity);
    }
}
