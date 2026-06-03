package cn.bzlom.lanthanum.networking;

import cn.bzlom.lanthanum.Lanthanum;
import dev.architectury.networking.NetworkChannel;

/**
 * Network channel for Lanthanum mod.
 * Currently unused in Phase 1 — ContainerData handles all GUI sync.
 * Prepared for Phase 2+ when custom packets are needed
 * (e.g., fluid sync, large energy values, config sync).
 */
public class ModNetwork {
    public static final NetworkChannel CHANNEL = NetworkChannel.create(
            new net.minecraft.resources.ResourceLocation(Lanthanum.MOD_ID, "main"));

    public static void init() {
        // Phase 1: No custom packets needed — ContainerData handles sync
        // Phase 2+: Register energy/fluid/config packets here
        Lanthanum.LOGGER.info("Network channel initialized");
    }
}
