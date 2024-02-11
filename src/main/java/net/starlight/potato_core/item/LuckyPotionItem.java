package net.starlight.potato_core.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LuckyPotionItem extends Item {
    public LuckyPotionItem() {
        super(new Item.Settings().maxCount(1).food(drink()));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    private static FoodComponent drink() {
        return new FoodComponent.Builder().alwaysEdible()
                .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,
                        3600, 0), 1.0f) // 3分钟 防火
                .statusEffect(new StatusEffectInstance(StatusEffects.SPEED,
                        7480, 0), 1.0f) // 6分14秒 速度
                .statusEffect(new StatusEffectInstance(StatusEffects.HASTE,
                        3240, 1), 1.0f) // 2分42秒 急迫 II
                .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,
                        6800, 1), 1.0f) // 5分40秒 力量 II
                .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST,
                        9580, 1), 1.0f) // 7分59秒 跳跃提升 II
                .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,
                        6760, 1), 1.0f) // 5分38秒 生命恢复 II
                .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,
                        4880, 0), 1.0f) // 4分04秒 防火
                .statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING,
                        4440, 0), 1.0f) // 3分42秒 水下呼吸
                .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION,
                        7640, 0), 1.0f) // 6分22秒 夜视
                .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST,
                        6540, 2), 1.0f) // 5分27秒 生命提升 III
                .statusEffect(new StatusEffectInstance(StatusEffects.SATURATION,
                        4600, 1), 1.0f) // 3分50秒 饱和 II
                .build();
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("防火 (3:00)").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("速度 (6:14))").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("急迫 II (2:42)").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("力量 II (5:40)").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("跳跃提升 II (7:59)").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("生命恢复 II (5:38)").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("防火 (4:04)").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("水下呼吸 (3:42)").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("夜视 (6:22)").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("生命提升 III (5:27)").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("饱和 II (3:50)").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable(" ").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("当生效后:").formatted(Formatting.DARK_PURPLE));
        tooltip.add(Text.translatable("+20% 速度").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("+20% 攻击速度").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("+6 攻击伤害").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("+12 最大生命值").formatted(Formatting.BLUE));
    }
}
