package net.starlight.potato_core.block;

import net.minecraft.block.*;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.starlight.potato_core.register.ModBlocks;

public abstract class CrystalSpreadableBlock extends Block {

    public CrystalSpreadableBlock(Settings settings) {
        super(settings);
    }

    /**
     * <p>判断该方块是否被掩埋</p>
     * @return 如果返回true，则不会转换为泥土；否则相反
     */
    private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        /* 获取该方块顶部的方块 */
        BlockPos topBlock = pos.up();
        /* 获取方块顶部默认状态 */
        BlockState block = world.getBlockState(topBlock);
        // 如果上方有一层雪，则不转换为泥土
        if (block.isOf(Blocks.SNOW) && block.get(SnowBlock.LAYERS) == 1) return true;
        // 如果液体状态等级为8，则转换为泥土
        if (block.getFluidState().getLevel() == 8) return false;
        // 判断光照等级是否可转换为泥土
        int lightLevel = ChunkLightProvider.getRealisticOpacity(world, state, pos, block, topBlock, Direction.UP, block.getOpacity(world, topBlock));
        return lightLevel < world.getMaxLightLevel();
    }

    /**
     * <p>判断方块是否可以传播</p>
     */
    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        /* 获取该方块顶部的方块 */
        BlockPos topBlock = pos.up();
        return CrystalSpreadableBlock.canSurvive(state, world, pos) && !world.getFluidState(topBlock).isIn(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        /* 如果方块被掩埋，则转换为泥土 */
        if (!CrystalSpreadableBlock.canSurvive(state, world, pos)) {
            world.setBlockState(pos, ModBlocks.CRYSAL_DIRT.getDefaultState());
            return;
        }
        /* 当光照等级大于等于9时，开始传播 */
        if (world.getLightLevel(pos.up()) >= 9) {
            BlockState blockState = this.getDefaultState();
            for (int i = 0; i < 4; ++i) {
                // 寻找在转为泥土的方块周围随机生成一个草方块
                BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (!world.getBlockState(blockPos).isOf(ModBlocks.CRYSAL_DIRT) || !CrystalSpreadableBlock.canSpread(blockState, world, blockPos)) continue;
                world.setBlockState(blockPos, blockState);
            }
        }
    }
}
