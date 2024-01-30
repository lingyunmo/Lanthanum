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
            "textures/metal/filled_metal_resistance.png");
    private static final Identifier EMPTY_METAL_RESISTANCE = new Identifier(Lanthanum.MOD_ID,
            "textures/metal/empty_metal_resistance.png");

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.player != null && !client.player.getAbilities().creativeMode) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            int x = width / 2;
            // Get metalResistance
            int metalResistance = ((IEntityDataSaver) client.player).getPersistentData().getInt("metalResistance");

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            // Draw metalResistance texture
            drawTexture(matrixStack, x, height, EMPTY_METAL_RESISTANCE, 10);
            drawTexture(matrixStack, x, height, FILLED_METAL_RESISTANCE, metalResistance);
        }
    }

    private void drawTexture(MatrixStack matrixStack, int x, int height, Identifier texture, int count) {
        RenderSystem.setShaderTexture(0, texture);
        for (int i = 0; i < count; i++) {
            DrawableHelper.drawTexture(matrixStack, x + 9 + (i * 9), height - 58,
                    0, 0, 10, 10, 10, 10);
        }
    }
}