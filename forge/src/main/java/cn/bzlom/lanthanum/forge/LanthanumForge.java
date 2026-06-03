package cn.bzlom.lanthanum.forge;

import cn.bzlom.lanthanum.Lanthanum;
import cn.bzlom.lanthanum.item.MaterialColors;
import cn.bzlom.lanthanum.registry.ModItems;
import cn.bzlom.lanthanum.screen.CrusherScreen;
import cn.bzlom.lanthanum.screen.ModScreenHandlers;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Lanthanum.MOD_ID)
public class LanthanumForge {

    public LanthanumForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(Lanthanum.MOD_ID, modEventBus);

        Lanthanum.init();

        modEventBus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER.get(), CrusherScreen::new);

            // ── ItemColor tinting for dust/crystal items ──
            Item[] tinted = new Item[]{
                    ModItems.LANTHANUM_DUST.get(), ModItems.WASHED_LANTHANUM_DUST.get(), ModItems.LANTHANUM_CRYSTAL.get(),
                    ModItems.IRON_DUST.get(), ModItems.WASHED_IRON_DUST.get(), ModItems.IRON_CRYSTAL.get(),
                    ModItems.GOLD_DUST.get(), ModItems.WASHED_GOLD_DUST.get(), ModItems.GOLD_CRYSTAL.get(),
                    ModItems.COPPER_DUST.get(), ModItems.WASHED_COPPER_DUST.get(), ModItems.COPPER_CRYSTAL.get(),
            };
            for (Item item : tinted) {
                Minecraft.getInstance().getItemColors().register(
                        (stack, layer) -> layer == 0 ? MaterialColors.getColor(stack.getItem()) : -1,
                        item
                );
            }
        });
    }
}
