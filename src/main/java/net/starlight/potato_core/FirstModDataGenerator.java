package net.starlight.potato_core;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.starlight.potato_core.datagen.*;
import net.starlight.potato_core.world.feature.TreeConfiguredFeatures;
import net.starlight.potato_core.world.feature.TreePlacedFeatures;

/**
 * FirstMod生成器
 */
public class FirstModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModBlockTagGeneration::new);
        pack.addProvider(ModModelGenerator::new);
        pack.addProvider(ModChineseGenerator::new);
        pack.addProvider(ModLanguageGenerator::new);
        pack.addProvider(ModWorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        // 注册configured_feature和placed_feature的方法
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, TreeConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, TreePlacedFeatures::bootstrap);
    }
}
