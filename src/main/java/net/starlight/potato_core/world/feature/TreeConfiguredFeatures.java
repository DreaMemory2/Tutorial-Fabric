package net.starlight.potato_core.world.feature;

import net.minecraft.block.Block;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.register.ModBlocks;

import java.util.List;
import java.util.OptionalInt;

/**
 * <p>TreeConfigFeatures</p>
 */
public class TreeConfiguredFeatures {
    // Iron Tree 的4种形式
    public static final RegistryKey<ConfiguredFeature<?, ?>> IRON_TREE = registry("iron_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> IRON_TREE_BEES = registry("iron_tree_bees");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_IRON = registry("fancy_iron");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_IRON_BEES = registry("fancy_iron_bees");

    /**
     * 原版的树的结构分为不同的种类，方便你做出复杂并且漂亮的树
     * @param log TrunkProvider 生成树干
     * @param leaves FoliageProvider 生成树叶
     * @param baseHeight 基础高度
     * @param firstRandomHeight 第一随机高度
     * @param secondRandomHeight 第二随机高度
     * @param radius 半径 r
     */
    private static TreeFeatureConfig.Builder builder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
        return new TreeFeatureConfig.Builder(
                // 树干方块提供器
                BlockStateProvider.of(log),
                // 放置竖直树干
                new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight),
                // 树叶方块提供器
                BlockStateProvider.of(leaves),
                // 生成水滴状的树叶（半径radius、相对于树干的偏移、高度）
                new BlobFoliagePlacer(ConstantIntProvider.create(radius), ConstantIntProvider.create(0), 3),
                // 不同层的树木的宽度，用于查看树木在不卡到方块中可以有多高
                new TwoLayersFeatureSize(1, 0, 1));
    }

    /**
     * <p>Iron Tree</p>
     */
    private static TreeFeatureConfig.Builder ironTree() {
        // 使用builder配置特征地形
        return TreeConfiguredFeatures.builder(ModBlocks.IRON_LOG, ModBlocks.IRON_LEAVES,
                4, 2, 0, 2).ignoreVines();
    }

    /**
     * <p>Fancy Iron</p>
     */
    private static TreeFeatureConfig.Builder fancyIron() {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.IRON_LOG),
                new LargeOakTrunkPlacer(3, 11, 0),
                BlockStateProvider.of(ModBlocks.IRON_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines();
    }


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        BeehiveTreeDecorator beehiveTreeDecorator4 = new BeehiveTreeDecorator(0.05f);
        BeehiveTreeDecorator beehiveTreeDecorator5 = new BeehiveTreeDecorator(1.0f);
        ConfiguredFeatures.register(featureRegisterable, IRON_TREE, Feature.TREE, TreeConfiguredFeatures.ironTree().build());
        ConfiguredFeatures.register(featureRegisterable, IRON_TREE_BEES, Feature.TREE, TreeConfiguredFeatures.ironTree().decorators(List.of(beehiveTreeDecorator4)).build());
        ConfiguredFeatures.register(featureRegisterable, FANCY_IRON, Feature.TREE, TreeConfiguredFeatures.fancyIron().decorators(List.of(beehiveTreeDecorator4)).build());
        ConfiguredFeatures.register(featureRegisterable, FANCY_IRON_BEES, Feature.TREE, TreeConfiguredFeatures.fancyIron().decorators(List.of(beehiveTreeDecorator5)).build());
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registry(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(FirstMod.MOD_ID, id));
    }
}
