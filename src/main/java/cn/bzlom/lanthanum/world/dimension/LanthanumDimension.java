package cn.bzlom.lanthanum.world.dimension;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class LanthanumDimension {
    public static final RegistryKey<World> LANTHANUM_DIMENSION_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier("lanthanum", "lanthanum_dimension"));
    public static final RegistryKey<DimensionType> LANTHANUM_DIMENSION_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier("lanthanum", "lanthanum_dimension_type"));
}