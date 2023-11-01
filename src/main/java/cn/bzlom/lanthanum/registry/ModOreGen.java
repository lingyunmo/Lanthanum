package cn.bzlom.lanthanum.registry;

import cn.bzlom.lanthanum.item.ModOrePlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGen {
    public static void generateOres() {
        //Gemstone
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.GEMSTONE_ORE_MIDDLE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.GEMSTONE_ORE_SMALL);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.GEMSTONE_ORE_UPPER);

        //Lanthanum
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.LANTHANUM_ORE_MIDDLE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.LANTHANUM_ORE_SMALL);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.LANTHANUM_ORE_UPPER);
    }
}
