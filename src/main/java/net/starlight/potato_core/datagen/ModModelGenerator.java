package net.starlight.potato_core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.starlight.potato_core.block.CornCrop;
import net.starlight.potato_core.register.ModBlocks;
import net.starlight.potato_core.register.ModFluids;
import net.starlight.potato_core.register.ModItems;
import net.starlight.potato_core.register.ModSounds;

public class ModModelGenerator extends FabricModelProvider {

    public ModModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // 上下左右八个面都是一个图片的简单的方块
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WOOL_1X);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WOOL_2X);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WOOL_3X);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WOOL_4X);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WOOL_5X);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WOOL_6X);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WOOL_7X);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WOOL_8X);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WOOL_9X);

        blockStateModelGenerator.registerCrop(ModBlocks.CORN, CornCrop.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.IRON_LEAVES);
        blockStateModelGenerator.registerLog(ModBlocks.IRON_LOG).log(ModBlocks.IRON_LOG);
        blockStateModelGenerator.registerTintableCross(ModBlocks.IRON_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerSimpleState(ModFluids.PAREDISE_WATER_BLOCK);


    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // 动态生成item模型[json文件]
        itemModelGenerator.register(ModItems.CHICKEN_SOUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.COLORS_BLACK, Models.GENERATED);
        itemModelGenerator.register(ModItems.KUN_BOOK, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_PEARL, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAP_BASKETBALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMALL_BLACK, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN, Models.GENERATED);
        // 锭、盔甲、武器
        itemModelGenerator.register(ModItems.GOLD_NETHERITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_NETHERITE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_NETHERITE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_NETHERITE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_NETHERITE_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_NETHERITE_SWORD, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_NETHERITE_SHOVEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_NETHERITE_PICKAXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_NETHERITE_AXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_NETHERITE_HOE, Models.GENERATED);
        // 其他物品
        itemModelGenerator.register(ModItems.FLAME_FLINT, Models.GENERATED);
        // other
        itemModelGenerator.register(ModSounds.GALAXY_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModFluids.PAREDISE_WATER_BUCKET, Models.GENERATED);
    }
}
