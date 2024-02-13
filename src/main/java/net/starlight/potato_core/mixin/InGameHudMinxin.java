package net.starlight.potato_core.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.starlight.potato_core.FirstMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.starlight.potato_core.mod.Logo;

/**
 * <p>绘制窗口内右上角文字，取名为Better Minecraft</p>
 * <p>render方法中对于游戏中界面进行注入，在绘制每个界面时，调用一个enableBlend方法</p>
 * <p>enableBlend方法可当作注入点：最后绘制的会在最上面，并且不能覆盖加载完的列表和对话框</p>
 * <p>因此：在最后一个enableBlend方法前注入，然后进行绘制</p>
 * <p>在InGameHudMixin类上写{@link Mixin}注解并调用{@link InGameHud}类</p>
 * <p>创建mixin类，将InGameHud类注入到InGameHudMinxin类中</p>
 * @author Stralight
 * @since 1.0
 */
// @Mixin(InGameHud.class)
public class InGameHudMinxin {
    /**
     * <p>Invoke代表在指定方法调用时注入</p>
     * <p>at注解有个target属性：传入方法(方法名称和描述)</p>
     * <p>但，这个方法里有很多目标方法，默认会在所有的指定方法调用时注入</p>
     * <p>at注解有个ordinal属性：输入序号，指定第几个方法调用时注入;这里时从0开始，所以第五个方法设置为4</p>
     * @see Logo#draw(MatrixStack matrices) 绘制窗口内右上角文字的方法
     */
    // @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V", ordinal = 4))
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        // 调用draw方法来进行绘制
        FirstMod.modManager.getIsEnables().forEach(it -> it.draw(matrices));
    }
}
