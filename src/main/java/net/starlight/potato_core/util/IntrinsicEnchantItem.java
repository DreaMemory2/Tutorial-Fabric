package net.starlight.potato_core.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

/**
 * Allows items to have an "intrinsic" enchant level in places where a specific enchant is checked.
 */
@Test(Result.USELESS)
public interface IntrinsicEnchantItem {
    int getIntrinsicEnchantLevel(ItemStack stack, Enchantment enchantment);
}
