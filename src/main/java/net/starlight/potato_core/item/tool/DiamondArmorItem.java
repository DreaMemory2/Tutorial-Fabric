package net.starlight.potato_core.item.tool;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.starlight.potato_core.armor.ModArmorMeterial;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiamondArmorItem extends ArmorItem {
    public DiamondArmorItem(EquipmentSlot slot) {
        super(ModArmorMeterial.DIAMOND, slot, new Settings());
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT) {
            addEnchantment(stack);
            stack.addHideFlag(ItemStack.TooltipSection.ENCHANTMENTS);
            return true;
        } else if (clickType == ClickType.LEFT) {
            stack.setNbt(new NbtCompound());
        }
        return true;
    }

    private void  addEnchantment(ItemStack itemStack) {
        // 保护 V、火焰保护 V、摔落保护 V、 爆炸保护 V、弹射物保护 V、耐久 V、水下速掘 V
        itemStack.addEnchantment(Enchantments.PROTECTION, 5);
        itemStack.addEnchantment(Enchantments.UNBREAKING, 5);
        itemStack.addEnchantment(Enchantments.FIRE_PROTECTION, 5);
        itemStack.addEnchantment(Enchantments.FEATHER_FALLING, 5);
        itemStack.addEnchantment(Enchantments.BLAST_PROTECTION, 5);
        itemStack.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 5);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("保护 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("耐久 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("火焰保护 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("摔落保护 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("爆炸保护 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("弹射物保护 V").formatted(Formatting.GRAY));
    }
}
