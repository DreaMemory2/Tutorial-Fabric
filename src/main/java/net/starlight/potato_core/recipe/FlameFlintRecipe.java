package net.starlight.potato_core.recipe;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.test.TestContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

import java.util.List;

/**
 * <p>自定义配方表</p>
 */
@Test(Result.FAIL)
public class FlameFlintRecipe implements Recipe<SimpleInventory> {
    private static final int BLOCK = 0;
    private static final int FLUID = 1;
    private static final int ITEM = 2;
    private boolean isCool;
    private Block inputBook;
    private Block outputBlock;
    private Fluid inputFluid;
    private Fluid onputFluid;
    public List<ItemStack> droppedItems;
    private TestContext world;
    private BlockPos pos;

    public FlameFlintRecipe(Identifier loc /*RecipeDef<EntropyRecipe> def */) {

    }

    public boolean matches(boolean isCool, Block block, Fluid fluid) {
        return isCool == this.isCool && (inputBook == block || inputFluid == fluid);
    }

    public void apply() {
        if (outputBlock != null) {
            world.setBlockState(pos, outputBlock.getDefaultState());
        }
        if (onputFluid != null) {
            world.setBlockState(pos, onputFluid.getDefaultState().getBlockState());
        }
        if (outputBlock == null && onputFluid == null) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        if (droppedItems != null) {
            // null
        }
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        return false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return null;
    }

    public ItemStack craft(SimpleInventory inventory) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return null;
    }

    public ItemStack getOutput() {
        return null;
    }

    @Override
    public Identifier getId() {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }
}
