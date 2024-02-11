package net.starlight.potato_core.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * <p>Nbt标签的学习</p>
 */
public class DataTabletItem extends Item {
    public DataTabletItem(Settings settings) {
        super(settings);
    }

    /**
     * <p>使用物品对方块时，获取Nbt数据</p>
     * @param world the world the item was used in
     * @param user the player who used the item
     * @param hand the hand used
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // 获取玩家手上的物品是否有Nbt
        if (user.getStackInHand(hand).hasNbt()) {
            // 如果有Nbt标签，则清空Nbt数据
            user.getStackInHand(hand).setNbt(new NbtCompound());
            // 则在玩家手里的物品上创建NBt
        }
        return super.use(world, user, hand);
    }

    /**
     * <p>添加物品附魔效果</p>
     * <p>如果Nbt标签，则赋予物品附魔效果，表示物品已经获得Nbt标签</p>
     * @param stack 物品
     * @return 判断是否带有附魔效果
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        // 如果Nbt标签，则赋予物品附魔效果
        return stack.hasNbt();
    }

    /**
     * <p>添加物品提示信息</p>
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        // 获取玩家手上的物品是否有Nbt
        if (stack.hasNbt()) {
            // 获取当前方块的Nbt数据，通过key来获取
            String block = stack.getNbt().getString("potato_core.last.block");
            // 添加提示信息
            tooltip.add(Text.literal(block));
        }
    }
}
