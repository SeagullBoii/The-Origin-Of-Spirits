package net.seagullboi.originofspirits.network.functions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;
import net.seagullboi.originofspirits.events.TOOSServerEvents;

import java.util.function.Supplier;

public class LeftSwingPacket {
    /*
   @credit Bioplethora - [missing link]
    */

    public LeftSwingPacket() {
    }

    public static void leftClickTrigger(LeftSwingPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        PlayerEntity player = context.get().getSender();
        if(player != null) {
            TOOSServerEvents.hitHandler(player, player.getHeldItem(Hand.MAIN_HAND));
        }
    }

    public static void encode(LeftSwingPacket message, PacketBuffer buffer) {
    }

    public static LeftSwingPacket decode(PacketBuffer buffer) {
        return new LeftSwingPacket();
    }
}
