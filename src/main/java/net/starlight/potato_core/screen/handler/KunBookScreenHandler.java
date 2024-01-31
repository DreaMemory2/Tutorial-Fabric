package net.starlight.potato_core.screen.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.starlight.potato_core.register.ModScreenHandlers;

// Kun书处理程序
/**
 * Forge与Fabric的关系
 * AbstractContainerMenu 相当与 ScreenHandler
 * Inventory 相当于 PlayerInventory
 * Container 相当于 Inventory
 * FriendlyByteBuf 相当于 PacketByteBuf
 * Level 相当于 World
 * Player 相当于 PlayerEntity;
 */
public class KunBookScreenHandler extends ScreenHandler {

    public KunBookScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf){
        this(syncId, inventory, new SimpleInventory(0));
    }

    public KunBookScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.KUN_SCREEN_HANDLER, syncId);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }
}
