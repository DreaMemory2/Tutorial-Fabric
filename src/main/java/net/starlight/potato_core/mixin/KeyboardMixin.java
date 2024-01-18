package net.starlight.potato_core.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.starlight.potato_core.FirstMod;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * <p>完成模块按键功能</p>
 * <p>用mixin代码注入到游戏的按键方法中</p>
 * <p>在Keyboard类中有个onKey方法，这个方法获取到在游戏中按键的代码</p>
 * <p>在和模块列表中过的模块的按键代码属性去比较，就可以启用和禁止模块</p>
 */
@Mixin(Keyboard.class)
public class KeyboardMixin {
    /**
     * <p>调用游戏的当前的界面必须为No，并且onKey中的action参数必须为1</p>
     * <p>这样就只能下游戏中打开模块，并且只有按下按键的时候才触发按键事件</p>
     *
     */
    @Inject(method = "onKey", at = @At("HEAD"))
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (MinecraftClient.getInstance().currentScreen == null && action == GLFW.GLFW_PRESS) {
            FirstMod.modManager.onKey(key);
        }
    }
}
