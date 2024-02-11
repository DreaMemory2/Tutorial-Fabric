package net.starlight.potato_core.item.tool;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.starlight.potato_core.tool.ModToolMeterial;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiamondPickaxeItem extends PickaxeItem {
    public DiamondPickaxeItem() {
        super(ModToolMeterial.DIAMOND, 1, -2.8f, new FabricItemSettings());
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT) {
            // 耐久V、锋利V、效率V、时运V、经验修补II
            stack.addEnchantment(Enchantments.UNBREAKING, 5);
            stack.addEnchantment(Enchantments.SHARPNESS, 5);
            stack.addEnchantment(Enchantments.EFFICIENCY, 5);
            stack.addEnchantment(Enchantments.FORTUNE, 5);
            stack.addEnchantment(Enchantments.MENDING, 2);
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
        tooltip.add(Text.translatable("效率 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("时运 V").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("经验修补 II").formatted(Formatting.GRAY));
    }
}
