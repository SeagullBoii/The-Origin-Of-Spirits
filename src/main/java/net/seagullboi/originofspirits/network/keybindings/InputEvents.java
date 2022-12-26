package net.seagullboi.originofspirits.network.keybindings;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.network.TOOSNetwork;
import net.seagullboi.originofspirits.registry.TOOSKeybinds;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {

    private static void onInput(Minecraft mc, int key, int action) {
        if (mc.currentScreen == null && TOOSKeybinds.openAccessoryMenu.isPressed()) {
            TOOSNetwork.CHANNEL.sendToServer(new OpenAccessoryGUIPacket(key));
        }
    }

    @SubscribeEvent
    public static void onKeyPressed(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world == null) {
            return;
        }
        onInput(mc, event.getKey(), event.getAction());
    }

    @SubscribeEvent
    public static void onMouseClicked(InputEvent.MouseInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world == null) return;
        onInput(mc, event.getButton(), event.getAction());
    }
}
