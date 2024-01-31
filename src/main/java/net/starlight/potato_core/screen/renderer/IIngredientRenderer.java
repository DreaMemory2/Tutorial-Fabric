package net.starlight.potato_core.screen.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

import java.util.List;

@Test(Result.UNKNOWN)
public interface IIngredientRenderer<T> {
    //
    default void render(MatrixStack context, T ingredient) {
        render(context, 0, 0, ingredient);
    }

    List<Text> getTooltip(T ingredient, TooltipContext tooltipContext);

    // 返回渲染的textRenderer
    default TextRenderer getFontRenderer(MinecraftClient client, T ingredient) {
        return client.textRenderer;
    }

    // 渲染的宽度
    default int getWidth() {
        return 16;
    }

    // 渲染的高度
    default int getHeight() {
        return 16;
    }

    default void render(MatrixStack context, int xPosition, int yPosition, T ingredient) {
    }

}
