package cn.bzlom.lanthanum;

import cn.bzlom.lanthanum.item.LanthanumFoodComponents;
import cn.bzlom.lanthanum.registry.ModItemGroup;
import cn.bzlom.lanthanum.registry.ModItems;
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
    public static final Item LANTHANUM_STACK_ROTTEN_FLESH = new Item(new FabricItemSettings().food(LanthanumFoodComponents.LANTHANUM_STACK_ROTTEN_FLESH));

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "lanthanum_stack_rotten_flesh"), LANTHANUM_STACK_ROTTEN_FLESH);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
            content.addAfter(Items.PUMPKIN_PIE, LANTHANUM_STACK_ROTTEN_FLESH);
        });
        ModItemGroup.registerModItemGroup();
        ModItems.registerItems();
    }
}