package net.starlight.potato_core.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.armor.ModArmorMeterial;
import net.starlight.potato_core.item.*;
import net.starlight.potato_core.item.tool.*;

public class ModItems {
    /**
     * Chicken soup 鸡汤[使用后的效果：中毒V 00:10 反胃II 00:10]
     * StatusEffectInstance(StatusEffect type, int duration, int amplifier)     给予物品的效果buff
     * 其中statusEffect是状态效果的ID, duration是状态效果的持续时间单位tick, amplifier是增益状态效应
     * 0表示I, 1表示II...没有-1，如果int amplifier = -1则会选择默认值0
     * 100tick = 5s 1s = 20tick,则10s = 200tick
     */
    public static final Item CHICKEN_SOUP = registerItem("chicken_soup", new ChickenSoup(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(2).saturationModifier(2.0F).alwaysEdible()
                    .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 4), 1.0F)
                    .build())));
    public static final Item ENDER_PEARL = registerItem("ender_pearl", new EnderPearl(new FabricItemSettings()));
    public static final Item RAP_BASKETBALL = registerItem("rap_basketball", new RapBasketball(new FabricItemSettings().maxCount(1)));
    public static final Item SMALL_BLACK = registerItem("small_black", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(2).saturationModifier(2.0F).alwaysEdible().build())));
    public static final Item KUN_BOOK = registerItem("kun_book", new KunBook(new FabricItemSettings().maxCount(1)));
    public static final Item COLORS_BLACK = registerItem("colors_black", new ColorsBlack(new FabricItemSettings()));
    public static final Item CORN = registerItem("corn", new Item(new FabricItemSettings().food(FoodComponents.POTATO)));
    public static final Item CORN_SEEDS = registerItem("corn_seeds",new BlockItem(ModBlocks.CORN, new FabricItemSettings()));
    // 锭、装甲、武器
    public static final Item GOLD_NETHERITE_INGOT = registerItem("gold_upgraded_netherite_ingot", new Item(new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_HELMET = registerItem("gold_upgraded_netherite_helmet", new ArmorItem(ModArmorMeterial.GOLDERITE,
            ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_CHESTPLATE = registerItem("gold_upgraded_netherite_chestplate", new ArmorItem(ModArmorMeterial.GOLDERITE,
            ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_LEGGINGS = registerItem("gold_upgraded_netherite_leggings", new ArmorItem(ModArmorMeterial.GOLDERITE,
            ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_BOOTS = registerItem("gold_upgraded_netherite_boots", new ArmorItem(ModArmorMeterial.GOLDERITE,
            ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_SWORD = registerItem("gold_upgraded_netherite_sword", new GolderiteSword(
            3, -2.4f, new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_SHOVEL = registerItem("gold_upgraded_netherite_shovel", new GolderiteShovel(
            1.5f, -3.0f, new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_PICKAXE = registerItem("gold_upgraded_netherite_pickaxe", new GolderitePickaxe(
            1, -2.8f, new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_AXE = registerItem("gold_upgraded_netherite_axe", new GolderiteAxe(
            5.0f, -3.0f, new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_HOE = registerItem("gold_upgraded_netherite_hoe", new GolderiteHoe(
            -4, 0.0f, new FabricItemSettings()));
    // 未实现
    public static final Item FLAME_FLINT = registerItem("flame_flint", new FlameFlintItem(new FabricItemSettings()));
    /** 苹果汁：在饮料制作机中，用于把苹果合成苹果汁，实现自定义配方 */
    public static final Item APPLE_JUICE = registerItem("apple_juice", new Item(new FabricItemSettings()));
    // 染色球注册 失败
    // public static final Map<AEColor, PaintBallItem> PAINT_BALLS = PaintBallItem.create(false, "paint_ball");;
    // public static final Map<AEColor, PaintBallItem> LUMEN_PAINT_BALLS = PaintBallItem.create(true, "lumen");
    /** 创建数据平台电脑这个物品，用来收集Nbt数据 */
    public static final Item DATA_TABLET = registerItem("data_tablet", new DataTabletItem(new FabricItemSettings().maxCount(1)));
    /** 定位器，用来获取方块信息 */
    public static final Item DOWSING_ROD = registerItem("dowsing_rod", new DowsingRodItem(new FabricItemSettings().maxCount(1)));
    /** 1:1，还原幸运村民的交易 */
    public static final Item DIAMOND_HELMET = registerOtherItem("diamond_helmet", new DiamondArmorItem(ArmorItem.Type.HELMET));
    public static final Item DIAMOND_CHESTPLATE = registerOtherItem("diamond_chestplate", new DiamondArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final Item DIAMOND_LEGGINGS = registerOtherItem("diamond_leggings", new DiamondArmorItem(ArmorItem.Type.LEGGINGS));
    public static final Item DIAMOND_BOOTS = registerOtherItem("diamond_boots", new DiamondArmorItem(ArmorItem.Type.BOOTS));
    public static final Item DIAMOND_SWORD = registerOtherItem("diamond_sword", new DiamondSwordItem());
    public static final Item DIAMOND_PICKAXE = registerOtherItem("diamond_pickaxe", new DiamondPickaxeItem());
    public static final Item DIAMOND_AXE = registerOtherItem("diamond_axe", new DiamondAxeItem());
    public static final Item DIAMOND_SHOVEL = registerOtherItem("diamond_shovel", new DiamondShovelItem());
    // 幸运药水
    public static final Item LUCKY_POTION = registerItem("lucky_potion", new LuckyPotionItem());
    // 自定义钻石矛
    public static final Item DIAMOND_SPEAR = registerItem("diamond_spear", new DiamondSpearItem(7.0f, 1.1f));

    private static Item registerItem(String name, Item item) {
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.ITEMS).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, new Identifier(FirstMod.MOD_ID, name), item);
    }
    private static Item registerOtherItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FirstMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FirstMod.LOGGER.debug("Registering Mod Items for" + FirstMod.MOD_ID);
    }
}