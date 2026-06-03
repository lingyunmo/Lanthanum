package cn.bzlom.lanthanum.screen;

import cn.bzlom.lanthanum.Lanthanum;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CrusherScreen extends AbstractContainerScreen<CrusherScreenHandler> {

    private static final ResourceLocation GUI_TEXTURE =
            new ResourceLocation(Lanthanum.MOD_ID, "textures/gui/crusher_gui.png");
    private static final ResourceLocation ENERGY_BAR =
            new ResourceLocation(Lanthanum.MOD_ID, "textures/gui/energy_bar_fill.png");
    private static final ResourceLocation PROGRESS_ARROW =
            new ResourceLocation(Lanthanum.MOD_ID, "textures/gui/progress_arrow_fill.png");

    // Texture sizes ──────────────────────────────────────
    private static final int GUI_W = 176, GUI_H = 166;
    private static final int ENERGY_BAR_W = 12, ENERGY_BAR_H = 60;   // energy_bar_fill.png
    private static final int PROGRESS_W = 40, PROGRESS_H = 12;       // progress_arrow_fill.png

    // Render positions ───────────────────────────────────
    private static final int ENERGY_X = 153, ENERGY_Y = 8;
    private static final int PROGRESS_X = 69, PROGRESS_Y = 37;

    public CrusherScreen(CrusherScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
        this.imageWidth = GUI_W;
        this.imageHeight = GUI_H;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        // Background — explicit texW/texH to avoid UV miscalculation
        graphics.blit(GUI_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, GUI_W, GUI_H);

        // Energy bar — fills bottom-up
        int energyHeight = this.menu.getScaledEnergy(ENERGY_BAR_H);
        if (energyHeight > 0) {
            int srcY = ENERGY_BAR_H - energyHeight;
            int dstY = topPos + ENERGY_Y + ENERGY_BAR_H - energyHeight;
            graphics.blit(ENERGY_BAR,
                    leftPos + ENERGY_X, dstY,
                    0, srcY,
                    ENERGY_BAR_W, energyHeight,
                    ENERGY_BAR_W, ENERGY_BAR_H);
        }

        // Progress arrow — fills left to right
        int progressWidth = this.menu.getScaledProgress(PROGRESS_W);
        if (progressWidth > 0) {
            graphics.blit(PROGRESS_ARROW,
                    leftPos + PROGRESS_X, topPos + PROGRESS_Y,
                    0, 0,
                    progressWidth, PROGRESS_H,
                    PROGRESS_W, PROGRESS_H);
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        renderTooltip(graphics, mouseX, mouseY);

        if (isMouseOverEnergyBar(mouseX, mouseY)) {
            graphics.renderTooltip(font,
                    Component.literal(this.menu.getEnergy() + " / " + this.menu.getMaxEnergy() + " FE"),
                    mouseX, mouseY);
        }
    }

    private boolean isMouseOverEnergyBar(int mouseX, int mouseY) {
        return mouseX >= leftPos + 152 && mouseX <= leftPos + 166
                && mouseY >= topPos + 8 && mouseY <= topPos + 68;
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawString(font, title, titleLabelX, titleLabelY, 0x404040, false);
        graphics.drawString(font, playerInventoryTitle, inventoryLabelX, inventoryLabelY, 0x404040, false);
    }
}
