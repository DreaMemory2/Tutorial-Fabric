package net.starlight.potato_core.block;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.starlight.potato_core.block.entity.DrinkMachineEntity;
import net.starlight.potato_core.register.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class DrinkMachineBlock extends BlockWithEntity implements BlockEntityProvider {
    /** 方块属性 */
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public DrinkMachineBlock(Settings settings) {
        super(settings);
        // 设置默认的方向
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    /**
     * <p>设置方块放置时的状态</p>
     */
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()); // 1.19.3
        return this.getDefaultState().with(FACING, ctx.getSide().getOpposite());
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
            ExtendedScreenHandlerFactory screenHandlerFactory = (DrinkMachineEntity) world.getBlockEntity(pos);
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

    /**
     * <p>方块旋转方法：轴旋转</p>
     */
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    /**
     *  <p>方块旋转方法：镜像</p>
     */
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    /**
     * <p>添加方块属性</p>
     */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
