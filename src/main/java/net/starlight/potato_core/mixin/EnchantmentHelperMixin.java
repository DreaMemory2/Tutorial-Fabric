package net.starlight.potato_core.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.starlight.potato_core.util.IntrinsicEnchantItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "getLevel", at = @At("HEAD"), cancellable = true)
    private static void hookGetItemEnchantmentLevel(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (cir.getReturnValueI() == 0 && stack.getItem() instanceof IntrinsicEnchantItem item) {
            int level = item.getIntrinsicEnchantLevel(stack, enchantment);
            if (level != 0) {
                cir.setReturnValue(level);
            }
        }
    }
}
