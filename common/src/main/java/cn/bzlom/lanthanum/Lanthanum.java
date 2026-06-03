package cn.bzlom.lanthanum;

import cn.bzlom.lanthanum.block.entity.ModBlockEntities;
import cn.bzlom.lanthanum.networking.ModNetwork;
import cn.bzlom.lanthanum.recipe.ModRecipes;
import cn.bzlom.lanthanum.registry.ModBlocks;
import cn.bzlom.lanthanum.registry.ModItems;
import cn.bzlom.lanthanum.registry.ModOreGen;
import cn.bzlom.lanthanum.registry.ModTabs;
import cn.bzlom.lanthanum.screen.ModScreenHandlers;
import dev.architectury.event.events.common.LifecycleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Lanthanum {
    public static final String MOD_ID = "lanthanum";
    public static final Logger LOGGER = LoggerFactory.getLogger("Lanthanum");

    private static boolean initialized = false;

    public static void init() {
        if (initialized) {
            LOGGER.warn("Lanthanum.init() called twice! Ignoring duplicate call.");
            return;
        }
        initialized = true;

        LOGGER.info("Lanthanum Dynamics Initializing...");

        ModTabs.TABS.register();
        ModBlocks.BLOCKS.register();
        ModItems.ITEMS.register();
        ModItems.registerColors();
        ModBlockEntities.BLOCK_ENTITIES.register();
        ModScreenHandlers.SCREEN_HANDLERS.register();
        ModRecipes.RECIPE_SERIALIZERS.register();
        ModRecipes.RECIPE_TYPES.register();
        ModNetwork.init();

        LifecycleEvent.SETUP.register(ModOreGen::onSetup);

        LOGGER.info("Lanthanum Dynamics Initialized!");
    }
}
