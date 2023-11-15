package cn.bzlom.lanthanum.networking.packet;

import cn.bzlom.lanthanum.utils.IEntityDataSaver;
import cn.bzlom.lanthanum.utils.MetalResistanceData;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class MetalResistanceC2SPacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender sender) {
        //for future
//        if (null!=null) {
//            MetalResistanceData.addMetalResistance((IEntityDataSaver) player, 1);
//        }

        // Sync to client
        MetalResistanceData.syncThirst(((IEntityDataSaver) player).getPersistentData().getInt("metalResistance"), player);
    }
}