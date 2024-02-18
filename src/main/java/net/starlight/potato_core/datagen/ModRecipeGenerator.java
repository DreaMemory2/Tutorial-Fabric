package net.starlight.potato_core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.SmithingTrimRecipeJsonBuilder;
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
        generateNetheriteToolAndArmors(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SWORD, ModItems.GOLD_NETHERITE_INGOT, RecipeCategory.TOOLS, "gold_netherite_sword");
        generateNetheriteToolAndArmors(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_PICKAXE, ModItems.GOLD_NETHERITE_INGOT, RecipeCategory.TOOLS, "gold_netherite_pickaxe");
        generateNetheriteToolAndArmors(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_AXE, ModItems.GOLD_NETHERITE_INGOT, RecipeCategory.TOOLS, "gold_netherite_axe");
        generateNetheriteToolAndArmors(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SHOVEL, ModItems.GOLD_NETHERITE_INGOT, RecipeCategory.TOOLS, "gold_netherite_shovel");
        generateNetheriteToolAndArmors(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HOE, ModItems.GOLD_NETHERITE_INGOT, RecipeCategory.TOOLS, "gold_netherite_hoe");
        generateNetheriteToolAndArmors(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HELMET, ModItems.GOLD_NETHERITE_INGOT, RecipeCategory.TOOLS, "gold_netherite_helmet");
        generateNetheriteToolAndArmors(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_CHESTPLATE, ModItems.GOLD_NETHERITE_INGOT, RecipeCategory.TOOLS, "gold_netherite_chestplate");
        generateNetheriteToolAndArmors(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_LEGGINGS, ModItems.GOLD_NETHERITE_INGOT, RecipeCategory.TOOLS, "gold_netherite_leggings");
        generateNetheriteToolAndArmors(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_BOOTS, ModItems.GOLD_NETHERITE_INGOT, RecipeCategory.TOOLS, "gold_netherite_boots");
    }

    /**
     * <p>1.19.3 动态配方合成表</p>
     * <pre><code>
     *     SmithingRecipeJsonProvide.create(
     *                 // 需要升级的武器
     *                 Ingredient.ofStacks(new ItemStack(base)),
     *                 // 原材料
     *                 Ingredient.ofStacks(new ItemStack(addition)),
     *                 // 配方类包
     *                 category,
     *                 // 升级后的武器
     *                 result
     *                 ).criterion(FabricRecipeProvider.hasItem(addition), FabricRecipeProvider.conditionsFromItem(addition))
     *                 .offerTo(exporter, recipeName);
     * </code></pre>
     */
    public void generateNetheriteToolAndArmors(Consumer<RecipeJsonProvider> exporter, Item template, Item base, Item addition, RecipeCategory category, String recipeName) {
        // 1.19.4 动态配方合成表
        SmithingTrimRecipeJsonBuilder.create(
                // 锻造模板
                Ingredient.ofStacks(new ItemStack(template)),
                // 原材料
                Ingredient.ofStacks(new ItemStack(base)),
                // 配方类包
                Ingredient.ofStacks(new ItemStack(addition)),
                category
        ).criterion(FabricRecipeProvider.hasItem(addition),
                        FabricRecipeProvider.conditionsFromItem(addition)).offerTo(exporter, recipeName);
    }
}
