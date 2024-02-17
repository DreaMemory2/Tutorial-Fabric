package net.starlight.potato_core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.register.ModBlocks;
import net.starlight.potato_core.register.ModFluids;
import net.starlight.potato_core.register.ModScreenHandlers;
import net.starlight.potato_core.screen.DrinkMachineScreen;
import net.starlight.potato_core.screen.KunBookScreen;

// Client客户端
public class FirstModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 注册GUI界面
        HandledScreens.register(ModScreenHandlers.KUN_SCREEN_HANDLER, KunBookScreen::new);
        HandledScreens.register(ModScreenHandlers.DRINK_MACHINE_SCREEN_HANDLER, DrinkMachineScreen::new);
        // 注册液体
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_PARADISE_WATER, ModFluids.FLOWING_PAREDISE_WATER,
                new SimpleFluidRenderHandler(new Identifier("minecraft:block/water_still"), new Identifier("minecraft:block/water_flow"), 0xA1E038D0));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_PARADISE_WATER, ModFluids.FLOWING_PAREDISE_WATER);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.IRON_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MAPLE_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.IRON_SAPLING, RenderLayer.getCutout());
    }
}
