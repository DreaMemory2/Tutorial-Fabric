package net.starlight.potato_core.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.block.CornCrop;
import net.starlight.potato_core.block.CrystalGrassBlock;
import net.starlight.potato_core.block.DrinkMachineBlock;
import net.starlight.potato_core.block.IronSaplingGenerator;
import net.starlight.potato_core.block.CandleAppleCakeBlock;

public class ModBlocks {
    // 压缩羊毛
    public static final Block WOOL_1X = ModBlocks.registerBlock("compressed_wool_1x", new Block(Settings.copy(Blocks.WHITE_WOOL)), ModItemGroup.BUILDING);
    public static final Block WOOL_2X = ModBlocks.registerBlock("compressed_wool_2x", new Block(Settings.copy(Blocks.WHITE_WOOL)), ModItemGroup.BUILDING);
    public static final Block WOOL_3X = ModBlocks.registerBlock("compressed_wool_3x", new Block(Settings.copy(Blocks.WHITE_WOOL)), ModItemGroup.BUILDING);
    public static final Block WOOL_4X = ModBlocks.registerBlock("compressed_wool_4x", new Block(Settings.copy(Blocks.WHITE_WOOL)), ModItemGroup.BUILDING);
    public static final Block WOOL_5X = ModBlocks.registerBlock("compressed_wool_5x", new Block(Settings.copy(Blocks.WHITE_WOOL)), ModItemGroup.BUILDING);
    public static final Block WOOL_6X = ModBlocks.registerBlock("compressed_wool_6x", new Block(Settings.copy(Blocks.WHITE_WOOL)), ModItemGroup.BUILDING);
    public static final Block WOOL_7X = ModBlocks.registerBlock("compressed_wool_7x", new Block(Settings.copy(Blocks.WHITE_WOOL)), ModItemGroup.BUILDING);
    public static final Block WOOL_8X = ModBlocks.registerBlock("compressed_wool_8x", new Block(Settings.copy(Blocks.WHITE_WOOL)), ModItemGroup.BUILDING);
    public static final Block WOOL_9X = ModBlocks.registerBlock("compressed_wool_9x", new Block(Settings.copy(Blocks.WHITE_WOOL)), ModItemGroup.BUILDING);
    // Difficult Problems
    // 玉米的作物
    public static final Block CORN = registerWithoutBlockItem("corn_crop", new CornCrop(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    /**
     * <p>自定义树，创建树苗，树干，树叶的所需要方块</p>
     * <p>配置Tree Configured Feature和Tree Placed Feature，创建树的地物</p>
     */
    // 铁树需要的原木、树叶和树苗
    public static final Block IRON_LOG = registerBlock("iron_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)), ModItemGroup.BUILDING);
    public static final Block IRON_LEAVES = registerBlock("iron_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)), ModItemGroup.BUILDING);
    public static final Block MAPLE_LEAVES = registerBlock("maple_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)), ModItemGroup.BUILDING); // 树的生成
    public static final Block IRON_SAPLING = registerBlock("iron_sapling", new SaplingBlock(new IronSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)), ModItemGroup.ITEMS);
    /**
     * <p>实现石头在不同群系中的颜色渲染效果，跟随生态群系的主要颜色变化而变化</p>
     */
    public static final Block STONE = registerBlock("biome_stone", new Block(FabricBlockSettings.copyOf(Blocks.STONE)), ModItemGroup.BLOCKS);
    /**
     * <p>添加白色木板楼梯</p>
     * <p>实现{@link StairsBlock}楼梯方块的类，并且提供方块状态和方块属性。其中方块默认状态是白色木板，方块属性复制橡木楼梯的属性的值</p>
     * <p>在资源文件中配置相关方块状态，在models/block文件夹下配置stairs_inner，stairs_outer和stairs材质</p>
     * <p>在models/item文件夹下配置stairs文件<，物品栏上方块显示材质样式；最后在loot_tables添加掉落物</p>
     */
    // 添加白色木板
    public static final Block WHITE_PLANKS = registerBlock("white_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), ModItemGroup.BLOCKS);
    public static final Block WHITE_PLANK_STAIRS = registerBlock("white_plank_stairs", new StairsBlock(ModBlocks.WHITE_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)), ModItemGroup.BLOCKS);
    /**
     * <p>饮料机，自定义方块信息界面，自定义配方</p>
     */
    public static final Block DRINK_MACHINE = registerBlock("drink_machine", new DrinkMachineBlock(FabricBlockSettings.of(Material.METAL)), ModItemGroup.BLOCKS);
    /**
     * 自定义蛋糕：苹果蛋糕
     */
    public static final Block APPLE_CAKE = registerBlock("apple_cake", new CakeBlock(FabricBlockSettings.copyOf(Blocks.CAKE)), ModItemGroup.ITEMS);
    /**
     * 自定义蜡烛蛋糕
     */
    public static final Block CANDLE_APPLE_CAKE = registerWithoutBlockItem("candle_apple_cake", new CandleAppleCakeBlock(Blocks.CANDLE));
    public static final Block WHITE_CANDLE_APPLE_CAKE = registerWithoutBlockItem("white_candle_apple_cake", new CandleAppleCakeBlock(Blocks.WHITE_CANDLE));
    public static final Block ORANGE_CANDLE_APPLE_CAKE = registerWithoutBlockItem("orange_candle_apple_cake", new CandleAppleCakeBlock(Blocks.ORANGE_CANDLE));
    public static final Block MAGENTA_CANDLE_APPLE_CAKE = registerWithoutBlockItem("magenta_candle_apple_cake", new CandleAppleCakeBlock(Blocks.MAGENTA_CANDLE));
    public static final Block LIGHT_BLUE_CANDLE_APPLE_CAKE = registerWithoutBlockItem("light_blue_candle_apple_cake", new CandleAppleCakeBlock(Blocks.LIGHT_BLUE_CANDLE));
    public static final Block YELLOW_CANDLE_APPLE_CAKE = registerWithoutBlockItem("yellow_candle_apple_cake", new CandleAppleCakeBlock(Blocks.YELLOW_CANDLE));
    public static final Block LIME_CANDLE_APPLE_CAKE = registerWithoutBlockItem("lime_candle_apple_cake", new CandleAppleCakeBlock(Blocks.LIME_CANDLE));
    public static final Block PINK_CANDLE_APPLE_CAKE = registerWithoutBlockItem("pink_candle_apple_cake", new CandleAppleCakeBlock(Blocks.PINK_CANDLE));
    public static final Block GRAY_CANDLE_APPLE_CAKE = registerWithoutBlockItem("gray_candle_apple_cake", new CandleAppleCakeBlock(Blocks.GRAY_CANDLE));
    public static final Block LIGHT_GRAY_CANDLE_APPLE_CAKE = registerWithoutBlockItem("light_gray_candle_apple_cake", new CandleAppleCakeBlock(Blocks.LIGHT_GRAY_CANDLE));
    public static final Block CYAN_CANDLE_APPLE_CAKE = registerWithoutBlockItem("cyan_candle_apple_cake", new CandleAppleCakeBlock(Blocks.CYAN_CANDLE));
    public static final Block PURPLE_CANDLE_APPLE_CAKE = registerWithoutBlockItem("purple_candle_apple_cake", new CandleAppleCakeBlock(Blocks.PURPLE_CANDLE));
    public static final Block BLUE_CANDLE_APPLE_CAKE = registerWithoutBlockItem("blue_candle_apple_cake", new CandleAppleCakeBlock(Blocks.BLUE_CANDLE));
    public static final Block BROWN_CANDLE_APPLE_CAKE = registerWithoutBlockItem("brown_candle_apple_cake", new CandleAppleCakeBlock(Blocks.BROWN_CANDLE));
    public static final Block GREEN_CANDLE_APPLE_CAKE = registerWithoutBlockItem("green_candle_apple_cake", new CandleAppleCakeBlock(Blocks.GREEN_CANDLE));
    public static final Block RED_CANDLE_APPLE_CAKE = registerWithoutBlockItem("red_candle_apple_cake", new CandleAppleCakeBlock(Blocks.RED_CANDLE));
    public static final Block BLACK_CANDLE_APPLE_CAKE = registerWithoutBlockItem("black_candle_apple_cake", new CandleAppleCakeBlock(Blocks.BLACK_CANDLE));
    /**
     * 自定义草方块
     */
    public static final Block CRYSAL_GRASS_BLOCK = registerBlock("crystal_grass", new CrystalGrassBlock(), ModItemGroup.BLOCKS);
    public static final Block CRYSAL_DIRT = registerBlock("crystal_dirt", new Block(FabricBlockSettings.copyOf(Blocks.DIRT)), ModItemGroup.BLOCKS);

    public static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registries.BLOCK, new Identifier(FirstMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block, ItemGroup tab) {
        Item item = Registry.register(Registries.ITEM, new Identifier(FirstMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.add(item));
    }

    private static Block registerWithoutBlockItem(String name, Block block) {
        // Registry.register(Registries.ITEM, new Identifier(FirstMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        return Registry.register(Registries.BLOCK, new Identifier(FirstMod.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        FirstMod.LOGGER.debug("Register Blocks for" + FirstMod.MOD_ID);
    }
}