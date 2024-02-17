package net.starlight.potato_core.register;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.starlight.potato_core.world.feature.TreePlacedFeatures;

public class ModGeneration {

    public static void registerGeneration() {
        ModGeneration.generateTrees();
    }

    /**
     * <p>创建树的生成器，生成的维度和生态群系</p>
     */
    private static void generateTrees() {
        // 枫树只生成在森林生态群系
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION, TreePlacedFeatures.MAPLE_TREE);
    }
}
