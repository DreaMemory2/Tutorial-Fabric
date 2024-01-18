package net.starlight.potato_core.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.fluid.ParadiseFluid;

/**
 * 液体分为两类：一类是静态液体(类似方块)，另一类是动态液体
 */
public class ModFluids {
    public static FlowableFluid STILL_PARADISE_WATER;
    public static FlowableFluid FLOWING_PAREDISE_WATER;
    public static Block PAREDISE_WATER_BLOCK;
    public static Item PAREDISE_WATER_BUCKET;

    public static void registerFluids() {
        STILL_PARADISE_WATER = Registry.register(Registries.FLUID, new Identifier(FirstMod.MOD_ID, "paradise_water"), new ParadiseFluid.Still());
        FLOWING_PAREDISE_WATER = Registry.register(Registries.FLUID, new Identifier(FirstMod.MOD_ID, "flowing_paradise_water"), new ParadiseFluid.Flowing());

        PAREDISE_WATER_BLOCK = Registry.register(Registries.BLOCK, new Identifier(FirstMod.MOD_ID, "paradise_water_block"), new FluidBlock(ModFluids.STILL_PARADISE_WATER, FabricBlockSettings.copyOf(Blocks.WATER)){ });
        PAREDISE_WATER_BUCKET = Registry.register(Registries.ITEM, new Identifier(FirstMod.MOD_ID, "paradise_water_bucket"), new BucketItem(ModFluids.STILL_PARADISE_WATER, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.ITEMS).register(entries -> entries.add(PAREDISE_WATER_BUCKET));
    }
}
