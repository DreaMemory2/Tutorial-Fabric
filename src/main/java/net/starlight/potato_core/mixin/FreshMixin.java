package net.starlight.potato_core.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.starlight.potato_core.register.ModEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class FreshMixin {
    @Inject(at = @At("HEAD"), method = "registerDefaults")
    private static void registerDefaults(CallbackInfo ci) {
        // 合成药水
        FreshMixin.registerPotionRecipe(Potions.WATER, Items.OAK_LEAVES, ModEffect.FRESH_POTION);
        // 延长药水效果时间
        FreshMixin.registerPotionRecipe(ModEffect.FRESH_POTION, Items.REDSTONE, ModEffect.LONG_FRESH_POTION);
        // 加强药水效果等级
        FreshMixin.registerPotionRecipe(ModEffect.LONG_FRESH_POTION, Items.GLOWSTONE_DUST, ModEffect.STRONG_FRESH_POTION);
    }

    /**
     * @param input  所需要的药水
     * @param item   所需要的物品
     * @param output 输出的药水，合成后的药水
     */
    @Invoker("registerPotionRecipe")
    public static void registerPotionRecipe(Potion input, Item item, Potion output) {
    }
}
