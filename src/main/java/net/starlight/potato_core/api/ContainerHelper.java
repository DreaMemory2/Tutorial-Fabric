package net.starlight.potato_core.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.collection.DefaultedList;

import java.util.List;

public class ContainerHelper {

    public static ItemStack removeItem(List<ItemStack> list, int i, int j) {
        if (i < 0 || i >= list.size() || list.get(i).isEmpty() || j <= 0) {
            return ItemStack.EMPTY;
        }
        return list.get(i).split(j);
    }

    public static ItemStack takeItem(List<ItemStack> list, int i) {
        if (i < 0 || i >= list.size()) {
            return ItemStack.EMPTY;
        }
        return list.set(i, ItemStack.EMPTY);
    }

    public static NbtCompound saveAllItems(NbtCompound compoundTag, DefaultedList<ItemStack> nonNullList) {
        return ContainerHelper.saveAllItems(compoundTag, nonNullList, true);
    }

    public static NbtCompound saveAllItems(NbtCompound compoundTag, DefaultedList<ItemStack> nonNullList, boolean bl) {
        NbtList listTag = new NbtList();
        for (int i = 0; i < nonNullList.size(); ++i) {
            ItemStack itemStack = nonNullList.get(i);
            if (itemStack.isEmpty()) continue;
            NbtCompound compoundTag2 = new NbtCompound();
            compoundTag2.putByte("Slot", (byte)i);
            itemStack.writeNbt(compoundTag2);
            listTag.add(compoundTag2);
        }
        if (!listTag.isEmpty() || bl) {
            compoundTag.put("Items", listTag);
        }
        return compoundTag;
    }

    public static void loadAllItems(NbtCompound compoundTag, DefaultedList<ItemStack> nonNullList) {
        NbtList listTag = compoundTag.getList("Items", 10);
        for (int i = 0; i < listTag.size(); ++i) {
            NbtCompound compoundTag2 = listTag.getCompound(i);
            int j = compoundTag2.getByte("Slot") & 0xFF;
            if (j < 0 || j >= nonNullList.size()) continue;
            nonNullList.set(j, ItemStack.fromNbt(compoundTag2));
        }
    }
}
