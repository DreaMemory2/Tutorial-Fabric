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
 * <p>绘制文字</p>
 * <p>创建mixin类，将InGameHud类注入到InGameHudMinxin类中</p>
 * @author Stralight
 * @since 1.0
 */
@Mixin(InGameHud.class)
public class InGameHudMinxin {
    /**
     * <p>Invoke代表在指定方法调用时注入</p>
     * <p>at注解有个target属性：传入方法(方法名称和秒速)</p>
     * <p>at注解有个ordinal属性：输入序号，指定第几个方法调用时注入;这里时从0开始，所以第五个方法设置为4</p>
     * @see Logo#draw(MatrixStack matrices)
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V", ordinal = 4))
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        FirstMod.modManager.getIsEnables().forEach(it -> it.draw(matrices));
    }
}
