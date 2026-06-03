package cn.bzlom.lanthanum.fabric.client;

import cn.bzlom.lanthanum.item.MaterialColors;
import cn.bzlom.lanthanum.registry.ModItems;
import cn.bzlom.lanthanum.screen.CrusherScreen;
import cn.bzlom.lanthanum.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.Item;

public class LanthanumFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER.get(), CrusherScreen::new);

        // ── ItemColor tinting for dust/crystal items ──
        Item[] tinted = new Item[]{
                ModItems.LANTHANUM_DUST.get(), ModItems.WASHED_LANTHANUM_DUST.get(), ModItems.LANTHANUM_CRYSTAL.get(),
                ModItems.IRON_DUST.get(), ModItems.WASHED_IRON_DUST.get(), ModItems.IRON_CRYSTAL.get(),
                ModItems.GOLD_DUST.get(), ModItems.WASHED_GOLD_DUST.get(), ModItems.GOLD_CRYSTAL.get(),
                ModItems.COPPER_DUST.get(), ModItems.WASHED_COPPER_DUST.get(), ModItems.COPPER_CRYSTAL.get(),
        };

        ColorProviderRegistry.ITEM.register(
                (stack, layer) -> layer == 0 ? MaterialColors.getColor(stack.getItem()) : -1,
                tinted
        );
    }
}
