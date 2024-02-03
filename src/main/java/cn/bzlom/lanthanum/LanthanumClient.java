package cn.bzlom.lanthanum;

import cn.bzlom.lanthanum.client.MetalResistanceHudOverlay;
import cn.bzlom.lanthanum.event.KeyInputHandler;
import cn.bzlom.lanthanum.networking.ModMessage;
import cn.bzlom.lanthanum.screen.LanthanumRefinerScreen;
import cn.bzlom.lanthanum.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class LanthanumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        ModMessage.registerC2SPackets();
        HudRenderCallback.EVENT.register(new MetalResistanceHudOverlay());
        HandledScreens.register(ModScreenHandlers.LANTHANUM_REFINER_SCREEN_HANDLER, LanthanumRefinerScreen::new);
    }
}