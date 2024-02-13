package net.starlight.potato_core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.starlight.potato_core.register.ModBlocks;
import net.starlight.potato_core.register.ModItems;

public class ModLanguageGenerator extends FabricLanguageProvider {
    public ModLanguageGenerator(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        // 方块语言配置
        builder.add(ModBlocks.WOOL_1X, "1 Compressed Wool");
        builder.add(ModBlocks.WOOL_2X, "2 Compressed Wool");
        builder.add(ModBlocks.WOOL_3X, "3 Compressed Wool");
        builder.add(ModBlocks.WOOL_4X, "4 Compressed Wool");
        builder.add(ModBlocks.WOOL_5X, "5 Compressed Wool");
        builder.add(ModBlocks.WOOL_6X, "6 Compressed Wool");
        builder.add(ModBlocks.WOOL_7X, "7 Compressed Wool");
        builder.add(ModBlocks.WOOL_8X, "8 Compressed Wool");
        builder.add(ModBlocks.WOOL_9X, "§C9 Compressed Wool");
        builder.add(ModBlocks.WHITE_PLANKS, "White Planks");
        builder.add(ModBlocks.WHITE_PLANK_STAIRS, "White Planks Stairs");
        builder.add(ModBlocks.DRINK_MACHINE, "Drink Machine");
        builder.add(ModBlocks.APPLE_CAKE, "Apple Cake");
        builder.add(ModBlocks.CORN, "Corn Seeds");
        builder.add(ModBlocks.IRON_LOG, "Iron Log");
        builder.add(ModBlocks.IRON_LEAVES, "Iron Leaves");
        builder.add(ModBlocks.IRON_SAPLING, "Iron Sapling");
        builder.add(ModBlocks.STONE, "Biome Stone");
        builder.add(ModBlocks.CRYSAL_GRASS_BLOCK, "Crystal Grass");
        builder.add(ModBlocks.CRYSAL_DIRT, "Crystal Dirt");

        // 物品语言配置
        builder.add(ModItems.CHICKEN_SOUP, "Chincken Soup");
        builder.add(ModItems.APPLE_JUICE, "Apple Juice");
        builder.add(ModItems.LUCKY_POTION, "Lucky Potion");
        builder.add(ModItems.FLAME_FLINT, "Flame Fire");
        builder.add(ModItems.DOWSING_ROD, "Dowsing Rod");
        builder.add(ModItems.CORN, "Corn");
        // 翻译组
        generateToolAndArmorTranslations(builder);
        // 其他翻译信息
        builder.add("item.potato_core.galaxy_item.desc", "Galaxy-2023");
        builder.add("itemGroup.potato_core.potato_item", "Potato Core Item");
        builder.add("itemGroup.potato_core.potato_block", "Potato Core Block");
        builder.add("itemGroup.potato_core.potato_building", "Potato Core Building");
        builder.add("potato_core.tootip.liquid.amount", "%s MB");
        builder.add("potato_core.tooltip.liquid.amount.with.capacity", "%s / %s MB");
        // 其他标签
        builder.add("entity.minecraft.villager.blue_worker", "§9Lucky Villager");
    }
    private void generateToolAndArmorTranslations(TranslationBuilder builder) {
        builder.add(ModItems.GOLD_NETHERITE, "Gold Upgraded Netherite Ingot");
        builder.add(ModItems.GOLD_NETHERITE_HELMET, "Gold Upgraded Netherite Helmet");
        builder.add(ModItems.GOLD_NETHERITE_CHESTPLATE, "Gold Upgraded Netherite Chestplate");
        builder.add(ModItems.GOLD_NETHERITE_LEGGINGS, "Gold Upgraded Netherite Leggings");
        builder.add(ModItems.GOLD_NETHERITE_BOOTS, "Gold Upgraded Netherite Boots");
        builder.add(ModItems.GOLD_NETHERITE_SWORD, "Gold Upgraded Netherite Sword");
        builder.add(ModItems.GOLD_NETHERITE_SHOVEL, "Gold Upgraded Netherite Shovel");
        builder.add(ModItems.GOLD_NETHERITE_PICKAXE, "Gold Upgraded Netherite Pickaxe");
        builder.add(ModItems.GOLD_NETHERITE_AXE, "Gold Upgraded Netherite Axe");
        builder.add(ModItems.GOLD_NETHERITE_HOE, "Gold Upgraded Netherite Hoe");

        builder.add(ModItems.DIAMOND_HELMET, "§BLucky Helmet");
        builder.add(ModItems.DIAMOND_CHESTPLATE, "§BLucky Chestplate");
        builder.add(ModItems.DIAMOND_LEGGINGS, "§BLucky Leggings");
        builder.add(ModItems.DIAMOND_BOOTS, "§BLucky Boots");
        builder.add(ModItems.DIAMOND_SWORD, "§BLucky Sword");
        builder.add(ModItems.DIAMOND_PICKAXE, "§BLucky Pickaxe");
        builder.add(ModItems.DIAMOND_AXE, "§BLucky Axe");
        builder.add(ModItems.DIAMOND_SHOVEL, "§BLucky Shovel");
    }
}
