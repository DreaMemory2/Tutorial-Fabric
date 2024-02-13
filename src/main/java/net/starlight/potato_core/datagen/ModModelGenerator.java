package net.starlight.potato_core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.starlight.potato_core.register.*;

public class ModModelGenerator extends FabricModelProvider {

    public ModModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator block) {
        // 六个面都是一个图片的简单的方块
        block.registerSimpleCubeAll(ModBlocks.WOOL_1X);
        block.registerSimpleCubeAll(ModBlocks.WOOL_2X);
        block.registerSimpleCubeAll(ModBlocks.WOOL_3X);
        block.registerSimpleCubeAll(ModBlocks.WOOL_4X);
        block.registerSimpleCubeAll(ModBlocks.WOOL_5X);
        block.registerSimpleCubeAll(ModBlocks.WOOL_6X);
        block.registerSimpleCubeAll(ModBlocks.WOOL_7X);
        block.registerSimpleCubeAll(ModBlocks.WOOL_8X);
        block.registerSimpleCubeAll(ModBlocks.WOOL_9X);
        block.registerSimpleCubeAll(ModBlocks.CRYSAL_DIRT);
        // 树叶生成器
        block.registerSingleton(ModBlocks.IRON_LEAVES, TexturedModel.LEAVES);
        // 原木生成器
        block.registerLog(ModBlocks.IRON_LOG).log(ModBlocks.IRON_LOG);
        // 树苗生成器
        block.registerTintableCross(ModBlocks.IRON_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        // 液体生成标签
        block.registerSimpleState(ModFluids.PAREDISE_WATER_BLOCK);
        // 楼梯生成器
        block.registerCubeAllModelTexturePool(ModBlocks.WHITE_PLANKS).stairs(ModBlocks.WHITE_PLANK_STAIRS);

        // 自定义生成器
        // 草方块生成器，例如CrystalGrass
        registerGrassBlock(block, ModBlocks.CRYSAL_GRASS_BLOCK, ModBlocks.CRYSAL_GRASS_BLOCK, ModBlocks.CRYSAL_DIRT);
        // 功能方块生成器
        registerCraftTable(block);
        // 蛋糕生成器
        registerCake(block);
        registerCandleCake(block);
    }

    @Override
    public void generateItemModels(ItemModelGenerator item) {
        // 锭、盔甲、武器
        item.register(ModItems.GOLD_NETHERITE_INGOT, Models.GENERATED);
        item.register(ModItems.GOLD_NETHERITE_HELMET, Models.GENERATED);
        item.register(ModItems.GOLD_NETHERITE_CHESTPLATE, Models.GENERATED);
        item.register(ModItems.GOLD_NETHERITE_LEGGINGS, Models.GENERATED);
        item.register(ModItems.GOLD_NETHERITE_BOOTS, Models.GENERATED);
        item.register(ModItems.GOLD_NETHERITE_SWORD, Models.GENERATED);
        item.register(ModItems.GOLD_NETHERITE_SHOVEL, Models.GENERATED);
        item.register(ModItems.GOLD_NETHERITE_PICKAXE, Models.GENERATED);
        item.register(ModItems.GOLD_NETHERITE_AXE, Models.GENERATED);
        item.register(ModItems.GOLD_NETHERITE_HOE, Models.GENERATED);
        // 其他物品
        // 动态生成item模型[json文件]
        item.register(ModItems.CHICKEN_SOUP, Models.GENERATED);
        item.register(ModItems.COLORS_BLACK, Models.GENERATED);
        item.register(ModItems.KUN_BOOK, Models.GENERATED);
        item.register(ModItems.RAP_BASKETBALL, Models.GENERATED);
        item.register(ModItems.SMALL_BLACK, Models.GENERATED);
        item.register(ModItems.CORN, Models.GENERATED);
        item.register(ModItems.FLAME_FLINT, Models.GENERATED);
        item.register(ModSounds.GALAXY_ITEM, Models.GENERATED);
        item.register(ModFluids.PAREDISE_WATER_BUCKET, Models.GENERATED);
        item.register(ModItems.LUCKY_POTION, Models.GENERATED);
        item.register(ModItems.DATA_TABLET,Models.GENERATED);
        item.register(ModItems.APPLE_JUICE, Models.GENERATED);
        item.register(ModItems.CORN_SEEDS, Models.GENERATED);
    }

    /**
     *
     * @param block 方块构造器
     * @param grassBlock 类似草方块的侧面
     * @param topBlock 类似草方块的顶部
     * @param dirt 泥土，类似草方块的底部
     */
    private void registerGrassBlock(BlockStateModelGenerator block, Block grassBlock, Block topBlock, Block dirt) {
        TextureMap texture = new TextureMap()
                // 底部类似泥土材质
                .put(TextureKey.BOTTOM, TextureMap.getId(dirt)).inherit(TextureKey.BOTTOM, TextureKey.PARTICLE)
                // 顶部类似草方块材质
                .put(TextureKey.TOP, TextureMap.getId(topBlock))
                // 侧面为带泥土的草方块，草方块的四个侧面
                .put(TextureKey.SIDE, TextureMap.getSubId(grassBlock, "_side"));
        //  创建一个方块模型，将材质设置为CubeBottomTop
        BlockStateVariant model = BlockStateVariant.create()
                .put(VariantSettings.MODEL, Models.CUBE_BOTTOM_TOP.upload(grassBlock, texture, block.modelCollector));
        block.blockStateCollector.accept(VariantsBlockStateSupplier.create(grassBlock, model));
    }

    /**
     * <p>功能方块生成器</p>
     * @param block 方块构造器
     */
    private void registerCraftTable(BlockStateModelGenerator block) {
        TextureMap texture = new TextureMap().put(TextureKey.TOP,
                TextureMap.getSubId(ModBlocks.DRINK_MACHINE, "_top"))
                        .put(TextureKey.SIDE, TextureMap.getSubId(ModBlocks.DRINK_MACHINE, "_side"))
                        .put(TextureKey.FRONT, TextureMap.getSubId(ModBlocks.DRINK_MACHINE, "_front"));
        Identifier identifier = Models.ORIENTABLE.upload(ModBlocks.DRINK_MACHINE, texture, block.modelCollector);
        // 配置属性文件
        block.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.DRINK_MACHINE)
                .coordinate(BlockStateVariantMap.create(HorizontalFacingBlock.FACING)
                                .register(Direction.NORTH, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, identifier))
                                .register(Direction.EAST, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, identifier)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                                .register(Direction.SOUTH, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, identifier)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                                .register(Direction.WEST, BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, identifier)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R270))));
    }
    private void registerCake(BlockStateModelGenerator block) {
        block.registerItemModel(ModBlocks.APPLE_CAKE.asItem());
        block.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.APPLE_CAKE)
                .coordinate(BlockStateVariantMap.create(Properties.BITES)
                        .register(0, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, ModelIds.getBlockModelId(ModBlocks.APPLE_CAKE)))
                        .register(1, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.APPLE_CAKE, "_slice1")))
                        .register(2, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.APPLE_CAKE, "_slice2")))
                        .register(3, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.APPLE_CAKE, "_slice3")))
                        .register(4, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.APPLE_CAKE, "_slice4")))
                        .register(5, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.APPLE_CAKE, "_slice5")))
                        .register(6, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(ModBlocks.APPLE_CAKE, "_slice6")))));
    }

    private void registerCandleCake(BlockStateModelGenerator block) {
        registerCandle(block, Blocks.WHITE_CANDLE, ModBlocks.WHITE_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.ORANGE_CANDLE, ModBlocks.ORANGE_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.MAGENTA_CANDLE, ModBlocks.MAGENTA_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.LIGHT_BLUE_CANDLE, ModBlocks.LIGHT_BLUE_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.YELLOW_CANDLE, ModBlocks.YELLOW_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.LIME_CANDLE, ModBlocks.LIME_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.PINK_CANDLE, ModBlocks.PINK_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.GRAY_CANDLE, ModBlocks.GRAY_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.LIGHT_GRAY_CANDLE, ModBlocks.LIGHT_GRAY_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.CYAN_CANDLE, ModBlocks.CYAN_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.PURPLE_CANDLE, ModBlocks.PURPLE_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.BLUE_CANDLE, ModBlocks.BLUE_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.BROWN_CANDLE, ModBlocks.BROWN_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.GREEN_CANDLE, ModBlocks.GREEN_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.RED_CANDLE, ModBlocks.RED_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.BLACK_CANDLE, ModBlocks.BLACK_CANDLE_APPLE_CAKE);
        registerCandle(block, Blocks.CANDLE, ModBlocks.CANDLE_APPLE_CAKE);
    }

    private void registerCandle(BlockStateModelGenerator block, Block candle, Block cake) {

        Identifier candleCake = Models.TEMPLATE_CAKE_WITH_CANDLE.upload(cake, candleCake(candle, false), block.modelCollector);
        Identifier candleCakeLit = Models.TEMPLATE_CAKE_WITH_CANDLE.upload(cake, "_lit", candleCake(candle, true), block.modelCollector);
        block.blockStateCollector.accept(VariantsBlockStateSupplier.create(cake)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT, candleCakeLit, candleCake)));
    }

    private static TextureMap candleCake(Block block, boolean lit) {
        return new TextureMap()
                .put(TextureKey.PARTICLE, TextureMap.getSubId(ModBlocks.APPLE_CAKE, "_side"))
                .put(TextureKey.BOTTOM, TextureMap.getSubId(ModBlocks.APPLE_CAKE, "_bottom"))
                .put(TextureKey.TOP, TextureMap.getSubId(ModBlocks.APPLE_CAKE, "_top"))
                .put(TextureKey.SIDE, TextureMap.getSubId(ModBlocks.APPLE_CAKE, "_side"))
                .put(TextureKey.CANDLE, TextureMap.getSubId(block, lit ? "_lit" : ""));
    }
}
