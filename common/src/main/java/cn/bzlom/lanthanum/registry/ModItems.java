package cn.bzlom.lanthanum.registry;

import cn.bzlom.lanthanum.Lanthanum;
import cn.bzlom.lanthanum.item.MaterialColors;
import cn.bzlom.lanthanum.item.UpgradeCard;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Lanthanum.MOD_ID, Registries.ITEM);

    // ── Lanthanum materials ──────────────────────────────
    public static final RegistrySupplier<Item> RAW_LANTHANUM = ITEMS.register("raw_lanthanum",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));

    public static final RegistrySupplier<Item> LANTHANUM_INGOT = ITEMS.register("lanthanum_ingot",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));

    public static final RegistrySupplier<Item> LANTHANUM_DUST = ITEMS.register("lanthanum_dust",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));

    public static final RegistrySupplier<Item> WASHED_LANTHANUM_DUST = ITEMS.register("washed_lanthanum_dust",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));

    public static final RegistrySupplier<Item> LANTHANUM_CRYSTAL = ITEMS.register("lanthanum_crystal",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE).rarity(Rarity.UNCOMMON)));

    // ── Iron dust/crystal ─────────────────────────────────
    public static final RegistrySupplier<Item> IRON_DUST = ITEMS.register("iron_dust",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));
    public static final RegistrySupplier<Item> WASHED_IRON_DUST = ITEMS.register("washed_iron_dust",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));
    public static final RegistrySupplier<Item> IRON_CRYSTAL = ITEMS.register("iron_crystal",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));

    // ── Gold dust/crystal ─────────────────────────────────
    public static final RegistrySupplier<Item> GOLD_DUST = ITEMS.register("gold_dust",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));
    public static final RegistrySupplier<Item> WASHED_GOLD_DUST = ITEMS.register("washed_gold_dust",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));
    public static final RegistrySupplier<Item> GOLD_CRYSTAL = ITEMS.register("gold_crystal",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));

    // ── Copper dust/crystal ───────────────────────────────
    public static final RegistrySupplier<Item> COPPER_DUST = ITEMS.register("copper_dust",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));
    public static final RegistrySupplier<Item> WASHED_COPPER_DUST = ITEMS.register("washed_copper_dust",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));
    public static final RegistrySupplier<Item> COPPER_CRYSTAL = ITEMS.register("copper_crystal",
            () -> new Item(new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));

    // ── Upgrade cards ────────────────────────────────────
    public static final RegistrySupplier<Item> UPGRADE_SPEED_CARD = ITEMS.register("upgrade_speed_card",
            () -> new UpgradeCard(UpgradeCard.Type.SPEED, new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE).rarity(Rarity.UNCOMMON).stacksTo(1)));

    public static final RegistrySupplier<Item> UPGRADE_EFFICIENCY_CARD = ITEMS.register("upgrade_efficiency_card",
            () -> new UpgradeCard(UpgradeCard.Type.EFFICIENCY, new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE).rarity(Rarity.UNCOMMON).stacksTo(1)));

    public static final RegistrySupplier<Item> UPGRADE_OUTPUT_CARD = ITEMS.register("upgrade_output_card",
            () -> new UpgradeCard(UpgradeCard.Type.OUTPUT, new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE).rarity(Rarity.RARE).stacksTo(1)));

    // ── Block items ──────────────────────────────────────
    public static final RegistrySupplier<Item> LANTHANUM_ORE_ITEM = ITEMS.register("lanthanum_ore",
            () -> new BlockItem(ModBlocks.LANTHANUM_ORE.get(), new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));

    public static final RegistrySupplier<Item> DEEPSLATE_LANTHANUM_ORE_ITEM = ITEMS.register("deepslate_lanthanum_ore",
            () -> new BlockItem(ModBlocks.DEEPSLATE_LANTHANUM_ORE.get(), new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));

    public static final RegistrySupplier<Item> LANTHANUM_BLOCK_ITEM = ITEMS.register("lanthanum_block",
            () -> new BlockItem(ModBlocks.LANTHANUM_BLOCK.get(), new Item.Properties().arch$tab(ModTabs.LANTHANUM_BASE)));

    public static final RegistrySupplier<Item> CRUSHER_ITEM = ITEMS.register("crusher",
            () -> new BlockItem(ModBlocks.CRUSHER.get(), new Item.Properties().arch$tab(ModTabs.LANTHANUM_MACHINES)));

    public static final RegistrySupplier<Item> ORE_WASHER_ITEM = ITEMS.register("ore_washer",
            () -> new BlockItem(ModBlocks.ORE_WASHER.get(), new Item.Properties().arch$tab(ModTabs.LANTHANUM_MACHINES)));

    public static final RegistrySupplier<Item> CRYSTALLIZER_ITEM = ITEMS.register("crystallizer",
            () -> new BlockItem(ModBlocks.CRYSTALLIZER.get(), new Item.Properties().arch$tab(ModTabs.LANTHANUM_MACHINES)));

    // ── Material color registration (uses RegistrySupplier, safe to call anytime) ──
    public static void registerColors() {
        MaterialColors.register(LANTHANUM_DUST, MaterialColors.LANTHANUM);
        MaterialColors.register(IRON_DUST, MaterialColors.IRON);
        MaterialColors.register(GOLD_DUST, MaterialColors.GOLD);
        MaterialColors.register(COPPER_DUST, MaterialColors.COPPER);
        MaterialColors.register(WASHED_LANTHANUM_DUST, MaterialColors.WASHED_LANTHANUM);
        MaterialColors.register(WASHED_IRON_DUST, MaterialColors.WASHED_IRON);
        MaterialColors.register(WASHED_GOLD_DUST, MaterialColors.WASHED_GOLD);
        MaterialColors.register(WASHED_COPPER_DUST, MaterialColors.WASHED_COPPER);
        MaterialColors.register(LANTHANUM_CRYSTAL, MaterialColors.CRYSTAL_LANTHANUM);
        MaterialColors.register(IRON_CRYSTAL, MaterialColors.CRYSTAL_IRON);
        MaterialColors.register(GOLD_CRYSTAL, MaterialColors.CRYSTAL_GOLD);
        MaterialColors.register(COPPER_CRYSTAL, MaterialColors.CRYSTAL_COPPER);
    }
}
