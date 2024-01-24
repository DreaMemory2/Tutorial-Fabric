package net.starlight.potato_core.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Keyboard;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.screen.handler.KunBookScreenHandler;

/**
 * Forge与Fabric的关系
 * Component相当于Text
 * PoseStack相当于MatrixStack
 *
 * // 方法区别
 * Forge:renderTooltip(PoseStack poseStack, int x, int y).
 * Fabric:drawMouseoverTooltip(MatrixStack matrices, int x, int y).
 */
public class KunBookScreen extends HandledScreen<KunBookScreenHandler> {
    public final World world;
    public final PlayerEntity entity;
    public int myX;
    public int myY;
    public int myZ;
    private static final Identifier TEXTURE = new Identifier(FirstMod.MOD_ID, "textures/gui/kun_book_gui.png");

    public KunBookScreen(KunBookScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.world = handler.world;
        this.myX = handler.x;
        this.myY = handler.y;
        this.myZ = handler.z;
        this.entity = handler.entity;
        this.backgroundWidth = 100;
        this.backgroundHeight = 100;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    public void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, this.x -23, this.y -44, 0.0F , 0.0F, 146, 180, 146, 180);
        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(keyCode == 256){
            if (this.client != null && this.client.player != null) {
                this.client.player.closeHandledScreen();
            }
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void handledScreenTick() {
        super.handledScreenTick();
    }

    @Override
    public void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        this.textRenderer.draw(matrices, "\u4F60\u5E72\u561B~~", -11, -28, -12829636);
        this.textRenderer.draw(matrices, "\u9E21\u4F60\u592A\u7F8E", -11, -16, -12829636);
    }

    @Override
    public void close() {
        super.close();
        new Keyboard(this.client);
        // new KeyboardClass(this.client).setSendRepeatsToGui(false);
    }

    @Override
    public void init() {
        super.init();
        new Keyboard(this.client);
        // new KeyboardClass(this.client).setSendRepeatsToGui(true);
    }
}
