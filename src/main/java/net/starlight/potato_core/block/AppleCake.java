package net.starlight.potato_core.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

public class AppleCake extends CakeBlock {
    public AppleCake(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // 获得玩家指定的物品栏
        ItemStack itemStack = player.getStackInHand(hand);
        // 通过物品栏获取Cake物品
        Item item = itemStack.getItem();
        // 获取蜡烛
        Block candleBlock;
        // 判断物品是否为蜡烛
        if (itemStack.isIn(ItemTags.CANDLES) && state.get(BITES) == 0 && (candleBlock = Block.getBlockFromItem(item)) instanceof CandleBlock) {
            if (!player.isCreative()) itemStack.decrement(1);
            // 播放"Cake squishes"声音
            world.playSound(null, pos, SoundEvents.BLOCK_CAKE_ADD_CANDLE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.setBlockState(pos, CandleAppleCakeBlock.getCandleCakeFromCandle(candleBlock));
            // 触发游戏事件：方块状态更改
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            // 统计使用方块次数
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            return ActionResult.SUCCESS;
        }
        // 判断该方块是否可以使用，判断物品栏是否为空
        if (world.isClient) {
            if (AppleCake.tryEat(world, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }
            if (itemStack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }
        // 以上都满足，尝试吃蛋糕，吃蛋糕次数-1
        return AppleCake.tryEat(world, pos, state, player);
    }

    public static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) return ActionResult.PASS;
        player.incrementStat(Stats.EAT_CAKE_SLICE);
        player.getHungerManager().add(2, 0.1f);
        int i = state.get(BITES);
        world.emitGameEvent(player, GameEvent.EAT, pos);
        if (i < 6) {
            world.setBlockState(pos, state.with(BITES, i + 1), Block.NOTIFY_ALL);
        } else {
            world.removeBlock(pos, false);
            world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }
        return ActionResult.SUCCESS;
    }
}
