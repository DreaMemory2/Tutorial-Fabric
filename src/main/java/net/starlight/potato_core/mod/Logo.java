package net.starlight.potato_core.mod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.starlight.potato_core.FirstMod;
import org.lwjgl.glfw.GLFW;

/**
 * <p>创建一个log模块类继承创建好的模块</p>
 * @author Stalight
 * @since 1.0
 */
public class Logo extends Mod{
    /**
     * <p>填完模块信息后，添加到ModManager类中</p>
     */
    public Logo() {
        super("Logo", Category.DRAW);
        // 在模块的构造方法中调用setKey方法来设置按键的代码
        // 按键代码可以调用GLFW获取，按键为M
        this.setKey(GLFW.GLFW_KEY_M);
    }

    /**
     * <p>draw方法：绘制窗口内右上角文字的方法</p>
     * <p>在注入的matrices中获取到所有已经启用的模块，调用每个模块中的主要方法</p>
     */
    @Override
    public void draw(MatrixStack matrices) {
        // 调用MinecraftClient类中的TextRender，可以获取文字的渲染对象
        // 调用其中的draw方法即可绘制文字
        // 每个绘制方法，都需要传入MatrixStack对象，接着传入要绘制文字，文字坐标，文字颜色
        MinecraftClient.getInstance().textRenderer.draw(matrices, "Better Minecraft", 0, 0, 0xFFFFFFFF);
    }

    /**
     * <p>启用enable方法</p>
     * <p>模块启用时打印日志</p>
     */
    @Override
    public void enable() {
        FirstMod.LOGGER.info("Logo Enable");
    }
    /**
     * <p>禁用disable方法</p>
     * <p>禁止使用时打印日志</p>
     */
    @Override
    public void disable() {
        FirstMod.LOGGER.info("Logo Disable");
    }
}
