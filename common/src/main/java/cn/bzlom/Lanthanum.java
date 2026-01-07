package cn.bzlom;

import cn.bzlom.registry.ModItems;
import cn.bzlom.registry.ModTabs;

public final class Lanthanum {
    public static final String MOD_ID = "lanthanum";

    public static void init() {
        ModItems.register();
        ModTabs.register();

        System.out.println("Lanthanum Dynamics Initialized!");
    }
}