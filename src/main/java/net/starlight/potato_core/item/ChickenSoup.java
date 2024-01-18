package net.starlight.potato_core.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChickenSoup extends Item {
    public ChickenSoup(Settings settings) {
        super(settings);
    }
    // 添加标签
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("喝个鸡汤多是一件美事啊").formatted(Formatting.RED));
    }
}
