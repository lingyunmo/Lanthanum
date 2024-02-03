package cn.bzlom.lanthanum.networking.packet;

import cn.bzlom.lanthanum.block.entity.LanthanumRefinerBlockEntity;
import cn.bzlom.lanthanum.screen.LanthanumRefinerScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class EnergySyncS2Packet {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender sender) {

        long energy = buf.readLong();
        BlockPos pos = buf.readBlockPos();

        if (client.world.getBlockEntity(pos) instanceof LanthanumRefinerBlockEntity entity) {
            entity.setEnergyStorage(energy);

            if (client.player.currentScreenHandler instanceof LanthanumRefinerScreenHandler screenHandler &&
                    screenHandler.blockEntity.getPos().equals(pos)) {
                entity.setEnergyStorage(energy);
            }
        }
    }
}