package cn.bzlom.lanthanum.item;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Maps dust/crystal items to their tint color.
 * Colors are registered with RegistrySupplier and resolved lazily
 * so DeferredRegister ordering doesn't matter.
 */
public class MaterialColors {

    private static final Map<RegistrySupplier<? extends Item>, Integer> COLOR_MAP = new LinkedHashMap<>();

    public static void register(RegistrySupplier<? extends Item> supplier, int color) {
        COLOR_MAP.put(supplier, color);
    }

    public static int getColor(Item item) {
        for (var entry : COLOR_MAP.entrySet()) {
            if (entry.getKey().isPresent() && entry.getKey().get() == item) {
                return entry.getValue();
            }
        }
        return 0xFFFFFF;
    }

    // ── Material color constants ───────────────────────
    public static final int IRON       = 0xFFD8D8D8;
    public static final int GOLD       = 0xFFFCEB55;
    public static final int COPPER     = 0xFFE77C56;
    public static final int LANTHANUM  = 0xFFC8D2E6;

    public static final int WASHED_IRON      = 0xFFE8E8E8;
    public static final int WASHED_GOLD      = 0xFFFFF0AA;
    public static final int WASHED_COPPER    = 0xFFFFAA88;
    public static final int WASHED_LANTHANUM = 0xFFE0E6F5;

    public static final int CRYSTAL_IRON      = 0xFFB0B0B0;
    public static final int CRYSTAL_GOLD      = 0xFFFFD700;
    public static final int CRYSTAL_COPPER    = 0xFFFF6D3A;
    public static final int CRYSTAL_LANTHANUM = 0xFF9098CC;
}
