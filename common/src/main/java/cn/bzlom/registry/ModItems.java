package cn.bzlom.registry;

import cn.bzlom.Lanthanum;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Lanthanum.MOD_ID, Registries.ITEM);

    public static final FoodProperties LANTHANUM_FOOD_PROP = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(0.1f)
            .meat()
            .alwaysEat()
            .build();

    public static final RegistrySupplier<Item> LANTHANUM_ROTTEN_FLESH = ITEMS.register("lanthanum_rotten_flesh",
            () -> new Item(new Item.Properties().food(LANTHANUM_FOOD_PROP).arch$tab(ModTabs.LANTHANUM_TAB)));

    public static void register() {
        ITEMS.register();
    }
}