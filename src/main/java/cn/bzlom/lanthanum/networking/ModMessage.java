package cn.bzlom.lanthanum.networking;

import cn.bzlom.lanthanum.Lanthanum;
import cn.bzlom.lanthanum.networking.packet.MetalResistanceC2SPacket;
import cn.bzlom.lanthanum.networking.packet.MetalResistanceSyncDataS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessage {
    public static final Identifier DRINKING_ID = new Identifier(Lanthanum.MOD_ID,"drinking");
    public static final Identifier THIRST_SYNC_ID = new Identifier(Lanthanum.MOD_ID,"thirst_sync");
    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(DRINKING_ID, MetalResistanceC2SPacket::receive);
    }

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(THIRST_SYNC_ID, MetalResistanceSyncDataS2CPacket::receive);
    }
}