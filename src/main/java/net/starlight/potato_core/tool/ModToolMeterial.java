package net.starlight.potato_core.tool;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import net.starlight.potato_core.register.ModItems;

import java.util.function.Supplier;

public enum ModToolMeterial implements ToolMaterial {
    GOLDERITE(MiningLevels.NETHERITE, 2031, 9.0f, 4.0f, 15, () -> Ingredient.ofItems(ModItems.GOLD_NETHERITE)),
    DIAMOND(MiningLevels.NETHERITE, -1, 9.0f, 4.0f, 15, () -> Ingredient.ofItems(Items.DIAMOND));
    /** 挖掘等级 */
    private final int miningLevel;
    /** 物品耐久性 */
    private final int itemDurability;
    /** 挖掘速度或攻击速度 */
    private final float miningSpeed;
    /** 攻击伤害 */
    private final float attackDamage;
    /** 附魔能力 */
    private final int enchantability;
    /** 修理材料 */
    private final Lazy<Ingredient> repairIngredient;

    ModToolMeterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<>(repairIngredient);
    }
    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    /**
     * <p>所需要的修复材料</p>
     */
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.GOLD_NETHERITE);
    }
}
