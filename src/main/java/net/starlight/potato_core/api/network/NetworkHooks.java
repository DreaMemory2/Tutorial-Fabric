package net.starlight.potato_core.api.network;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.function.Consumer;

public class NetworkHooks {
    /**
     * Request to open a GUI on the client, from the server
     *
     * Refer to {...} for how to provide a function to consume
     * these GUI requests on the client.
     *
     * @param player player The player to open the GUI for
     * @param containerSupplier containerSupplier A supplier of container properties including the registry name of the container
     */
    public static void openScreen(ServerPlayerEntity player, NamedScreenHandlerFactory containerSupplier){
        openScreen(player, containerSupplier, buf -> {});
    }
    /**
     * Request to open a GUI on the client, from the server
     *
     * Refer to {...} for how to provide a function to consume
     * these GUI requests on the client.
     *
     * @param player player The player to open the GUI for
     * @param containerSupplier containerSupplier A supplier of container properties including the registry name of the container
     * @param pos pos A block pos, which will be encoded into the auxillary data for this request
     */
    public static void openScreen(ServerPlayerEntity player, NamedScreenHandlerFactory containerSupplier, BlockPos pos){
        openScreen(player, containerSupplier, buf -> buf.writeBlockPos(pos));
    }
    /**
     * Request to open a GUI on the client, from the server
     *
     * Refer to {...} for how to provide a function to consume
     * these GUI requests on the client.
     *
     * The maximum size for #extraDataWriter is 32600 bytes.
     *
     * @param player The player to open the GUI for
     * @param containerSupplier A supplier of container properties including the registry name of the container
     * @param extraDataWriter Consumer to write any additional data the GUI needs
     */
    public static void openScreen(ServerPlayerEntity player, NamedScreenHandlerFactory containerSupplier, Consumer<PacketByteBuf> extraDataWriter){
        if(player.world.isClient) return;
        player.closeScreenHandler();
        // player.incrementScreenHandlerSyncId();
        // int openContainerId = player.screenHandlerSyncId;
        int openContainerId = 10;
        PacketByteBuf extraData = new PacketByteBuf(Unpooled.buffer());
        extraDataWriter.accept(extraData);
        extraData.readerIndex(0); // reset to beginning in case modders read for whatever reason

        PacketByteBuf output = new PacketByteBuf(Unpooled.buffer());
        output.writeVarInt(extraData.readableBytes());
        output.writeBytes(extraData);

        if(output.readableBytes() > 32600 || output.readableBytes() < 1) {
            throw new IllegalArgumentException("Invalid PacketBuffer for openGui, found " + + output.readableBytes()+ " bytes");
        }
        ScreenHandler c = containerSupplier.createMenu(openContainerId, player.getInventory(), player);
        ScreenHandlerType<?> type = c.getType();
        //PlayMessages.OpenContainer msg = new PlayMessages.OpenContainer(type, openContainerId, containerSupplier.getDisplayName(), output);
        //NetworkConstants.playChannel.sendTo(msg, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);

        //player.containerMenu = c;
        //player.initMenu(player.containerMenu);
        //MinecraftForge.EVENT_BUS.post(new PlayerContainerEvent.Open(player, c));
    }
}
