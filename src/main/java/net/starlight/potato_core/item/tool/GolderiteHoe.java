package net.starlight.potato_core.item.tool;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.starlight.potato_core.tool.ModToolMeterial;
import net.starlight.potato_core.util.GolderiteToolEnchantItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GolderiteHoe extends HoeItem implements GolderiteToolEnchantItem {
    public GolderiteHoe(int attackDamage, float attackSpeed, Settings settings) {
        super(ModToolMeterial.GOLDERITE, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("时运 X").formatted(Formatting.GRAY));
    }
}
