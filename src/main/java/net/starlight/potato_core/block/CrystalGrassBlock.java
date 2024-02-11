package net.starlight.potato_core.block;

import net.minecraft.block.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import java.util.List;
import java.util.Optional;

public class CrystalGrassBlock extends GrassBlock {
    private final Block block;

    public CrystalGrassBlock(Block block) {
        super(Settings.copy(Blocks.GRASS_BLOCK));
        this.block = block;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
        BlockState blockState = block.getDefaultState();
        Optional<RegistryEntry.Reference<PlacedFeature>> optional = world.getRegistryManager().get(RegistryKeys.PLACED_FEATURE).getEntry(VegetationPlacedFeatures.GRASS_BONEMEAL);

        block0:
        for (int i = 0; i < 128; ++i) {
            RegistryEntry<PlacedFeature> registryEntry;
            BlockPos blockPos2 = blockPos;
            for (int j = 0; j < i / 16; ++j) {
                boolean result = !world.getBlockState((blockPos2 = blockPos2.add(
                        random.nextInt(3) - 1,
                        (random.nextInt(3) - 1) * random.nextInt(3) / 2,
                        random.nextInt(3) - 1)).down()).isOf(this) ||
                        world.getBlockState(blockPos2).isFullCube(world, blockPos2);
                if (result) continue block0;
            }
            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.isOf(blockState.getBlock()) && random.nextInt(10) == 0)
                ((Fertilizable) blockState.getBlock()).grow(world, random, blockPos2, blockState2);
            if (!blockState2.isAir()) continue;
            if (random.nextInt(8) == 0) {
                List<ConfiguredFeature<?, ?>> list = world.getBiome(blockPos2).value().getGenerationSettings().getFlowerFeatures();
                if (list.isEmpty()) continue;
                registryEntry = ((RandomPatchFeatureConfig) list.get(0).config()).feature();
            } else {
                if (optional.isEmpty()) continue;
                registryEntry = optional.get();
            }
            registryEntry.value().generateUnregistered(world, world.getChunkManager().getChunkGenerator(), random, blockPos2);
        }
    }
}
