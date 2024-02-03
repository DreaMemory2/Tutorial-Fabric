package net.starlight.potato_core.register;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.effect.FreshStatusEffect;

public class ModEffect {
    public static final StatusEffect FRESH = new FreshStatusEffect();
    /**
     * 自定义药水瓶子，效果时间为45秒
     */
    public static final Potion FRESH_POTION = register("fresh", new Potion(new StatusEffectInstance(FRESH, 900)));
    /**
     * 延长版药水 效果时间为1分30秒
     */
    public static final Potion LONG_FRESH_POTION = register("long_fresh", new Potion(new StatusEffectInstance(FRESH, 1800)));
    /**
     * 加强版药水 效果时间为45秒, 药水等级为2
     */
    public static final Potion STRONG_FRESH_POTION = register("strong_fresh", new Potion(new StatusEffectInstance(FRESH, 900, 1)));

    public static void registerModEffects() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(FirstMod.MOD_ID, "fresh"), FRESH);
    }

    private static Potion register(String name, Potion potion) {
        return Registry.register(Registries.POTION, name, potion);
    }
}
