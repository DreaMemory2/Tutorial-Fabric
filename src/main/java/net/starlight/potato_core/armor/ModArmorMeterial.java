package net.starlight.potato_core.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;
import net.starlight.potato_core.register.ModItems;

import java.util.function.Supplier;

public enum ModArmorMeterial implements ArmorMaterial {
    GOLDERITE("golderite", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0f, 0.1f, () -> Ingredient.ofItems(ModItems.GOLD_NETHERITE_INGOT)),
    DIAMOND("diamond", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0f, 0.1f, () -> Ingredient.ofItems(Items.DIAMOND));

    /** Base Durability 基本耐久性 */
    private static final int[] BASE_DURABILITY = new int[]{-1, -1, -1, -1};
    /** name 材料的名称 */
    private final String name;
    /** Durability Multiplier 耐久倍增器 */
    private final int durabilityMultiplier;
    /** Protection Amounts 保护值  */
    private final int[] protectionAmounts;
    /** Enchatability 附魔能力 */
    private final int enchantability;
    /** Equip Sound 装备声音 */
    private final SoundEvent equipSound;
    /** Toughness 盔甲韧性修饰符 */
    private final float toughness;
    /** Knockback Resistance 盔甲击退抗性修饰符 */
    private final float knockbackResistance;
    /** Repair Ingredient Supplier 修复的材料 */
    private final Lazy<Ingredient> repairIngredientSupplier;

    ModArmorMeterial(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = new Lazy<>(repairIngredientSupplier);
    }

    @Deprecated(forRemoval = true, since = "1.19.3")
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
    }

    @Deprecated(forRemoval = true, since = "1.19.3")
    public int getProtectionAmount(EquipmentSlot slot) {
        return this.protectionAmounts[slot.getEntitySlotId()];
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.getEquipmentSlot().getEntitySlotId()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return this.protectionAmounts[type.getEquipmentSlot().getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    /**
     * <p>所需要的修复材料</p>
     */
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.GOLD_NETHERITE_INGOT);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
