package cn.bzlom.lanthanum.event;

import cn.bzlom.lanthanum.utils.IEntityDataSaver;
import cn.bzlom.lanthanum.utils.MetalResistanceData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Random;

public class PlayerTickHandler implements ServerTickEvents.StartTick{
    @Override
    public void onStartTick(MinecraftServer server) {
        for(ServerPlayerEntity player : server.getPlayerManager().getPlayerList()){
            if(new Random().nextFloat() <= 0.0005f){
                IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
                MetalResistanceData.addMetalResistance(dataPlayer,1);
                player.sendMessage(Text.literal("Add Metal Resistance"));
            }
        }
    }
}