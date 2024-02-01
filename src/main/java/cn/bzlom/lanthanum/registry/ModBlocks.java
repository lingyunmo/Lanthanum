package cn.bzlom.lanthanum.registry;

import cn.bzlom.lanthanum.Lanthanum;
import cn.bzlom.lanthanum.block.custom.LanthanumRefinerBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    //Gemstone family
    public static final Block GEMSTONE_BLOCK = registerBlock("gemstone_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(8.0f).requiresTool()),
            ModItemGroup.LanthanumBase);
    public static final Block GEMSTONE_ORE = registerBlock("gemstone_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(8.0f).requiresTool(),
                    UniformIntProvider.create(4, 8)), ModItemGroup.LanthanumBase);
    public static final Block DEEP_SLATE_GEMSTONE_ORE = registerBlock("deep_slate_gemstone_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(8.0f).requiresTool(),
                    UniformIntProvider.create(4, 8)), ModItemGroup.LanthanumBase);

    //lanthanum family
    public static final Block LANTHANUM_BLOCK = registerBlock("lanthanum_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(8.0f).requiresTool()),
            ModItemGroup.LanthanumBase);
    public static final Block LANTHANUM_ORE = registerBlock("lanthanum_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(8.0f).requiresTool(),
                    UniformIntProvider.create(4, 8)), ModItemGroup.LanthanumBase);
    public static final Block DEEP_SLATE_LANTHANUM_ORE = registerBlock("deep_slate_lanthanum_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(8.0f).requiresTool(),
                    UniformIntProvider.create(4, 8)), ModItemGroup.LanthanumBase);

    public static final Block LANTHANUM_PORTAL_FRAME = registerBlock("lanthanum_portal_frame",
            new Block(FabricBlockSettings.of(Material.METAL).strength(8.0f).requiresTool()),
            ModItemGroup.LanthanumBase);

    //lanthanum facilities
    public static final Block LANTHANUM_REFINER_BLOCK = registerBlock("lanthanum_refiner_block",
            new LanthanumRefinerBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(4.0f).requiresTool().nonOpaque()),ModItemGroup.LanthanumMachine);

    public static Block registerBlock(String name, Block block, ItemGroup... itemGroups) {
        ModItems.registerItem(name, new BlockItem(block, new FabricItemSettings()), itemGroups);
        return Registry.register(Registries.BLOCK, new Identifier(Lanthanum.MOD_ID, name), block);
    }


    public static void registerModBlocks() {
        Lanthanum.LOGGER.debug("Registering mod blocks for" + Lanthanum.MOD_ID);
    }
}
