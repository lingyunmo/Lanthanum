package cn.bzlom.lanthanum.networking.packet;

import cn.bzlom.lanthanum.utils.IEntityDataSaver;
import cn.bzlom.lanthanum.utils.MetalResistanceData;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Material;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

public class MetalResistanceC2SPacket {
    public static final String MESSAGE_RESTORE_METAL_RESISTANCE = "message.lanthanum.restore_metal_resistance";
    public static final String MESSAGE_LOW_LEVEL_METAL_RESISTANCE = "message.lanthanum.low_level_metal_resistance";

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender sender) {

        ServerWorld world = player.getWorld();
        if (isAroundMetalThem(player, world)) {

            player.sendMessage(Text.translatable(MESSAGE_RESTORE_METAL_RESISTANCE).
                    fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), false);

            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_GENERIC_DRINK,
                    SoundCategory.PLAYERS, 0.5f, world.random.nextFloat() * 0.1f + 0.9f);

            MetalResistanceData.removeMetalResistance((IEntityDataSaver) player, 1);

            player.sendMessage(Text.literal("MetalResistance:" + ((IEntityDataSaver) player).
                            getPersistentData().getInt("metalResistance")).
                    fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
        } else {
            player.sendMessage(Text.translatable(MESSAGE_LOW_LEVEL_METAL_RESISTANCE).
                    fillStyle(Style.EMPTY.withColor(Formatting.RED)), false);

            MetalResistanceData.removeMetalResistance((IEntityDataSaver) player, 1);

            player.sendMessage(Text.literal("MetalResistance:" + ((IEntityDataSaver) player).
                            getPersistentData().getInt("metalResistance")).
                    fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);

            // Sync to client
            MetalResistanceData.syncThirst(((IEntityDataSaver) player).getPersistentData().getInt("metalResistance"), player);
        }
    }

    private static boolean isAroundMetalThem(ServerPlayerEntity player, ServerWorld world) {
        boolean isAroundMetalBlock = BlockPos.stream(player.getBoundingBox().expand(5)).map(world::getBlockState).
                anyMatch(state -> state.getMaterial().equals(Material.METAL));

        boolean isCarryingMetalItem = player.getInventory().main.stream().anyMatch(itemStack ->
                itemStack.isOf(Items.IRON_INGOT) ||
                        itemStack.isOf(Items.COPPER_INGOT) ||
                        itemStack.isOf(Items.GOLD_INGOT) ||
                        itemStack.isOf(Items.IRON_SWORD) ||
                        itemStack.isOf(Items.GOLDEN_SWORD) ||
                        itemStack.isOf(Items.IRON_PICKAXE) ||
                        itemStack.isOf(Items.GOLDEN_PICKAXE) ||
                        itemStack.isOf(Items.IRON_AXE) ||
                        itemStack.isOf(Items.GOLDEN_AXE) ||
                        itemStack.isOf(Items.IRON_SHOVEL) ||
                        itemStack.isOf(Items.GOLDEN_SHOVEL));
        return isAroundMetalBlock && isCarryingMetalItem;
    }
}