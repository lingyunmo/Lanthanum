package cn.bzlom.lanthanum.registry;

import cn.bzlom.lanthanum.Lanthanum;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    //gemstone family
    public static final Item GEMSTONE = registerItem("gemstone",
            new Item(new FabricItemSettings()),
            ModItemGroup.LanthanumBase, ItemGroups.INGREDIENTS);

    public static final Item RAW_GEMSTONE = registerItem("raw_gemstone",
            new Item(new FabricItemSettings()),
            ModItemGroup.LanthanumBase, ItemGroups.INGREDIENTS);

    public static final Item GEMSTONE_SWORD = registerItem("gemstone_sword",
            new SwordItem(GemstoneToolMaterials.GEMSTONE, 5, -2.4F, new FabricItemSettings()), ModItemGroup.LanthanumBase);
    public static final Item GEMSTONE_SHOVEL = registerItem("gemstone_shovel",
            new ShovelItem(GemstoneToolMaterials.GEMSTONE, 1.5F, -3.0F, new FabricItemSettings()), ModItemGroup.LanthanumBase);
    public static final Item GEMSTONE_PICKAXE = registerItem("gemstone_pickaxe",
            new PickaxeItem(GemstoneToolMaterials.GEMSTONE, 1, -2.8F, new FabricItemSettings()), ModItemGroup.LanthanumBase);
    public static final Item GEMSTONE_AXE = registerItem("gemstone_axe",
            new AxeItem(GemstoneToolMaterials.GEMSTONE, 6.0F, -3.0F, new FabricItemSettings()), ModItemGroup.LanthanumBase);
    public static final Item GEMSTONE_HOE = registerItem("gemstone_hoe",
            new HoeItem(GemstoneToolMaterials.GEMSTONE, 0, -3.0F, new FabricItemSettings()), ModItemGroup.LanthanumBase);

    //lanthanum family
    public static final Item LANTHANUM = registerItem("lanthanum",
            new Item(new FabricItemSettings()),
            ModItemGroup.LanthanumBase, ItemGroups.INGREDIENTS);

    public static final Item RAW_LANTHANUM = registerItem("raw_lanthanum",
            new Item(new FabricItemSettings()),
            ModItemGroup.LanthanumBase, ItemGroups.INGREDIENTS);

    public static final Item LANTHANUM_SWORD = registerItem("lanthanum_sword",
            new SwordItem(LanthanumToolMaterials.LANTHANUM, 5, -2.4F, new FabricItemSettings()), ModItemGroup.LanthanumBase);
    public static final Item LANTHANUM_SHOVEL = registerItem("lanthanum_shovel",
            new ShovelItem(LanthanumToolMaterials.LANTHANUM, 1.5F, -3.0F, new FabricItemSettings()), ModItemGroup.LanthanumBase);
    public static final Item LANTHANUM_PICKAXE = registerItem("lanthanum_pickaxe",
            new PickaxeItem(LanthanumToolMaterials.LANTHANUM, 1, -2.8F, new FabricItemSettings()), ModItemGroup.LanthanumBase);
    public static final Item LANTHANUM_AXE = registerItem("lanthanum_axe",
            new AxeItem(LanthanumToolMaterials.LANTHANUM, 6.0F, -3.0F, new FabricItemSettings()), ModItemGroup.LanthanumBase);
    public static final Item LANTHANUM_HOE = registerItem("lanthanum_hoe",
            new HoeItem(LanthanumToolMaterials.LANTHANUM, 0, -3.0F, new FabricItemSettings()), ModItemGroup.LanthanumBase);

    public static Item registerItem(String name, Item item, ItemGroup... itemGroups) {
        Item registeredItem = Registry.register(Registries.ITEM, new Identifier(Lanthanum.MOD_ID, name), item);
        for (ItemGroup itemGroup : itemGroups) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(registeredItem));
        }
        return registeredItem;
    }

    public static void registerItems() {
        Lanthanum.LOGGER.debug("Registering mod items for" + Lanthanum.MOD_ID);
    }
}
