package net.starlight.potato_core.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;

public interface GolderiteToolEnchantItem extends IntrinsicEnchantItem {
    @Override
    default int getIntrinsicEnchantLevel(ItemStack stack, Enchantment enchantment) {
        // 使工具有时运十的初始附魔
        return enchantment == Enchantments.FORTUNE ? 10 : 0;
    }
}
