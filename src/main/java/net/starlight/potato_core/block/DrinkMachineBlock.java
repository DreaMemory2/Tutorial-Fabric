package net.starlight.potato_core.block;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.starlight.potato_core.block.entity.DrinkMachineEntity;
import net.starlight.potato_core.register.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class DrinkMachineBlock extends BlockWithEntity implements BlockEntityProvider {
    public DrinkMachineBlock(Settings settings) {
        super(settings);
    }

    /**
     * <p>方块渲染类型</p>
     * <p>作用：避免方块实体放置后材质渲染问题</p>
     */
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    /**
     * <p>方块状态变化信息的方法</p>
     */
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        // 如果当前的方块状态不等于新方块的状态时，返回true
        // 比如方块位置发生移动时；该方块移除，破坏时。
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DrinkMachineEntity entity) {
                // 掉落方块容器中的物品
                ItemScatterer.spawn(world, pos, entity);
                // 更新周围比较器，发送红石信号
                world.updateComparators(pos, this);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    /**
     * <p>对方快右键时</p>
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = (DrinkMachineEntity) world.getBlockEntity(pos);
            // 打开GUI
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    /**
     * <p>创建方块实体</p>
     */
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        // 创建方块实体
        // 返回DrinkMachineEntity方块实体对象
        return new DrinkMachineEntity(pos, state);
    }

    /**
     * <p>获取方块实体中的tick方法</p>
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.DRINK_MACHINE_ENTITY, DrinkMachineEntity::tick);
    }
}
