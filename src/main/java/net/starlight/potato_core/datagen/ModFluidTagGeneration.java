package net.starlight.potato_core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.FluidTags;
import net.starlight.potato_core.register.ModFluids;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagGeneration extends FabricTagProvider.FluidTagProvider {
    public ModFluidTagGeneration(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(FluidTags.WATER)
                .add(ModFluids.STILL_PARADISE_WATER)
                .add(ModFluids.FLOWING_PAREDISE_WATER);
    }
}
