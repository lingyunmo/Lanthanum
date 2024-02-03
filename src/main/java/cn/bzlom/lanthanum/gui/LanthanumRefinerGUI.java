package cn.bzlom.lanthanum.gui;

import cn.bzlom.lanthanum.block.entity.LanthanumRefinerBlockEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import reborncore.client.gui.builder.GuiBase;
import reborncore.common.screen.BuiltScreenHandler;

public class LanthanumRefinerGUI extends GuiBase<BuiltScreenHandler> {
    LanthanumRefinerBlockEntity blockEntity;

    public LanthanumRefinerGUI(PlayerEntity player, LanthanumRefinerBlockEntity blockEntity) {
        super(player,blockEntity,null);
        this.blockEntity = blockEntity;
    }

    @Override
    protected void drawBackground(MatrixStack matrixStack, final float f, final int mouseX, final int mouseY) {
    }

    @Override
    public void drawForeground(MatrixStack matrixStack, final int mouseX, final int mouseY) {
        final GuiBase.Layer layer = GuiBase.Layer.FOREGROUND;
        this.builder.drawMultiEnergyBar(matrixStack, this, 9, 19, (int) blockEntity.energyStorage.amount,
                (int) blockEntity.energyStorage.capacity, mouseX, mouseY, 0, layer);
    }
}
