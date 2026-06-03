package cn.bzlom.lanthanum.registry;

import cn.bzlom.lanthanum.Lanthanum;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModOreGen {

    public static final ResourceKey<PlacedFeature> LANTHANUM_ORE_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE,
                    new ResourceLocation(Lanthanum.MOD_ID, "lanthanum_ore_placed"));

    public static void onSetup() {
        BiomeModifications.addProperties(
                ctx -> ctx.hasTag(BiomeTags.IS_OVERWORLD),
                (ctx, props) -> props.getGenerationProperties().addFeature(
                        GenerationStep.Decoration.UNDERGROUND_ORES,
                        LANTHANUM_ORE_PLACED
                )
        );
        Lanthanum.LOGGER.info("Ore generation registered for Lanthanum ore");
    }
}
