package net.starlight.potato_core.item.tool;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.starlight.potato_core.tool.ModToolMeterial;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiamondSwordItem extends SwordItem {
    public DiamondSwordItem() {
        super(ModToolMeterial.DIAMOND, 3, -2.4f, new FabricItemSettings());
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT) {
            // 耐久V、锋利V、抢夺V、亡灵杀手V、节肢杀手V、横扫之刃V、火焰附加II
            stack.addEnchantment(Enchantments.UNBREAKING, 5);
            stack.addEnchantment(Enchantments.SHARPNESS, 5);
            stack.addEnchantment(Enchantments.LOOTING, 5);
            stack.addEnchantment(Enchantments.SMITE, 5);
            stack.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 5);
            stack.addEnchantment(Enchantments.SWEEPING, 5);
            stack.addEnchantment(Enchantments.FIRE_ASPECT, 2);
            stack.addHideFlag(ItemStack.TooltipSection.ENCHANTMENTS);
            return true;
        } else if (clickType == ClickType.LEFT) {
            stack.setNbt(new NbtCompound());
        }
        return true;
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
        tooltip.add(Text.translatable("耐久 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("锋利 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("抢夺 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("亡灵杀手 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("节肢杀手 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("横扫之刃 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("火焰附加 II").formatted(Formatting.GRAY));
    }
}
