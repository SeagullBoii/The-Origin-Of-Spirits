package net.seagullboi.originofspirits.network;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.network.functions.LeftSwingPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.seagullboi.originofspirits.network.keybindings.OpenAccessoryGUIPacket;

public class TOOSNetwork {
    /*
    @credit Bioplethora - [missing link]
     */

    public static String NETWORK_VERSION = "0.1.2";
    private static int packetIndex = 0;

    public static final SimpleChannel CHANNEL = NetworkRegistry
            .newSimpleChannel(new ResourceLocation(OriginOfSpirits.MOD_ID, "network"), () -> NETWORK_VERSION,
                    version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

    public static void initializeNetwork() {
        CHANNEL.registerMessage(packetIndex++, LeftSwingPacket.class, LeftSwingPacket::encode, LeftSwingPacket::decode, LeftSwingPacket::leftClickTrigger);
        CHANNEL.registerMessage(packetIndex++, OpenAccessoryGUIPacket.class, OpenAccessoryGUIPacket::encode, OpenAccessoryGUIPacket::decode, OpenAccessoryGUIPacket::openGUI);

    }
}
