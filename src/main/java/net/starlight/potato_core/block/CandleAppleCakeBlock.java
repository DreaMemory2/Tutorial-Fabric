package net.starlight.potato_core.block;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.starlight.potato_core.register.ModBlocks;

import java.util.Map;

public class CandleAppleCakeBlock extends CandleCakeBlock {
    /** 把蜡烛插入蛋糕上，转变成蜡烛蛋糕 */
    private static final Map<Block, CandleAppleCakeBlock> CAKES_TRANSFORM = Maps.newHashMap();

    public CandleAppleCakeBlock(Block candle) {
        super(candle, FabricBlockSettings.copyOf(ModBlocks.APPLE_CAKE));
        CAKES_TRANSFORM.put(candle, this);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // 获取物品栏上物品
        ItemStack itemStack = player.getStackInHand(hand);
        // 判断物品是否为打火石或火焰弹
        if(itemStack.isOf(Items.FLINT_AND_STEEL) || itemStack.isOf(Items.FIRE_CHARGE)) return ActionResult.PASS;
        // 当蜡烛没有点燃且玩家物品栏不空
        if (!(CandleAppleCakeBlock.isHittingCandle(hit) && player.getStackInHand(hand).isEmpty() && state.get(LIT))) {
            // 返回结果：尝试吃蛋糕
            ActionResult result = AppleCake.tryEat(world, pos, ModBlocks.APPLE_CAKE.getDefaultState(), player);
            if (result.isAccepted()) CandleCakeBlock.dropStacks(state, world, pos);
            return result;
        }
        // 蜡烛熄灭
        CandleCakeBlock.extinguish(player, state, world, pos);
        return ActionResult.success(world.isClient);
    }

    /**
     * <p>判断是否已经点燃蜡烛</p>
     */
    private static boolean isHittingCandle(BlockHitResult hitResult) {
        return hitResult.getPos().y - (double)hitResult.getBlockPos().getY() > 0.5;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModBlocks.APPLE_CAKE);
    }
    /**
     * <p>把蜡烛插入蛋糕上，转变成蜡烛蛋糕</p>
     */
    public static BlockState getCandleCakeFromCandle(Block candle) {
        return CAKES_TRANSFORM.get(candle).getDefaultState();
    }
}
