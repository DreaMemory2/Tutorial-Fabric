package net.starlight.potato_core.screen.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.starlight.potato_core.screen.ModScreenHandlers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

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
public class KunBookScreenHandler extends ScreenHandler implements Supplier<Map<Integer, Slot>> {
    public final World world;
    public final PlayerEntity entity;
    public BlockPos pos;
    public Inventory inventory;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    public int x;
    public int y;
    public int z;

    public KunBookScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf){
        this(syncId, inventory, new SimpleInventory(0));
        if(buf != null){
            pos = buf.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
        }
    }

    public KunBookScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.KUN_SCREEN_HANDLER, syncId);
        this.entity = playerInventory.player;
        this.world = playerInventory.player.world;
        this.inventory = inventory;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public Map<Integer, Slot> get() {
        return customSlots;
    }
}
