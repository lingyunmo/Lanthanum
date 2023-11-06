package cn.bzlom.lanthanum;

import cn.bzlom.lanthanum.client.ThirstHudOverlay;
import cn.bzlom.lanthanum.event.KeyInputHandler;
import cn.bzlom.lanthanum.networking.ModMessage;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class LanthanumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        ModMessage.registerC2SPackets();
        HudRenderCallback.EVENT.register(new ThirstHudOverlay());
    }
}