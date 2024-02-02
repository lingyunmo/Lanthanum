package cn.bzlom.lanthanum.screen;

import cn.bzlom.lanthanum.Lanthanum;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<LanthanumRefinerScreenHandler> LANTHANUM_REFINER_SCREEN_HANDLER
            = new ExtendedScreenHandlerType<>(LanthanumRefinerScreenHandler::new);

    public static void registerAllScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(Lanthanum.MOD_ID,
                "lanthanum_refiner_block_screen_handler"), LANTHANUM_REFINER_SCREEN_HANDLER);
    }
}