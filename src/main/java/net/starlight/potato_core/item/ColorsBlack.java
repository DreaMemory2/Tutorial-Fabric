package net.starlight.potato_core.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.starlight.potato_core.util.ColorUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ColorsBlack extends Item {
    public ColorsBlack(Settings settings) {
        super(settings);
    }
    // 物品名
    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(ColorUtil.makeColour01("五彩斑斓的黑"));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        for (int i = 0; i < 10; i++) {
            tooltip.add(Text.translatable(ColorUtil.makeColour01("五彩斑斓的黑~~")));
        }
    }
}
