package net.starlight.potato_core.screen.handler;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.starlight.potato_core.block.entity.DrinkMachineEntity;
import net.starlight.potato_core.register.ModScreenHandlers;
import net.starlight.potato_core.util.FluidStack;

/**
 * <p>创建DrinkMachine相关界面[处理器]</p>
 */
public class DrinkMachineScreenHandler extends ScreenHandler {
    /**
     * 方块实体
     */
    public final DrinkMachineEntity blockEntity;
    /**
     * 方块库存，方块容器
     */
    private final Inventory inventory;
    /**
     * 进度信息
     */
    private final PropertyDelegate propertyDelegate;
    /**
     * 流体类型
     */
    public FluidStack fluidStack;

    /**
     * <p>方块界面注册方法</p>
     *
     * @param syncId          同步Id
     * @param playerInventory 玩家的库存
     */
    public DrinkMachineScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(buf.readBlockPos()), new ArrayPropertyDelegate(2));
    }

    /**
     * @param syncId           同步Id
     * @param playerInventory  玩家的库存
     * @param entity           方块实体
     * @param propertyDelegate 进度信息
     */
    public DrinkMachineScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity entity, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.DRINK_MACHINE_SCREEN_HANDLER, syncId);
        this.inventory = (Inventory) entity;
        this.propertyDelegate = propertyDelegate;
        this.blockEntity = (DrinkMachineEntity) entity;
        this.fluidStack = new FluidStack(blockEntity.fluidStorage.variant, blockEntity.fluidStorage.amount);

        // 检查方块库存的大小是否比预期方块大小一样大
        ScreenHandler.checkSize(inventory, 5);
        // 打开玩家库存
        inventory.onOpen(playerInventory.player);
        // 设置插槽，与GUI的插槽位置对应
        // 液体插槽 已实现
        this.addSlot(new Slot(inventory, 0, 37, 59));
        // 输入插槽 已实现
        this.addSlot(new Slot(inventory, 1, 37, 31));
        // 输出插槽 已实现
        this.addSlot(new Slot(inventory, 2, 85, 31));
        // 加热插槽 未实现
        this.addSlot(new Slot(inventory, 3, 118, 20));
        // 冷却插槽 未实现
        this.addSlot(new Slot(inventory, 4, 118, 52));

        // 设置玩家库存的插槽
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        // 设置进度数值
        addProperties(propertyDelegate);
    }

    /**
     * @return 判断当前是否在制作中，合成中
     */
    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    /**
     * <p>获取当前进度，范围从0到100%</p>
     *
     * @return 当前进度的数值
     */
    public int getScaledProgress() {
        // 当前进度，或最小进度
        int minProgress = propertyDelegate.get(0);
        int maxProgress = propertyDelegate.get(1);
        // 像素点
        int progressArrowSize = 24;
        return maxProgress != 0 && minProgress != 0 ? progressArrowSize * minProgress / maxProgress : 0;
    }

    /**
     * <p>表示：玩家按住Shift键点击物品时，快速从玩家的背包添加到机器上</p>
     */
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newItemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalItemStack = slot.getStack();
            newItemStack = originalItemStack.copy();

            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalItemStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalItemStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalItemStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newItemStack;
    }

    /**
     * <p>玩家是否能够使用方块</p>
     */
    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    /**
     * <p>玩家点击方块时触发</p>
     * <p>添加玩家背包</p>
     */
    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int l = 0; l < 9; l++) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    /**
     * <p>添加玩家快捷栏</p>
     */
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    /*-- 设置液体界面 -*/

    /**
     * <p>设置流体槽</p>>
     *
     * @param stack 流体类型
     */
    public void setFuild(FluidStack stack) {
        this.fluidStack = stack;
    }
}
