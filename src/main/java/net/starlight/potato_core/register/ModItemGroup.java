package net.starlight.potato_core.register;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;

public class ModItemGroup {
    // 方法一:
    /*
    * public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(FirstMod.MOD_ID, "item")
    ).icon(new Supplier<ItemStack>() {
             @Override
             public ItemStack get() {
                 return new ItemStack(ModItems.CHICKEN_SOUP);
             }
    }).build();
     */
    public static final ItemGroup ITEMS = FabricItemGroup.builder(new Identifier(FirstMod.MOD_ID, "potato_item")).icon(() ->
            new ItemStack(ModItems.CHICKEN_SOUP)).build();
    public static final ItemGroup BLOCKS = FabricItemGroup.builder(new Identifier(FirstMod.MOD_ID, "potato_block")).icon(() ->
            new ItemStack(Blocks.STONE)).build();
    public static final ItemGroup BUILDING = FabricItemGroup.builder(new Identifier(FirstMod.MOD_ID, "potato_building")).icon(() ->
            new ItemStack(ModBlocks.WOOL_1X)).build();
}
