package cn.bzlom.lanthanum.fabric;

import cn.bzlom.lanthanum.Lanthanum;
import net.fabricmc.api.ModInitializer;

public class LanthanumFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Lanthanum.init();
    }
}
