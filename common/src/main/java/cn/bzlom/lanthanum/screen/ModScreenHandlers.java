package cn.bzlom.lanthanum.screen;

import cn.bzlom.lanthanum.Lanthanum;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlers {
    public static final DeferredRegister<MenuType<?>> SCREEN_HANDLERS =
            DeferredRegister.create(Lanthanum.MOD_ID, Registries.MENU);

    public static final RegistrySupplier<MenuType<CrusherScreenHandler>> CRUSHER_SCREEN_HANDLER =
            SCREEN_HANDLERS.register("crusher",
                    () -> new MenuType<>(CrusherScreenHandler::new, FeatureFlags.VANILLA_SET));
}
