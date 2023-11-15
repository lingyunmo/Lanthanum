package cn.bzlom.lanthanum.event;

import cn.bzlom.lanthanum.networking.ModMessage;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;


public class KeyInputHandler {
    public static final String KEY_CATEGORY_LANTHANUM = "key.category.lanthanum.lanthanum";
    public static final String KEY_RESTORE_METAL_RESISTANCE = "key.lanthanum.restore_metal_resistance";

    public static KeyBinding restoreMetalResistanceKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (restoreMetalResistanceKey.wasPressed()) {
                ClientPlayNetworking.send(ModMessage.DRINKING_ID, PacketByteBufs.create());
            }
        });
    }

    public static void register() {
        restoreMetalResistanceKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(KEY_RESTORE_METAL_RESISTANCE,
                InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_LANTHANUM));

        registerKeyInputs();
    }
}