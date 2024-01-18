package net.starlight.potato_core.mixin;

import net.minecraft.client.MinecraftClient;
import net.starlight.potato_core.FirstMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * <p>修改游戏标题</p>
 * <p>需要在游戏窗口之后，获取Minecraft窗口对象，调用其中的设置标题方法即可设置游戏标题</p>
 * <p>需要借助mixin来修改，在类的上面添加一个mixin注解，传入MincraftClient类</p>
 * <p>将mixin类添加到mixin的配置文件中，其中有client列表和mixins列表</p>
 * <p>client列表里面只会修改client环境下的类；mixins列表里面client和server环境都行</p>
 * @author stalight
 * @since 1.0.0
 */
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    /**
     * <p>将游戏标题修改自己游戏标题</p>
     * <p>给方法添加一个inject注解，说明方法修改为类里面的方法</p>
     * <p>注解中有一个method属性，这个属性就是方法名字，将要被修改的方法名字填进去</p>
     * <p>注解中有一个at属性，说明就是代码注入到对应方法的哪个位置上，HEAD方法的最开始处</p>
     */
    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    public void getWindowTitle(CallbackInfoReturnable<String> cir){
        // 调用回调参数里的setReturnValue来设置返回的值
        // 调用回调方法后，需要在注解中添加cancellable属性为true
        // 这样就会在方法注入的位置，直接返回设置的值，返回窗口的标题就可以
        cir.setReturnValue("Better Minecraft 1.0");
    }

    /**
     * <p>在mixin中注入游戏启动的方法</p>
     */
    @Inject(method = "run", at = @At("HEAD"))
    public void run(CallbackInfo ci) {
        FirstMod.run();
    }

    @Inject(method = "stop", at = @At("HEAD"))
    public void stop(CallbackInfo ci) {
        FirstMod.stop();
    }
}
