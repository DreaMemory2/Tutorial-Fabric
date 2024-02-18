package net.starlight.potato_core.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * <p>实现无限不死图腾的效果</p>
 * @author Stalight
 * @since 1.0
 */
@Mixin(LivingEntity.class)
public abstract class InfiniteItemMixin extends Entity {
    public InfiniteItemMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /**
     * <p>设置给予玩家的生命值</p>
     * @param health 生命值
     */
    @Shadow
    public abstract void setHealth(float health);

    /**
     * <p>获取玩家手持的物品</p>
     * @param hand 玩家手持的物品
     */
    @Shadow
    public abstract ItemStack getStackInHand(Hand hand);

    @Shadow
    public abstract boolean clearStatusEffects();

    /**
     * <p>添加使用物品后的效果</p>
     * @param effect 类似药水的效果
     */
    @Shadow
    public final boolean addStatusEffect(StatusEffectInstance effect) {
        return this.addStatusEffect(effect, null);
    }

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source);

    /**
     * <p>灵感来源Mafuyu33</p>
     * @author Stalight
     * @reason Change the totem of undying code
     */
    @Overwrite
    private boolean tryUseTotem(DamageSource source) {
        if (source.isIndirect()) return false;
        else {
            ItemStack stack = null;
            Hand[] var4 = Hand.values();
            int var5 = var4.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Hand hand = var4[var6];
                ItemStack itemStack = this.getStackInHand(hand);
                if (itemStack.isOf(Items.TOTEM_OF_UNDYING)) {
                    stack = itemStack.copy();
                    break;
                }
            }

            if (stack != null) {
                this.setHealth(1.0f);
                this.clearStatusEffects();
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
                this.world.sendEntityStatus(this, EntityStatuses.USE_TOTEM_OF_UNDYING);
            }
            return stack != null;
        }
    }
}
