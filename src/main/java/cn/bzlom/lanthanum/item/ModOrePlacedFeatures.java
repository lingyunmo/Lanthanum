package cn.bzlom.lanthanum.item;

import cn.bzlom.lanthanum.Lanthanum;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModOrePlacedFeatures {

    //Gemstone family
    public static final RegistryKey<PlacedFeature> GEMSTONE_ORE_UPPER = registerKey("gemstone_ore_upper");
    public static final RegistryKey<PlacedFeature> GEMSTONE_ORE_MIDDLE = registerKey("gemstone_ore_middle");
    public static final RegistryKey<PlacedFeature> GEMSTONE_ORE_SMALL = registerKey("gemstone_ore_small");

    //Lanthanum family
    public static final RegistryKey<PlacedFeature> LANTHANUM_ORE_UPPER = registerKey("lanthanum_ore_upper");
    public static final RegistryKey<PlacedFeature> LANTHANUM_ORE_MIDDLE = registerKey("lanthanum_ore_middle");
    public static final RegistryKey<PlacedFeature> LANTHANUM_ORE_SMALL = registerKey("lanthanum_ore_small");

    public ModOrePlacedFeatures() {
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Lanthanum.MOD_ID, name));
    }

    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }

    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        var registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        //Gemstone
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry1 = registryEntryLookup.getOrThrow(ModOreConfiguredFeatures.GEMSTONE_ORE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry2 = registryEntryLookup.getOrThrow(ModOreConfiguredFeatures.GEMSTONE_ORE_BURIED);

        register(featureRegisterable, GEMSTONE_ORE_UPPER, registryEntry1, modifiersWithCount(50, HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.fixed(256))));
        register(featureRegisterable, GEMSTONE_ORE_MIDDLE, registryEntry2, modifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-64), YOffset.fixed(32))));
        register(featureRegisterable, GEMSTONE_ORE_SMALL, registryEntry2, modifiers(CountPlacementModifier.of(UniformIntProvider.create(0, 1)), HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-48))));

        //Lanthanum
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry3 = registryEntryLookup.getOrThrow(ModOreConfiguredFeatures.LANTHANUM_ORE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry4 = registryEntryLookup.getOrThrow(ModOreConfiguredFeatures.LANTHANUM_ORE_BURIED);


        register(featureRegisterable, LANTHANUM_ORE_UPPER, registryEntry3, modifiersWithCount(50, HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.fixed(256))));
        register(featureRegisterable, LANTHANUM_ORE_MIDDLE, registryEntry4, modifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-64), YOffset.fixed(32))));
        register(featureRegisterable, LANTHANUM_ORE_SMALL, registryEntry4, modifiers(CountPlacementModifier.of(UniformIntProvider.create(0, 1)), HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-48))));
    }


    public static void register(Registerable<PlacedFeature> featureRegisterable, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
        featureRegisterable.register(key, new PlacedFeature(feature, List.copyOf(modifiers)));
    }

    public static void register(Registerable<PlacedFeature> featureRegisterable, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        register(featureRegisterable, key, feature, List.of(modifiers));
    }
}
