package cn.bzlom.lanthanum.block.entity;

import cn.bzlom.lanthanum.Lanthanum;
import cn.bzlom.lanthanum.registry.ModBlocks;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Lanthanum.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<CrusherBlockEntity>> CRUSHER =
            BLOCK_ENTITIES.register("crusher",
                    () -> BlockEntityType.Builder.of(
                            CrusherBlockEntity::new,
                            ModBlocks.CRUSHER.get()
                    ).build(null));

    public static final RegistrySupplier<BlockEntityType<OreWasherBlockEntity>> ORE_WASHER =
            BLOCK_ENTITIES.register("ore_washer",
                    () -> BlockEntityType.Builder.of(
                            OreWasherBlockEntity::new,
                            ModBlocks.ORE_WASHER.get()
                    ).build(null));

    public static final RegistrySupplier<BlockEntityType<CrystallizerBlockEntity>> CRYSTALLIZER =
            BLOCK_ENTITIES.register("crystallizer",
                    () -> BlockEntityType.Builder.of(
                            CrystallizerBlockEntity::new,
                            ModBlocks.CRYSTALLIZER.get()
                    ).build(null));
}
