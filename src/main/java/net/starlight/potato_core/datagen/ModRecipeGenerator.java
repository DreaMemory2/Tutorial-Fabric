package net.starlight.potato_core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.starlight.potato_core.register.ModItems;

import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLD_NETHERITE_HELMET)
                .pattern("aaa")
                .pattern("a a")
                .pattern("   ")
                .input('a', ModItems.GOLD_NETHERITE)
                .criterion(FabricRecipeProvider.hasItem(ModItems.GOLD_NETHERITE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.GOLD_NETHERITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLD_NETHERITE_CHESTPLATE)
                .pattern("a a")
                .pattern("aaa")
                .pattern("aaa")
                .input('a', ModItems.GOLD_NETHERITE)
                .criterion(FabricRecipeProvider.hasItem(ModItems.GOLD_NETHERITE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.GOLD_NETHERITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLD_NETHERITE_LEGGINGS)
                .pattern("aaa")
                .pattern("a a")
                .pattern("a a")
                .input('a', ModItems.GOLD_NETHERITE)
                .criterion(FabricRecipeProvider.hasItem(ModItems.GOLD_NETHERITE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.GOLD_NETHERITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLD_NETHERITE_BOOTS)
                .pattern("   ")
                .pattern("a a")
                .pattern("a a")
                .input('a', ModItems.GOLD_NETHERITE)
                .criterion(FabricRecipeProvider.hasItem(ModItems.GOLD_NETHERITE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.GOLD_NETHERITE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLD_NETHERITE_SWORD)
                .pattern(" a ")
                .pattern(" a ")
                .pattern(" i ")
                .input('a', ModItems.GOLD_NETHERITE)
                .input('i', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.GOLD_NETHERITE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.GOLD_NETHERITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLD_NETHERITE_SHOVEL)
                .pattern(" a ")
                .pattern(" i ")
                .pattern(" i ")
                .input('a', ModItems.GOLD_NETHERITE)
                .input('i', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.GOLD_NETHERITE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.GOLD_NETHERITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLD_NETHERITE_PICKAXE)
                .pattern("aaa")
                .pattern(" i ")
                .pattern(" i ")
                .input('a', ModItems.GOLD_NETHERITE)
                .input('i', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.GOLD_NETHERITE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.GOLD_NETHERITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLD_NETHERITE_AXE)
                .pattern("aa ")
                .pattern("ai ")
                .pattern(" i ")
                .input('a', ModItems.GOLD_NETHERITE)
                .input('i', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.GOLD_NETHERITE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.GOLD_NETHERITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLD_NETHERITE_HOE)
                .pattern("aa ")
                .pattern(" i ")
                .pattern(" i ")
                .input('a', ModItems.GOLD_NETHERITE)
                .input('i', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.GOLD_NETHERITE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.GOLD_NETHERITE))
                .offerTo(exporter);
    }
}
