package cn.bzlom.lanthanum;

import cn.bzlom.lanthanum.item.LanthanumFoodComponents;
import cn.bzlom.lanthanum.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lanthanum implements ModInitializer {
    public static final String MOD_ID = "lanthanum";
    public static final Logger LOGGER = LoggerFactory.getLogger("Lanthanum");


    @Override
    public void onInitialize() {
        ModItemGroup.registerModItemGroup();
        ModItems.registerItems();
        ModBlocks.registerModBlocks();
        ModOreGen.generateOres();
    }
}