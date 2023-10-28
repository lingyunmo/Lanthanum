package cn.bzlom.lanthanum.registry;

import cn.bzlom.lanthanum.Lanthanum;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item GEMSTONE = registerItem("gemstone",
            new Item(new FabricItemSettings()),
            ModItemGroup.GEMSTONE, ItemGroups.INGREDIENTS);

    public static final Item RAW_GEMSTONE = registerItem("raw_gemstone",
            new Item(new FabricItemSettings()),
            ModItemGroup.GEMSTONE,ItemGroups.INGREDIENTS);
    public static final Item GEMSTONE_BLOCK = registerItem("gemstone_block",
            new Item(new FabricItemSettings()),
            ModItemGroup.GEMSTONE, ItemGroups.INGREDIENTS);
    public static Item registerItem(String name, Item item, ItemGroup... itemGroups){
        Item registeredItem = Registry.register(Registries.ITEM,new Identifier(Lanthanum.MOD_ID,name),item);
        for (ItemGroup itemGroup : itemGroups){
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(registeredItem));
        }
        return registeredItem;
    }
    public static void registerItems(){
        Lanthanum.LOGGER.debug("Registering mod items for" + Lanthanum.MOD_ID);
    }
}
