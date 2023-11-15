package cn.bzlom.lanthanum.networking;

import cn.bzlom.lanthanum.Lanthanum;
import cn.bzlom.lanthanum.networking.packet.MetalResistanceC2SPacket;
import cn.bzlom.lanthanum.networking.packet.MetalResistanceSyncDataS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessage {
    public static final Identifier RESTORE_METAL_RESISTANCE_ID = new Identifier(Lanthanum.MOD_ID,"restore_metal_resistance");
    public static final Identifier METAL_RESISTANCE_SYNC_ID = new Identifier(Lanthanum.MOD_ID,"metal_resistance_sync");
    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(RESTORE_METAL_RESISTANCE_ID, MetalResistanceC2SPacket::receive);
    }

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(METAL_RESISTANCE_SYNC_ID, MetalResistanceSyncDataS2CPacket::receive);
    }
}