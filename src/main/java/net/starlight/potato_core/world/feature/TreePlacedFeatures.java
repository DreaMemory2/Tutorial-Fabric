package net.starlight.potato_core.world.feature;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.starlight.potato_core.register.ModBlocks;

public class TreePlacedFeatures {
    // Iron Tree 的4种地物
    public static final RegistryKey<PlacedFeature> IRON_CHECKED = registry("iron_checked");
    public static final RegistryKey<PlacedFeature> IRON_TREE_BEES = registry("iron_tree_bees");
    public static final RegistryKey<PlacedFeature> FANCY_IRON = registry("fancy_iron");
    public static final RegistryKey<PlacedFeature> FANCY_IRON_BEES = registry("fancy_iron_bees");

    public static RegistryKey<PlacedFeature> registry(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(id));
    }

    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry.Reference<ConfiguredFeature<?, ?>> iron_tree = registryEntryLookup.getOrThrow(TreeConfiguredFeatures.IRON_TREE);
        RegistryEntry.Reference<ConfiguredFeature<?, ?>> iron_tree_bees = registryEntryLookup.getOrThrow(TreeConfiguredFeatures.IRON_TREE_BEES);
        RegistryEntry.Reference<ConfiguredFeature<?, ?>> fancy_tree = registryEntryLookup.getOrThrow(TreeConfiguredFeatures.FANCY_IRON);
        RegistryEntry.Reference<ConfiguredFeature<?, ?>> fancy_iron_bees = registryEntryLookup.getOrThrow(TreeConfiguredFeatures.FANCY_IRON_BEES);
        PlacedFeatures.register(featureRegisterable, IRON_CHECKED, iron_tree, PlacedFeatures.wouldSurvive(ModBlocks.IRON_SAPLING));
        PlacedFeatures.register(featureRegisterable, IRON_TREE_BEES, iron_tree_bees, PlacedFeatures.wouldSurvive(ModBlocks.IRON_SAPLING));
        PlacedFeatures.register(featureRegisterable, FANCY_IRON, fancy_tree, PlacedFeatures.wouldSurvive(ModBlocks.IRON_SAPLING));
        PlacedFeatures.register(featureRegisterable, FANCY_IRON_BEES, fancy_iron_bees, PlacedFeatures.wouldSurvive(ModBlocks.IRON_SAPLING));
    }
}
