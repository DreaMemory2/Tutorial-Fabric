package net.starlight.potato_core.block;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.starlight.potato_core.world.feature.TreeConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

/**
 * <p>树苗生成器</p>
 * <p>树苗生成树木Generator类</p>
 */
public class IronSaplingGenerator extends SaplingGenerator {
    @Nullable
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        if (random.nextInt(10) == 0) {
            return bees ? TreeConfiguredFeatures.FANCY_IRON_BEES : TreeConfiguredFeatures.FANCY_IRON;
        }
        return bees ? TreeConfiguredFeatures.IRON_TREE_BEES : TreeConfiguredFeatures.IRON_TREE;
    }
}
