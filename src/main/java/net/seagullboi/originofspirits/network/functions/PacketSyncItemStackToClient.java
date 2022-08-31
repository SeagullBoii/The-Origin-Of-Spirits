package net.seagullboi.originofspirits.network.functions;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.items.ItemStackHandler;
import net.seagullboi.originofspirits.api.IInventoryHandlingBlockEntity;
import net.seagullboi.originofspirits.block.tile_entities.PressingMachineTileEntity;

import java.util.function.Supplier;

public class PacketSyncItemStackToClient {
    private final ItemStackHandler itemStackHandler;
    private final BlockPos pos;

    public PacketSyncItemStackToClient(ItemStackHandler stack, BlockPos pos) {
        this.itemStackHandler = stack;
        this.pos = pos;
    }

    public PacketSyncItemStackToClient(PacketBuffer buffer) {
        itemStackHandler = new ItemStackHandler(1);
            World world = Minecraft.getInstance().world;
            this.pos = buffer.readBlockPos();
            itemStackHandler.insertItem(0, ((PressingMachineTileEntity) world.getTileEntity(pos)).itemHandler.getStackInSlot(0), false);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT YES
            if(Minecraft.getInstance().world.getTileEntity(pos) instanceof IInventoryHandlingBlockEntity) {
                IInventoryHandlingBlockEntity blockEntity = (IInventoryHandlingBlockEntity) Minecraft.getInstance().world.getTileEntity(pos);
                blockEntity.setHandler(this.itemStackHandler);
            }
        });
        return true;
    }

    public static void encode(LeftSwingPacket message, PacketBuffer buffer) {
    }
}