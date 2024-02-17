package net.starlight.potato_core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.starlight.potato_core.register.ModBlocks;

import java.util.concurrent.CompletableFuture;

/**
 * <p>方块标签生成器</p>
 */
public class ModBlockTagGeneration extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagGeneration(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup arg) {
        // 原木方块标签
        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(ModBlocks.IRON_LOG);
        // 泥土方块标签
        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(ModBlocks.CRYSAL_GRASS_BLOCK)
                .add(ModBlocks.CRYSAL_DIRT);
        // 蛋糕标签
        getOrCreateTagBuilder(BlockTags.CANDLE_CAKES)
                .add(ModBlocks.APPLE_CAKE)
                .add(ModBlocks.CANDLE_APPLE_CAKE)
                .add(ModBlocks.WHITE_CANDLE_APPLE_CAKE)
                .add(ModBlocks.ORANGE_CANDLE_APPLE_CAKE)
                .add(ModBlocks.MAGENTA_CANDLE_APPLE_CAKE)
                .add(ModBlocks.LIGHT_BLUE_CANDLE_APPLE_CAKE)
                .add(ModBlocks.YELLOW_CANDLE_APPLE_CAKE)
                .add(ModBlocks.LIME_CANDLE_APPLE_CAKE)
                .add(ModBlocks.PINK_CANDLE_APPLE_CAKE)
                .add(ModBlocks.GRAY_CANDLE_APPLE_CAKE)
                .add(ModBlocks.LIGHT_GRAY_CANDLE_APPLE_CAKE)
                .add(ModBlocks.CYAN_CANDLE_APPLE_CAKE)
                .add(ModBlocks.PURPLE_CANDLE_APPLE_CAKE)
                .add(ModBlocks.BLUE_CANDLE_APPLE_CAKE)
                .add(ModBlocks.BROWN_CANDLE_APPLE_CAKE)
                .add(ModBlocks.GREEN_CANDLE_APPLE_CAKE)
                .add(ModBlocks.RED_CANDLE_APPLE_CAKE)
                .add(ModBlocks.BLACK_CANDLE_APPLE_CAKE);
        // 树叶标签
        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.IRON_LEAVES);
        // 使用工具标签
        configureTools();
    }

    private void configureTools() {
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.CRYSAL_GRASS_BLOCK)
                .add(ModBlocks.CRYSAL_DIRT);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.STONE);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.IRON_LOG)
                .add(ModBlocks.WHITE_PLANKS)
                .add(ModBlocks.WHITE_PLANK_STAIRS);

    }
}
