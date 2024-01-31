package net.starlight.potato_core.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.screen.handler.DrinkMachineScreenHandler;

/**
 * <p>创建DrinkMachine相关界面[展示]</p>
 */
public class DrinkMachineScreen extends HandledScreen<DrinkMachineScreenHandler> {

    /**
     * 界面材质包
     */
    private static final Identifier TEXTURE = new Identifier(FirstMod.MOD_ID, "textures/gui/drink_machine.png");

    /**
     *
     * @param handler 界面处理器展现
     * @param inventory 界面库存展现
     * @param title 界面标题展现
     */
    public DrinkMachineScreen(DrinkMachineScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    /**
     * <p>初始化姐GUI界面</p>
     */
    @Override
    protected void init() {
        super.init();
        // 设置标题位置中间
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }

    /**
     * 设置GUI界面大小，位置
     * @param matrices Texture
     * @param mouseX 鼠标x轴的位置
     * @param mouseY 鼠标y轴的位置
     * @param delta delta
     */
    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        // 屏幕在正中间处
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        // 图片的其实位置和宽高
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        // 进度条的绘制
        renderProgress(matrices, x, y);
    }

    /**
     * @param matrices 材质
     * @param x x轴位置
     * @param y y轴位置
     */
    private void renderProgress(MatrixStack matrices, int x, int y) {
        // 判断是否处在合成状态
        if (this.handler.isCrafting()) {
            // 其中x,y表示进度条的起始位置，u,v表示进度条的图片起始位置，13表示进度条的图片宽度
            // 平直的进度条
            drawTexture(matrices, x + 58, y + 30, 176, 14, this.handler.getScaledProgress(), 17);
        }
    }

    /**
     * <p>渲染处理</p>
     */
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
