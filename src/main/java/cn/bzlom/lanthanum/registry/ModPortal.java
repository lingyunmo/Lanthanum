package cn.bzlom.lanthanum.registry;

import cn.bzlom.lanthanum.Lanthanum;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.LanternBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Identifier;

public class ModPortal {
    public static void portalBuilder(){
        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.LANTHANUM_BLOCK)
                .lightWithFluid(Fluids.WATER)
                .destDimID(new Identifier(Lanthanum.MOD_ID,"lanthanum_dimension"))
                .tintColor(212,52,216)
                .registerPortal();
    }
}
