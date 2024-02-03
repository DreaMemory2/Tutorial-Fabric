package net.starlight.potato_core.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EnderPearl extends Item {

    public EnderPearl(Settings settings) {
        super(settings);
    }

    /**
     * 玩家右键物品会播放"末影珍珠飞出"的声音
     * @param world 目前世界上播放声音
     * @param user 玩家右键
     * @param hand 物品栏上的物品
     * @return 播放声音
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.2F);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
