package cn.bzlom.lanthanum.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CrusherBlockEntity extends BaseMachineBlockEntity {

    private static final long ENERGY_CAPACITY = 30000;
    private static final long ENERGY_MAX_IO = 32;
    private static final int BASE_PROCESS_TIME = 200;   // 10s
    private static final long BASE_ENERGY_PER_TICK = 32;

    public CrusherBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRUSHER.get(), pos, state);
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
        return Component.translatable("block.lanthanum.crusher");
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CrusherBlockEntity entity) {
        BaseMachineBlockEntity.tick(level, pos, state, entity);
    }
}
