package net.starlight.potato_core.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FreshStatusEffect extends StatusEffect {
    /**
     * <p>药水效果是有益的还是有害的</p>
     * <p>药水粒子颜色显示的颜色</p>
     */
    public FreshStatusEffect() {
        // 设置效果的属性
        super(StatusEffectCategory.BENEFICIAL, 0x07bd1f);
    }

    /**
     * <p>这个方法在每个 tick 都会调用，以检查是否应应用药水效果</p>
     */
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // 在我们的例子中，为了确保每一 tick 药水效果都会被应用，我们只要这个方法返回 true 就行了
        return true;
    }
    /**
     * <p>这个方法在应用药水效果时会被调用，所以我们可以在这里实现自定义功能</p>
     */
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // 在这里实现自定义的药水效果效果
        // 例如，你可以在这里给实体添加属性或状态效果
        // 这里只是一个简单的示例，我们只是给实体添加一个生命回复效果 3分钟 等级3
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 3,
                false,false, false));
    }
}
