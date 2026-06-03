package cn.bzlom.lanthanum.registry;

import cn.bzlom.lanthanum.Lanthanum;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Lanthanum.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> LANTHANUM_BASE = TABS.register("lanthanum_base",
            () -> CreativeTabRegistry.create(
                    Component.translatable("itemGroup.lanthanum.base"),
                    () -> new ItemStack(ModItems.LANTHANUM_INGOT.get())
            ));

    public static final RegistrySupplier<CreativeModeTab> LANTHANUM_MACHINES = TABS.register("lanthanum_machines",
            () -> CreativeTabRegistry.create(
                    Component.translatable("itemGroup.lanthanum.machines"),
                    () -> new ItemStack(ModItems.CRUSHER_ITEM.get())
            ));
}
