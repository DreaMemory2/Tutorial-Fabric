package net.starlight.potato_core.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.block.CornCrop;
import net.starlight.potato_core.block.IronSaplingGenerator;
import net.starlight.potato_core.block.NineCompressedWool;

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
    public static final Block WOOL_9X = ModBlocks.registerBlock("compressed_wool_9x", new NineCompressedWool(Settings.copy(Blocks.WHITE_WOOL)), ModItemGroup.BUILDING);
    // Difficult Problems
    // 玉米的作物
    public static Block CORN = Registry.register(Registries.BLOCK, new Identifier(FirstMod.MOD_ID, "corn"),
            new CornCrop(Settings.of(Material.REPLACEABLE_PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
    // 铁树需要的原木、树叶和树苗
    public static final Block IRON_LOG = registerBlock("iron_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)), ModItemGroup.BUILDING);
    public static final Block IRON_LEAVES = registerBlock("iron_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)), ModItemGroup.BUILDING);
    // Sapling 铁树苗
    public static final Block IRON_SAPLING = registerBlock("iron_sapling", new SaplingBlock(new IronSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)), ModItemGroup.ITEMS);

    public static Block registerBlock(String name, Block block, ItemGroup tab){
        registerBlockItem(name, block, tab);
        return Registry.register(Registries.BLOCK, new Identifier(FirstMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block, ItemGroup tab) {
        Item item = Registry.register(Registries.ITEM, new Identifier(FirstMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.add(item));
    }

    private static void registerOtherBlocks() {
        // 玉米的作物
        Registry.register(Registries.ITEM, new Identifier(FirstMod.MOD_ID, "corn"), new BlockItem(new CornCrop(FabricBlockSettings.copyOf(Blocks.WHEAT)), new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        FirstMod.LOGGER.debug("Register Blocks for" + FirstMod.MOD_ID);
    }
}