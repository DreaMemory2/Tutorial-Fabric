package net.starlight.potato_core.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.screen.handler.KunBookScreenHandler;

/**
 * Forge与Fabric的关系
 * Component相当于Text
 * PoseStack相当于MatrixStack
 * <p>
 * // 方法区别
 * Forge:renderTooltip(PoseStack poseStack, int x, int y).
 * Fabric:drawMouseoverTooltip(MatrixStack matrices, int x, int y).
 */
public class KunBookScreen extends HandledScreen<KunBookScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(FirstMod.MOD_ID, "textures/gui/kun_book_gui.png");

    public KunBookScreen(KunBookScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
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
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0.0F, 0.0F, 146, 180, 146, 180);
    }

    @Override
    public void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        this.textRenderer.draw(matrices, "\u4F60\u5E72\u561B~~", -11, -28, -12829636);
        this.textRenderer.draw(matrices, "\u9E21\u4F60\u592A\u7F8E", -11, -16, -12829636);
    }
}
