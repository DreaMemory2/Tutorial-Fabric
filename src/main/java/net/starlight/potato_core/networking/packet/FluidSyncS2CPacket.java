package net.starlight.potato_core.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.starlight.potato_core.block.entity.DrinkMachineEntity;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;
import net.starlight.potato_core.screen.handler.DrinkMachineScreenHandler;
import net.starlight.potato_core.util.FluidStack;

@Test(Result.UNKNOWN)
public class FluidSyncS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        FluidVariant variant = FluidVariant.fromPacket(buf);
        long fluidLevel = buf.readLong();
        BlockPos position = buf.readBlockPos();

        if (client.world.getBlockEntity(position) instanceof DrinkMachineEntity entity) {
            entity.setFluidLevel(variant, fluidLevel);

            if (client.player.currentScreenHandler instanceof DrinkMachineScreenHandler screenHandler &&
                    screenHandler.blockEntity.getPos().equals(position)) {
                entity.setFluidLevel(variant, fluidLevel);
                screenHandler.setFuild(new FluidStack(variant, fluidLevel));
            }
        }
    }
}
