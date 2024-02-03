package cn.bzlom.lanthanum.block.entity;

import cn.bzlom.lanthanum.Lanthanum;
import cn.bzlom.lanthanum.registry.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import team.reborn.energy.api.EnergyStorage;

public class ModBlockEntities {
    public static BlockEntityType<LanthanumRefinerBlockEntity> LANTHANUM_REFINER_BLOCK;

    public static void registerBlockEntities() {
        LANTHANUM_REFINER_BLOCK = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Lanthanum.MOD_ID, "lanthanum_refiner_block"),
                FabricBlockEntityTypeBuilder.create(LanthanumRefinerBlockEntity::new,
                        ModBlocks.LANTHANUM_REFINER_BLOCK).build(null));

        EnergyStorage.SIDED.registerForBlockEntities((blockEntity, context) -> ((LanthanumRefinerBlockEntity) blockEntity).energyStorage, LANTHANUM_REFINER_BLOCK);
//        FluidStorage.SIDED.registerForBlockEntities((blockEntity, context) -> ((GemInfusingBlockEntity)blockEntity).fluidStorage,GEM_INFUSING_STATION);
//
//        EXAMPLE_ANIMATION_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
//                new Identifier(TutorialMod.MOD_ID,"example_animation_block"),
//                FabricBlockEntityTypeBuilder.create(ExampleAnimationBlockEntity::new,
//                        ModBlocks.EXAMPLE_ANIAMTION_BLOCK).build());
    }
}
