package net.starlight.potato_core.item.tool;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.starlight.potato_core.util.GolderiteToolEnchantItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GolderitePickaxe extends PickaxeItem implements GolderiteToolEnchantItem {
    public GolderitePickaxe(int attackDamage, float attackSpeed, Settings settings) {
        super(ToolMaterials.NETHERITE, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("时运 X").formatted(Formatting.GRAY));
    }
}
