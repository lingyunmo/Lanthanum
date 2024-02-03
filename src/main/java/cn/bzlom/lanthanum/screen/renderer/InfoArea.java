package cn.bzlom.lanthanum.screen.renderer;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Rect2i;

public abstract class InfoArea {
    protected final Rect2i area;

    public InfoArea(Rect2i area) {
        this.area = area;
    }
}
