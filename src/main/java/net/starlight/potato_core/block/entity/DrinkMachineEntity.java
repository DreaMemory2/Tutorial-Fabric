package net.starlight.potato_core.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.starlight.potato_core.register.ModBlockEntities;
import net.starlight.potato_core.screen.handler.DrinkMachineScreenHandler;
import org.jetbrains.annotations.Nullable;

/**
 * <p>实现简单的SidedInventory接口，重写默认的方法和获取物品的列表的方法</p>
 * <p>使用writeNbt和readNbt方法</p>
 *
 * @author Stalight
 * @version 1.0
 */
public class DrinkMachineEntity extends BlockEntity implements SidedInventory, NamedScreenHandlerFactory {
    /**
     * 物品栏的插槽数量
     */
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
    /**
     * 合成时进度给客户端进行同步
     */
    private final PropertyDelegate propertyDelegate;
    /**
     * 最小进度
     **/
    private int minProgress = 0;
    /**
     * 最大进度
     */
    private int maxProgress = 100;

    public DrinkMachineEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DRINK_MACHINE_ENTITY, pos, state);
        this.propertyDelegate = new DrinkMachinePropertyDelegate();
    }

    /*-- 实现SidedInventory接口 --*/

    /**
     * <p>获取该库存的物品的列表</p>
     * <p>每次调用返回的必须是相同的实例</p>
     * <p>DefaultedList有对应每个槽位下标[物品栏]</p>
     */
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    /**
     * <p>根据物品的列表创建一个Inventory</p>
     *
     * @param it
     public boolean fits(int var1, int var2);
     public static DefaultedList<ItemStack> of(DefaultedList<ItemStack> items) {
        return items;
    }

    /**
     * <p>更器大小创建一个新的Inventory</p>
     *
     * @param size Inventory的大小
     * @return 新的Inventory
     */
    public static DefaultedList<ItemStack> offSize(int size) {
        return of(DefaultedList.ofSize(size, ItemStack.EMPTY));
    }


    /**
     * <p>返回指定面的可自动化的可用slot</p>
     * <p>根据方块的六个面指定输出的物品或指定的输入插槽，例如熔炉</p>
     *
     * @param side 方块的面
     * @return 指定的输入插槽[输入存储槽]
     */
    @Override
    public int[] getAvailableSlots(Direction side) {
        // 插槽的数量
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return result;
    }

    /**
     * <p>如果物品可以从指定的面slot中插入特定的slot，返回true</p>
     *
     * @param slot  插槽
     * @param stack 物品
     * @param dir   面
     * @return 如果可以插入返回true
     */
    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return true;
    }

    /**
     * <p>如果物品可以从指定的面slot中提取出来</p>
     *
     * @param slot  插槽
     * @param stack 物品
     * @param dir   面
     * @return 如果可以提取返回true
     */
    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    /**
     * <p>返回Inventory的大小</p>
     * <p>默认实现返回的是getItems的大小</p>
     *
     * @return Inventory的大
     */
    @Override
    public int size() {
        return getItems().size();
    }

    /**
     * @return 判断Inventory包含空的stack，则返回true
     */
    @Override
    public boolean isEmpty() {
        for (int i = 0; i < size(); i++) {
            ItemStack itemStack = this.getStack(i);
            if (!itemStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取slot的物品
     *
     * @param slot 插槽
     * @return slot中的物品
     */
    @Override
    public ItemStack getStack(int slot) {
        return this.getItems().get(slot);
    }

    /**
     * <p>使用提供的ItemStack替换当前的ItemStack</p>
     *
     * @param slot  插槽
     * @param stack 物品的Stack
     */
    @Override
    public void setStack(int slot, ItemStack stack) {
        this.getItems().set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }
    }

    /**
     * <p>从slot中取出指定数量的物品</p>
     *
     * @param slot   插槽
     * @param amount 数量
     */
    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(this.getItems(), slot, amount);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }

    /**
     * <p>移除slot中的当前物品stack</p>
     *
     * @param slot 插槽
     * @return 返回移除物品stack
     */
    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.getItems(), slot);
    }

    /**
     * <p>清空Inventory</p>
     */
    @Override
    public void clear() {
        this.getItems().clear();
    }

    /**
     * 玩家是否可以使用方法
     */
    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }
    /*-- 实现NamedScreenHandlerFactory接口 --*/

    /**
     * <p>设置GUI界面的标题</p>
     */
    @Override
    public Text getDisplayName() {
        return Text.literal("Drink Craft Machine");
    }

    /**
     * <p>创建GUI界面</p>
     */
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new DrinkMachineScreenHandler(syncId, inv, this, this.propertyDelegate);
    }
    /*-- 实现BlockEntity方法 --*/

    /**
     * <p>读取NBT数据</p>
     *
     * @param nbt NBT数据
     */
    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        this.minProgress = nbt.getInt("drink_machine.progress");
    }

    /**
     * <p>写入NBT数据</p>
     *
     * @param nbt NBT数据
     */
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        nbt.putInt("drink_machine.progress", this.minProgress);
    }
    /*-- DrinkMachineEntity的方法 --*/

    /**
     * <p>重置当前的进度</p>
     */
    private void resetProgress() {
        this.minProgress = 0;
    }

    /**
     * <p>每个游戏刻都会对这个方法进行一个回调</p>
     * <p>功能：实体tick函数</p>
     * <p>参数：world：世界；pos：位置；state：状态；entity：实体</p>
     */
    public static void tick(World world, BlockPos pos, BlockState state, DrinkMachineEntity entity) {
        // 判断当前世界是否存在
        if (world.isClient) {
            return;
        }
        // 判断当前是否有合成表
        if (DrinkMachineEntity.hasRecipe(entity)) {
            // 如果合成表，则进行合成[进度开始]
            entity.minProgress++;
            markDirty(world, pos, state);
            // 如果进度达到最大值，则合成结束[进度结束]
            if (entity.minProgress >= entity.maxProgress) {
                // 生成合成后的物品
                DrinkMachineEntity.craftItem(entity);
            }
        } else {
            // 没有合成表，则清空当前的进度条
            entity.resetProgress();
            markDirty(world, pos, state);
        }
    }

    /**
     * <p>判断是否有合成表</p>
     */
    private static boolean hasRecipe(DrinkMachineEntity entity) {
        // 提供简单库存，插槽的数量为3
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            // 将当前的物品存入到简单库存中
            inventory.setStack(i, entity.getStack(i));
        }
        // 判断第一槽位的物品与合成表是否匹配
        boolean firstSlot = entity.getStack(0).getItem() == Items.APPLE;
        // 全部满足三种情况，则合成表继续
        return firstSlot && canInsertAmountIntOutputSlot(inventory)
                && canInsertItemIntOutputSlot(inventory, Items.APPLE);
    }

    /**
     * <p>判断第二槽位是否同一个物品，如果是，则进行输出</p>
     *
     * @return 如果是，则进行输出；如果不是，则不进行输出
     */
    private static boolean canInsertItemIntOutputSlot(SimpleInventory inventory, Item output) {
        // 如果同一物品，则输出物品，如果插槽内容为空，则可以输出
        return inventory.getStack(1).getItem() == output || inventory.getStack(1).isEmpty();
    }

    /**
     * <p>是否满足当前最大堆叠问题</p>
     *
     * @return 如果获取第二槽位的物品大于当前最大堆叠，则返回false；否则返回true
     */
    private static boolean canInsertAmountIntOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(1).getMaxCount() > inventory.getStack(1).getCount();
    }

    /**
     * <p>获取合成后的物品</p>
     */
    public static void craftItem(DrinkMachineEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            // 将当前的物品存入到简单库存中
            inventory.setStack(i, entity.getStack(i));
        }
        // 判断当前是否有合成表
        if (DrinkMachineEntity.hasRecipe(entity)) {
            // 清楚第一个插槽中的物品一个
            entity.removeStack(0, 1);
            // 生成合成后在第二插槽中的的物品
            entity.setStack(1, new ItemStack(Items.APPLE, entity.getStack(1).getCount() + 1));
            // 清除当前进度[归零0]
            entity.resetProgress();
        }
    }


    class DrinkMachinePropertyDelegate implements PropertyDelegate {
        /**
         * <p>获取同步的变量值</p>
         */
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return DrinkMachineEntity.this.minProgress;
                case 1:
                    return DrinkMachineEntity.this.maxProgress;
                default:
                    return 0;
            }
        }

        /**
         * <p>设置同步的变量值的值</p>
         */
        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    DrinkMachineEntity.this.minProgress = value;
                    break;
                case 1:
                    DrinkMachineEntity.this.maxProgress = value;
                    break;
            }
        }

        /**
         * <p>同步数量的值</p>
         */
        @Override
        public int size() {
            return 2;
        }
    }
}
