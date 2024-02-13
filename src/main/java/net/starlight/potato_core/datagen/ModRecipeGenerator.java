package net.starlight.potato_core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.SmithingRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.starlight.potato_core.register.ModItems;

import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        generateNetheriteToolAndArmors(exporter, Items.DIAMOND_SWORD, ModItems.GOLD_NETHERITE_INGOT, ModItems.GOLD_NETHERITE_SWORD, RecipeCategory.TOOLS, "gold_netherite_sword");
        generateNetheriteToolAndArmors(exporter, Items.DIAMOND_PICKAXE, ModItems.GOLD_NETHERITE_INGOT, ModItems.GOLD_NETHERITE_PICKAXE, RecipeCategory.TOOLS, "gold_netherite_pickaxe");
        generateNetheriteToolAndArmors(exporter, Items.DIAMOND_AXE, ModItems.GOLD_NETHERITE_INGOT, ModItems.GOLD_NETHERITE_AXE, RecipeCategory.TOOLS, "gold_netherite_axe");
        generateNetheriteToolAndArmors(exporter, Items.DIAMOND_SHOVEL, ModItems.GOLD_NETHERITE_INGOT, ModItems.GOLD_NETHERITE_SHOVEL, RecipeCategory.TOOLS, "gold_netherite_shovel");
        generateNetheriteToolAndArmors(exporter, Items.DIAMOND_HOE, ModItems.GOLD_NETHERITE_INGOT, ModItems.GOLD_NETHERITE_HOE, RecipeCategory.TOOLS, "gold_netherite_hoe");

        generateNetheriteToolAndArmors(exporter, Items.DIAMOND_HELMET, ModItems.GOLD_NETHERITE_INGOT, ModItems.GOLD_NETHERITE_HELMET, RecipeCategory.TOOLS, "gold_netherite_helmet");
        generateNetheriteToolAndArmors(exporter, Items.DIAMOND_CHESTPLATE, ModItems.GOLD_NETHERITE_INGOT, ModItems.GOLD_NETHERITE_CHESTPLATE, RecipeCategory.TOOLS, "gold_netherite_chestplate");
        generateNetheriteToolAndArmors(exporter, Items.DIAMOND_LEGGINGS, ModItems.GOLD_NETHERITE_INGOT, ModItems.GOLD_NETHERITE_LEGGINGS, RecipeCategory.TOOLS, "gold_netherite_leggings");
        generateNetheriteToolAndArmors(exporter, Items.DIAMOND_BOOTS, ModItems.GOLD_NETHERITE_INGOT, ModItems.GOLD_NETHERITE_BOOTS, RecipeCategory.TOOLS, "gold_netherite_boots");
    }

    public void generateNetheriteToolAndArmors(Consumer<RecipeJsonProvider> exporter, Item base, Item addition, Item result, RecipeCategory category, String recipeName) {
        SmithingRecipeJsonBuilder.create(
                // 需要升级的武器
                Ingredient.ofStacks(new ItemStack(base)),
                // 原材料
                Ingredient.ofStacks(new ItemStack(addition)),
                // 配方类包
                category,
                // 升级后的武器
                result
                ).criterion(FabricRecipeProvider.hasItem(addition), FabricRecipeProvider.conditionsFromItem(addition))
                .offerTo(exporter, recipeName);
    }
}
