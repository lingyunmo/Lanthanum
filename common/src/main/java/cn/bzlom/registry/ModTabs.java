package cn.bzlom.registry;

import cn.bzlom.Lanthanum;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Lanthanum.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> LANTHANUM_TAB = TABS.register("lanthanum_group", () ->
            CreativeTabRegistry.create(
                    Component.translatable("itemGroup.lanthanum.main"),
                    () -> new ItemStack(ModItems.LANTHANUM_ROTTEN_FLESH.get())
            )
    );

    public static void register() {
        TABS.register();
    }
}