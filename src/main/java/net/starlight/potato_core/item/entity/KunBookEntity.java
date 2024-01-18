package net.starlight.potato_core.item.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.starlight.potato_core.api.ContainerHelper;
import org.jetbrains.annotations.Nullable;

public class KunBookEntity implements SidedInventory {
    private final ItemStack stack;
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(0, ItemStack.EMPTY);

    public KunBookEntity(ItemStack stack){
        this.stack = stack;
        NbtCompound tag = stack.getSubNbt("Items");
        if(tag != null){
            ContainerHelper.loadAllItems(tag, items);
        }
    }

    public DefaultedList<ItemStack> getItems(){
        return items;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return result;
    }

    @Override
    public int getMaxCountPerStack() {
        return 1;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return true;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    @Override
    public int size() {
        return getItems().size();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < size(); i++) {
            ItemStack stack = getStack(i);
            if(!stack.isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return getItems().get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        // if(!result.isEmpty()) {...}
        return ContainerHelper.removeItem(getItems(), slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return ContainerHelper.takeItem(getItems(), slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if( stack.getCount() > getMaxCountPerStack()){
            stack.setCount(getMaxCountPerStack());
        }
    }

    @Override
    public void markDirty() {
        NbtCompound tag = stack.getOrCreateSubNbt("Items");
        ContainerHelper.saveAllItems(tag, items);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        getItems().clear();
    }
}
