package net.seagullboi.originofspirits.registry;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.seagullboi.originofspirits.OriginOfSpirits;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class TOOSKeybinds {

    public static KeyBinding openAccessoryMenu;

    public static void register(final FMLClientSetupEvent event) {
        openAccessoryMenu = create("open_accessory_gui", GLFW.GLFW_KEY_K);

        ClientRegistry.registerKeyBinding(openAccessoryMenu);
    }

    private static KeyBinding create(String name,  int key) {
        return new KeyBinding("key." + OriginOfSpirits.MOD_ID + "." + name, key, "key.categories.toos");
    }

}
