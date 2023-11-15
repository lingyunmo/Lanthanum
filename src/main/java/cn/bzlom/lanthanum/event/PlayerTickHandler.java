package cn.bzlom.lanthanum.event;

import cn.bzlom.lanthanum.utils.IEntityDataSaver;
import cn.bzlom.lanthanum.utils.MetalResistanceData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Material;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    private static boolean isAroundMetalThem(ServerPlayerEntity player, ServerWorld world) {
        //无法正确判断金属内容
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
        System.out.println("方法内判断结果为" + (isAroundMetalBlock || isCarryingMetalItem));
        return isAroundMetalBlock || isCarryingMetalItem;
    }

    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            ServerWorld world = player.getWorld();
//            if(new Random().nextFloat() <= 0.0005f){
            if (new Random().nextFloat() <= 0.005f && !isAroundMetalThem(player,world)) {
                IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
                MetalResistanceData.addMetalResistance(dataPlayer, 1);
                player.sendMessage(Text.literal("通过每tick增加的金属耐受性"));
            }
        }
    }
}