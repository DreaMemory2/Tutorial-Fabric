package net.starlight.potato_core.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;
import net.starlight.potato_core.register.ModItems;
import net.starlight.potato_core.register.ModTags;
import net.starlight.potato_core.util.InventoryUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Test(Result.UNKNOWN)
public class DowsingRodItem extends Item {

    public DowsingRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState blockBelow = context.getWorld().getBlockState(positionClicked.down(i));

                if (isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(positionClicked.down(i), player, blockBelow.getBlock());
                    foundBlock = true;

                    if(InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET)) {
                        addNbtToDataTablet(player, positionClicked.add(0, -i, 0), blockBelow.getBlock());
                    }

                    spawnFoundParticles(context, positionClicked);

                    context.getWorld().playSound(player, positionClicked, SoundEvents.BLOCK_STONE_BREAK,
                            SoundCategory.BLOCKS, 1f, 1f);

                    break;
                }
            }

            if(!foundBlock) {
                player.sendMessage(Text.translatable("item.potato_core.dowsing_rod.no_found"), false);
            }
        }

        context.getStack().damage(1, context.getPlayer(),
                (player) -> player.sendToolBreakStatus(player.getActiveHand()));

        return super.useOnBlock(context);
    }

    private boolean isValuableBlock(BlockState state) {
        return state.isIn(ModTags.ORE_BLOCKS);
    }

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block blockBelow) {
        player.sendMessage(Text.literal("Found " + blockBelow.asItem().getName().getString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"), false);
    }

    private void spawnFoundParticles(ItemUsageContext pContext, BlockPos positionClicked) {
        for(int i = 0; i < 360; i++) {
            if(i % 20 == 0) {
                pContext.getWorld().addParticle(ParticleTypes.COMPOSTER,
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.25d, 0.15d, Math.sin(i) * 0.25d);
            }
        }
    }

    /**
     * <p>将获取的Nbt信息传入Data Tablet物品里</p>
     * @param player 玩家
     * @param pos 方块位置信息
     * @param blockBelow 方块信息
     */
    private void addNbtToDataTablet(PlayerEntity player, BlockPos pos, Block blockBelow) {
        // 从玩家物品栏上获取物品，通过第一个物品下标来判断Data Tablet物品是否下玩家快捷栏上
        int inventory = InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET);
        ItemStack dataTablet = player.getInventory().getStack(inventory);
        // 然后创建新的Nbt标签
        NbtCompound nbtData = new NbtCompound();
        // 设置Nbt工具Nbt信息
        // 效果如下：Found Coal Ore at (-591, 36, -873)
        nbtData.putString("potato_core.last.block", "Found " + blockBelow.asItem().getName().getString() + " at ( " +
                pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + " )");

        dataTablet.setNbt(nbtData);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.potato_core.dowsing_rod.tooltip.shift"));
        } else {
            tooltip.add(Text.translatable("item.potato_core.dowsing_rod.tooltip"));
        }
    }
}
