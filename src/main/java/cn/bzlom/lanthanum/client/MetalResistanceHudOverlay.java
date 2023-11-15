package cn.bzlom.lanthanum.client;

import cn.bzlom.lanthanum.Lanthanum;
import cn.bzlom.lanthanum.utils.IEntityDataSaver;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MetalResistanceHudOverlay implements HudRenderCallback {
    private static final Identifier FILLED_METAL_RESISTANCE = new Identifier(Lanthanum.MOD_ID,
            "textures/thirst/filled_thirst.png");
    private static final Identifier EMPTY_METAL_RESISTANCE = new Identifier(Lanthanum.MOD_ID,
            "textures/thirst/empty_thirst.png");

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.player != null && !client.player.getAbilities().creativeMode) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            int x = width / 2;
            // Get metalResistance value
            int metalResistance = ((IEntityDataSaver) client.player).getPersistentData().getInt("metalResistance");

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            // Draw metalResistance texture (empty)
            for (int i = 0; i < 10; i++) {
                RenderSystem.setShaderTexture(0, EMPTY_METAL_RESISTANCE);
                DrawableHelper.drawTexture(matrixStack, x - 94 + (i * 9), height - 54,
                        0, 0, 12, 12, 12, 12);
            }
            // Draw metalResistance texture (full)
            for (int i = 0; i < metalResistance; i++) {
                RenderSystem.setShaderTexture(0, FILLED_METAL_RESISTANCE);
                DrawableHelper.drawTexture(matrixStack, x - 94 + (i * 9), height - 54,
                        0, 0, 12, 12, 12, 12);
            }
        }
    }
}
