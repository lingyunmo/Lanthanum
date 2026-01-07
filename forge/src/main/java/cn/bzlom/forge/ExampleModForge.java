package cn.bzlom.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import cn.bzlom.Lanthanum;

@Mod(Lanthanum.MOD_ID)
@SuppressWarnings("removal")
public final class ExampleModForge {
    public ExampleModForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(Lanthanum.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        Lanthanum.init();
    }
}
