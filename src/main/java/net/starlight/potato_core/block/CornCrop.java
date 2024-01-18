package net.starlight.potato_core.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.starlight.potato_core.register.ModItems;

public class CornCrop extends CropBlock {
    public static final IntProperty AGE = IntProperty.of("age", 0, 3);
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),  // 0
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 32.0, 16.0), // 1
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 32.0, 16.0), // 2
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 32.0, 16.0), // 3
    };
    public CornCrop(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[state.get(this.getAgeProperty())];
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.CORN_SEEDS;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
