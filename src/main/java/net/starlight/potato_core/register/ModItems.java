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
import net.starlight.potato_core.armor.NetherArmorMeterial;
import net.starlight.potato_core.item.*;
import net.starlight.potato_core.item.tool.*;
import net.starlight.potato_core.sound.Sounds;
import net.starlight.potato_core.tool.NetherToolMeterial;

/**
 * Chicken soup 鸡汤[使用后的效果：中毒V 00:10 反胃II 00:10]
 * StatusEffectInstance(StatusEffect type, int duration, int amplifier)     给予物品的效果buff
 * 其中statusEffect是状态效果的ID, duration是状态效果的持续时间单位tick, amplifier是增益状态效应
 * 0表示I, 1表示II...没有-1，如果int amplifier = -1则会选择默认值0
 * 100tick = 5s 1s = 20tick,则10s = 200tick
 */
public class ModItems {
    public static final Item CHICKEN_SOUP = registerItem("chicken_soup", new ChickenSoup(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(2).saturationModifier(2.0F).alwaysEdible()
                    .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 4), 1.0F)
                    .build())));
    public static final Item MUSIC_CARD = registerItem("music_card", new MusicCard(new FabricItemSettings()));
    public static final Item RAP_BASKETBALL = registerItem("rap_basketball", new RapBasketball(new FabricItemSettings().maxCount(1)));
    public static final Item SMALL_BLACK = registerItem("small_black", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(2).saturationModifier(2.0F).alwaysEdible().build())));
    public static final Item KUN_BOOK = registerItem("kun_book", new KunBook(new FabricItemSettings().maxCount(1)));
    public static final Item COLORS_BLACK = registerItem("colors_black", new ColorsBlack(new FabricItemSettings()));
    public static final Item CORN = registerItem("corn", new Item(new FabricItemSettings().food(FoodComponents.POTATO)));
    public static final Item CORN_SEEDS = registerItem("corn_seeds",new BlockItem(ModBlocks.CORN, new FabricItemSettings()));
    // 锭、装甲、武器
    public static final Item GOLD_NETHERITE = registerItem("gold_upgraded_netherite_ingot", new Item(new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_HELMET = registerItem("gold_upgraded_netherite_helmet", new ArmorItem(NetherArmorMeterial.GOLDERITE,
            EquipmentSlot.HEAD, new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_CHESTPLATE = registerItem("gold_upgraded_netherite_chestplate", new ArmorItem(NetherArmorMeterial.GOLDERITE,
            EquipmentSlot.CHEST, new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_LEGGINGS = registerItem("gold_upgraded_netherite_leggings", new ArmorItem(NetherArmorMeterial.GOLDERITE,
            EquipmentSlot.LEGS, new FabricItemSettings()));
    public static final Item GOLD_NETHERITE_BOOTS = registerItem("gold_upgraded_netherite_boots", new ArmorItem(NetherArmorMeterial.GOLDERITE,
            EquipmentSlot.FEET, new FabricItemSettings()));
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
    public static final Item FLAME_FLINT = registerItem("flame_flint", new FlameFlintItem(new FabricItemSettings()));

    public static void addItems() {
        addItemGroup(CHICKEN_SOUP);
        addItemGroup(MUSIC_CARD);
        addItemGroup(RAP_BASKETBALL);
        addItemGroup(SMALL_BLACK);
        addItemGroup(KUN_BOOK);
        addItemGroup(COLORS_BLACK);
        addItemGroup(CORN);
        addItemGroup(CORN_SEEDS);
        addItemGroup(GOLD_NETHERITE);
        addItemGroup(GOLD_NETHERITE_HELMET);
        addItemGroup(GOLD_NETHERITE_CHESTPLATE);
        addItemGroup(GOLD_NETHERITE_LEGGINGS);
        addItemGroup(GOLD_NETHERITE_BOOTS);
        addItemGroup(GOLD_NETHERITE_SWORD);
        addItemGroup(GOLD_NETHERITE_SHOVEL);
        addItemGroup(GOLD_NETHERITE_PICKAXE);
        addItemGroup(GOLD_NETHERITE_AXE);
        addItemGroup(GOLD_NETHERITE_HOE);
        addItemGroup(FLAME_FLINT);

        addItemGroup(Sounds.GALAXY_ITEM);
    }

    private static void addItemGroup(Item item){
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.ITEMS).register(entries -> entries.add(item));
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FirstMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FirstMod.LOGGER.debug("Registering Mod Items for" + FirstMod.MOD_ID);
        addItems();
    }
}