package cn.bzlom.lanthanum.event;

import cn.bzlom.lanthanum.networking.ModMessage;
import cn.bzlom.lanthanum.registry.ModItems;
import cn.bzlom.lanthanum.utils.IEntityDataSaver;
import cn.bzlom.lanthanum.utils.MetalResistanceData;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Material;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    private static boolean initialized = false;

    private static boolean isAroundMetalThem(ServerPlayerEntity player, ServerWorld world) {
        boolean isAroundMetalBlock = BlockPos.stream(player.getBoundingBox().expand(5)).map(world::getBlockState).
                anyMatch(state -> state.getMaterial().equals(Material.METAL));

        List<Item> ingots = Arrays.asList(
                Items.IRON_INGOT, Items.COPPER_INGOT, Items.GOLD_INGOT,
                ModItems.RAW_GEMSTONE, ModItems.GEMSTONE, ModItems.RAW_LANTHANUM, ModItems.LANTHANUM
        );

        List<Item> swords = Arrays.asList(
                Items.IRON_SWORD, Items.GOLDEN_SWORD, ModItems.GEMSTONE_SWORD, ModItems.LANTHANUM_SWORD
        );

        List<Item> pickaxes = Arrays.asList(
                Items.IRON_PICKAXE, Items.GOLDEN_PICKAXE, ModItems.GEMSTONE_PICKAXE, ModItems.LANTHANUM_PICKAXE
        );

        List<Item> axes = Arrays.asList(
                Items.IRON_AXE, Items.GOLDEN_AXE, ModItems.GEMSTONE_AXE, ModItems.LANTHANUM_AXE
        );

        List<Item> shovels = Arrays.asList(
                Items.IRON_SHOVEL, Items.GOLDEN_SHOVEL, ModItems.GEMSTONE_SHOVEL, ModItems.LANTHANUM_SHOVEL
        );

        List<Item> itemsToCheck = new ArrayList<>();
        itemsToCheck.addAll(ingots);
        itemsToCheck.addAll(swords);
        itemsToCheck.addAll(pickaxes);
        itemsToCheck.addAll(axes);
        itemsToCheck.addAll(shovels);

        boolean isCarryingMetalItem = player.getInventory().main.stream()
                .anyMatch(itemStack -> itemsToCheck.contains(itemStack.getItem()));

//        System.out.println("The Method Result:" + (isAroundMetalBlock || isCarryingMetalItem));
        return isAroundMetalBlock || isCarryingMetalItem;
    }

    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            ServerWorld world = player.getWorld();
            if (new Random().nextFloat() <= 0.05f
                    && MinecraftClient.getInstance().player != null
                    && !initialized
                    && ((IEntityDataSaver) player).getPersistentData().getInt("metalResistance") == 0
                    && !isAroundMetalThem(player, world)) {
                MetalResistanceData.addMetalResistance(((IEntityDataSaver) player), 10);
                initialized = true;
            } else if (new Random().nextFloat() <= 0.05f && MinecraftClient.getInstance().player != null) {
                ClientPlayNetworking.send(ModMessage.RESTORE_METAL_RESISTANCE_ID, PacketByteBufs.create());
            } else if (MinecraftClient.getInstance().player != null && initialized) {
                if (new Random().nextFloat() <= 0.0005f && !isAroundMetalThem(player, world)) {
                    IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
                    MetalResistanceData.addMetalResistance(dataPlayer, 1);
                }
                if (new Random().nextFloat() <= 0.00005f && isAroundMetalThem(player, world)) {
                    IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
                    MetalResistanceData.removeMetalResistance(dataPlayer, 1);
                }
            }
        }
    }
}