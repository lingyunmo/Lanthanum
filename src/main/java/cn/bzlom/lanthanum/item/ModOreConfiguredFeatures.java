package cn.bzlom.lanthanum.item;


import cn.bzlom.lanthanum.Lanthanum;
import cn.bzlom.lanthanum.registry.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModOreConfiguredFeatures {
    //Gemstone
    public static final RegistryKey<ConfiguredFeature<?, ?>> GEMSTONE_ORE = registerKey("gemstone_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GEMSTONE_ORE_BURIED = registerKey("gemstone_ore_buried");

    //Lanthanum

    public static final RegistryKey<ConfiguredFeature<?, ?>> LANTHANUM_ORE = registerKey("lanthanum_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LANTHANUM_ORE_BURIED = registerKey("lanthanum_ore_buried");

    public ModOreConfiguredFeatures() {
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Lanthanum.MOD_ID, name));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RuleTest shallow = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslate = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        //Gemstone
        List<OreFeatureConfig.Target> overworld_silver_ore = List.of(OreFeatureConfig.createTarget(shallow, ModBlocks.GEMSTONE_ORE.getDefaultState()), OreFeatureConfig.createTarget(deepslate, ModBlocks.DEEP_SLATE_GEMSTONE_ORE.getDefaultState()));

        register(featureRegisterable, GEMSTONE_ORE, Feature.ORE, new OreFeatureConfig(overworld_silver_ore, 9));
        register(featureRegisterable, GEMSTONE_ORE_BURIED, Feature.ORE, new OreFeatureConfig(overworld_silver_ore, 9, 0.7F));

        //Lanthanum
        List<OreFeatureConfig.Target> overworld_lanthanum_ore = List.of(OreFeatureConfig.createTarget(shallow, ModBlocks.LANTHANUM_ORE.getDefaultState()), OreFeatureConfig.createTarget(deepslate, ModBlocks.DEEP_SLATE_LANTHANUM_ORE.getDefaultState()));

        register(featureRegisterable, LANTHANUM_ORE, Feature.ORE, new OreFeatureConfig(overworld_lanthanum_ore, 2));
        register(featureRegisterable, LANTHANUM_ORE_BURIED, Feature.ORE, new OreFeatureConfig(overworld_lanthanum_ore, 2, 0.7F));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        registerable.register(key, new ConfiguredFeature<>(feature, config));
    }
}
