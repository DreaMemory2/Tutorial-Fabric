package net.starlight.potato_core.recipe;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.starlight.potato_core.api.Result;
import net.starlight.potato_core.api.Test;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * <p>自定义配方表</p>
 */
@Test(Result.FAIL)
public class FlameFlintRecipe implements Recipe<SimpleInventory> {
    private static final int BLOCK = 0;
    public static final RecipeType<FlameFlintRecipe> TYPE = RecipeType.register("flame_flint");
    private boolean isCool;
    @Nullable
    private Block inputBook;
    @Nullable
    private Block outputBlock;
    @Nullable
    private Fluid inputFluid;
    @Nullable
    private Fluid onputFluid;
    public World world;
    public BlockPos pos;
    public List<ItemStack> droppedItems;


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
    public ItemStack craft(SimpleInventory inventory) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
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
