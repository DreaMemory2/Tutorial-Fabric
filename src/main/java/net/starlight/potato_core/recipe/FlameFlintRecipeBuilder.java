package net.starlight.potato_core.recipe;

import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Test(Result.FAIL)
public class FlameFlintRecipeBuilder {
    public Identifier id;
    private FlameFlintMode mode;

    private Block inputBlock;
    private Block outputBlock;
    private Fluid inputFluid;
    private Fluid outputFluid;
    private List<ItemStack> stacks;

    public FlameFlintRecipeBuilder cool(Identifier id) {
        return new FlameFlintRecipeBuilder().setId(id).setMode(FlameFlintMode.COOL);
    }
    private FlameFlintRecipeBuilder setMode(FlameFlintMode mode) {
        this.mode = Objects.requireNonNull(mode, "mode must not be null");
        return this;
    }

    private FlameFlintRecipeBuilder setId(Identifier id) {
        Preconditions.checkState(id != null);
        this.id = id;
        return this;
    }

    public FlameFlintRecipeBuilder inputBlock(Block inputBlock) {
        this.inputBlock = Objects.requireNonNull(inputBlock, "inputBlock must not be null");
        return this;
    }

    public FlameFlintRecipeBuilder inputFluid(Fluid inputFluid) {
        this.inputFluid = Objects.requireNonNull(inputFluid, "inputFluid must not be null");
        return this;
    }

    public FlameFlintRecipeBuilder output(ItemStack... stacks) {
        return output(Arrays.asList(stacks));
    }

    private FlameFlintRecipeBuilder output(List<ItemStack> stacks) {
        Preconditions.checkArgument(!stacks.isEmpty(), "drops needs to be a non empty list when set");

        this.stacks = stacks;
        return this;
    }

    public FlameFlintRecipeBuilder outputBlock(Block block) {
        this.outputBlock = Objects.requireNonNull(block, "outputBlock must not be null");
        return this;
    }

    public FlameFlintRecipeBuilder setOutputFluid(Fluid outputFluid) {
        this.outputFluid = Objects.requireNonNull(outputFluid, "outputFluid must not be null");
        return this;
    }

    public FlameFlintRecipeBuilder parseBlock() {
        return null;
    }
    /*
    public EntropyRecipeBuilder addBlockStateAppliers(StateApplier<?> applier) {
        Preconditions.checkState(this.outputBlock != null,
                "Can only add appliers when an output block is present.");

        if (this.outputBlockStateAppliers.isEmpty()) {
            this.outputBlockStateAppliers = new ArrayList<>();
        }

        this.outputBlockStateAppliers.add(applier);

        return this;
    }
     */

    private void setType(boolean input, String type) {
        // null
    }
}
