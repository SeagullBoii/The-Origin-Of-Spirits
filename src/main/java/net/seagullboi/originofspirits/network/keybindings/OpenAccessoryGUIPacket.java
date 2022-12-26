package net.seagullboi.originofspirits.network.keybindings;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.seagullboi.originofspirits.gui.BaubleGUIGui;

import java.util.function.Supplier;

public class OpenAccessoryGUIPacket {
    public int key;

    public OpenAccessoryGUIPacket() {
    }

    public OpenAccessoryGUIPacket(int key) {
        this.key = key;
    }

    public static void encode(OpenAccessoryGUIPacket message, PacketBuffer buffer) {
        buffer.writeInt(message.key);
    }

    public static OpenAccessoryGUIPacket decode(PacketBuffer buffer) {
        return new OpenAccessoryGUIPacket(buffer.readInt());
    }

    public static void openGUI(OpenAccessoryGUIPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity player = context.getSender();
            World world = player.getEntityWorld();
            int x = (int) player.getPosX();
            int y = (int) player.getPosY();
            int z = (int) player.getPosZ();

            if (OriginofspiritsModVariables.MapVariables.get(world).hasAccessories) {
                    BlockPos pos = new BlockPos(x, y, z);
                    NetworkHooks.openGui(player, new INamedContainerProvider() {
                        @Override
                        public ITextComponent getDisplayName() {
                            return new StringTextComponent("BaubleGUI");
                        }

                        @Override
                        public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
                            return new BaubleGUIGui.GuiContainerMod(id, inventory, new PacketBuffer(Unpooled.buffer()).writeBlockPos(pos));
                        }
                    }, pos);
            }
        });
        context.setPacketHandled(true);
    }
}
