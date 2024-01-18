package net.starlight.potato_core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.starlight.potato_core.register.ModItems;

public class ModLanguageGenerator extends FabricLanguageProvider {
    public ModLanguageGenerator(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.GOLD_NETHERITE, "Gold Upgraded Netherite Ingot");
        translationBuilder.add(ModItems.GOLD_NETHERITE_HELMET, "Gold Upgraded Netherite Helmet");
        translationBuilder.add(ModItems.GOLD_NETHERITE_CHESTPLATE, "Gold Upgraded Netherite Chestplate");
        translationBuilder.add(ModItems.GOLD_NETHERITE_LEGGINGS, "Gold Upgraded Netherite Leggings");
        translationBuilder.add(ModItems.GOLD_NETHERITE_BOOTS, "Gold Upgraded Netherite Boots");
        translationBuilder.add(ModItems.GOLD_NETHERITE_SWORD, "Gold Upgraded Netherite Sword");
        translationBuilder.add(ModItems.GOLD_NETHERITE_SHOVEL, "Gold Upgraded Netherite Shovel");
        translationBuilder.add(ModItems.GOLD_NETHERITE_PICKAXE, "Gold Upgraded Netherite Pickaxe");
        translationBuilder.add(ModItems.GOLD_NETHERITE_AXE, "Gold Upgraded Netherite Axe");
        translationBuilder.add(ModItems.GOLD_NETHERITE_HOE, "Gold Upgraded Netherite Hoe");
        // 其他物品
        translationBuilder.add(ModItems.CORN_SEEDS, "Corn Seeds");
        translationBuilder.add(ModItems.FLAME_FLINT, "Flame Fire");
    }
}
