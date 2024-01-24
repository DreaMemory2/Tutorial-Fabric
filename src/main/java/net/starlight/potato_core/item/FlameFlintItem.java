package net.starlight.potato_core.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

@Test(Result.USELESS)
public class FlameFlintItem extends Item {

    private static String CUR_POWER_NBT = "curPower";
    private static final int MAX_POWER = 50;
    private static final int BAR_COLOR = MathHelper.hsvToRgb(1.0f / 3.0f, 1.0f, 1.0f);

    public FlameFlintItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return BAR_COLOR;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        double filled = this.getPower(stack) / (double) MAX_POWER;
        return MathHelper.clamp((int) (filled * 13), 0, 13);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        BlockHitResult result = Item.raycast(world, player, RaycastContext.FluidHandling.ANY);
        if (result.getType() != HitResult.Type.BLOCK) {
            return new TypedActionResult<>(ActionResult.FAIL, player.getStackInHand(hand));
        }
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (!state.getFluidState().isEmpty()) {
            useOnBlock(new ItemUsageContext(player, hand, result));
        }
        return new TypedActionResult<>(ActionResult.success(world.isClient), player.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack stack = context.getStack();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        Direction face = context.getPlayerFacing();
        int power = this.getPower(stack);
        if (power == 0) {
            return ActionResult.PASS;
        }
        if (player == null) {
            return ActionResult.FAIL;
        }
        BlockHitResult result = Item.raycast(world, player, RaycastContext.FluidHandling.ANY);
        if (result.getType() == HitResult.Type.BLOCK) {
            pos = result.getBlockPos();
        }
        if (!player.canPlaceOn(pos, face, stack)) {
            return ActionResult.FAIL;
        }
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        return tryApplyEffect(world, stack, pos, player, face) ? ActionResult.CONSUME : ActionResult.FAIL;
    }

    private boolean tryApplyEffect(World world, ItemStack stack, BlockPos pos, PlayerEntity player, Direction face) {
        player.sendMessage(Text.literal(world.getBlockState(pos).toString()));
        extractPower(stack);
        return true;
    }


    private void extractPower(ItemStack stack) {
        setPower(stack, getPower(stack) - 1);
    }

    private int getPower(ItemStack stack) {
        NbtCompound tag = stack.getNbt();
        if (tag != null) {
            return tag.getInt(CUR_POWER_NBT);
        }
        return MAX_POWER;
    }

    private void setPower(ItemStack stack, int p) {
        stack.getOrCreateNbt().putInt(CUR_POWER_NBT, p);
    }
}
