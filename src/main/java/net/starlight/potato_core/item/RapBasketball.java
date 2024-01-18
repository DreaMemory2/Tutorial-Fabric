package net.starlight.potato_core.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.starlight.potato_core.sound.Sounds;

public class RapBasketball extends Item {
    public RapBasketball(Settings settings) {
        super(settings);
    }
    // 唱rap篮球的kun, 冷去时间200tick
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), Sounds.BEAUTIFUL_CHICKEN, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        user.getItemCooldownManager().set(this, 480);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
