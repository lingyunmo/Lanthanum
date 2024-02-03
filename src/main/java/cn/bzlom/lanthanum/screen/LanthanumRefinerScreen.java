package cn.bzlom.lanthanum.screen;

import cn.bzlom.lanthanum.Lanthanum;
import cn.bzlom.lanthanum.screen.renderer.EnergyInfoArea;
import cn.bzlom.lanthanum.utils.MouseUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class LanthanumRefinerScreen extends HandledScreen<LanthanumRefinerScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(Lanthanum.MOD_ID, "textures/gui/lanthanum_refiner_block_gui.png");
    private EnergyInfoArea energyInfoArea;
    //    private FluidStackRenderer fluidStackRenderer;


    public LanthanumRefinerScreen(LanthanumRefinerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        assignEnergyInfoArea();
//        assignFluidStackRenderer();
    }

    //    private void assignFluidStackRenderer(){
//        fluidStackRenderer = new FluidStackRenderer(FluidStack.convertDropletsToMb(FluidConstants.BUCKET) * 20,
//                true,15,61);
//    }
//
    private void assignEnergyInfoArea() {
        energyInfoArea = new EnergyInfoArea(((width - backgroundWidth) / 2) + 156,
                ((height - backgroundHeight) / 2) + 13, handler.blockEntity.energyStorage);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
//        renderProgressArrow(matrices,x,y);
//
//        RenderSystem.setShaderTexture(0, TEXTURE);
//        drawTexture(matrices,x,y,0,0,backgroundWidth,backgroundHeight);

        renderEnergyAreaTooltips(matrices, mouseX, mouseY, x, y);
//        renderFluidTooltip(context,mouseX,mouseY,x,y,handler.fluidStack,55,15,fluidStackRenderer);
    }

//    private void renderFluidTooltip(DrawContext context,int mouseX,int mouseY,int x,int y,
//                                    FluidStack fluidStack,int offsetX,int offsetY,FluidStackRenderer renderer){
//        if(isMouseAboveArea(mouseX,mouseY,x,y,offsetX,offsetY,renderer)){
//            // 绘制流体的提示信息
//            context.drawTooltip(textRenderer,renderer.getTooltip(fluidStack, TooltipContext.Default.BASIC),
//                    Optional.empty(),mouseX-x,mouseY-y);
//        }
//    }


    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderProgressArrow(matrices, x, y);
//        PlayerEntity player = null;
//        if (client != null) {
//            player = client.player;
//        }
//        LanthanumRefinerGUI lanthanumRefinerGUI = new LanthanumRefinerGUI(player,handler.blockEntity);
//        lanthanumRefinerGUI.drawForeground(matrices,mouseX,mouseY);

//        // 绘制流体slot
//        fluidStackRenderer.drawFluid(context,handler.fluidStack,x+55,y+15,16,61,
//                FluidStack.convertDropletsToMb(FluidConstants.BUCKET)*20);
    }

    private void renderEnergyAreaTooltips(MatrixStack matrices, int pMouseX, int pMouseY, int x, int y) {
        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 156, 13, 8, 64)) {
            renderTooltip(matrices, energyInfoArea.getTooltips(), pMouseX - x, pMouseY - y);
        }
    }

    private void renderProgressArrow(MatrixStack matrices, int x, int y) {
        if (handler.isCrafting()) {
            RenderSystem.setShaderTexture(0, TEXTURE);
            drawTexture(matrices, x + 105, y + 33, 176, 0, 8, handler.getScaledProgress());
        }
    }

    private boolean isMouseAboveArea(int MouseX, int MouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(MouseX, MouseY, x + offsetX, y + offsetY, width, height);
    }

//    private boolean isMouseAboveArea(int pMouseX,int pMouseY,int x,int y,int offsetX,int offsetY,FluidStackRenderer renderer){
//        return MouseUtil.isMouseOver(pMouseX,pMouseY,x+offsetX,y+offsetY,renderer.getWidth(),renderer.getHeight());
//    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}