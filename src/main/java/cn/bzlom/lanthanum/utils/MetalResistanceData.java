package cn.bzlom.lanthanum.utils;

import cn.bzlom.lanthanum.networking.ModMessage;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class MetalResistanceData {
    public static void addMetalResistance(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int metalResistance = nbt.getInt("metalResistance");
        metalResistance = Math.min(metalResistance + amount, 10);
        nbt.putInt("metalResistance", metalResistance);
        if (player instanceof ServerPlayerEntity) {
            syncThirst(metalResistance, (ServerPlayerEntity) player);
        }
    }

    public static void removeMetalResistance(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int metalResistance = nbt.getInt("metalResistance");
        metalResistance = Math.max(0, metalResistance - amount);
        nbt.putInt("metalResistance", metalResistance);
        syncThirst(metalResistance, (ServerPlayerEntity) player);
    }

    public static void syncThirst(int metalResistance, ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(metalResistance);
        ServerPlayNetworking.send(player, ModMessage.METAL_RESISTANCE_SYNC_ID, buf);
    }
}