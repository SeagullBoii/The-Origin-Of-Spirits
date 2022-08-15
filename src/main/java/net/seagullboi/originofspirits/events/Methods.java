package net.seagullboi.originofspirits.events;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Methods {
    public static boolean isWorld(World world, ResourceLocation loc) {
        RegistryKey<World> world2 = world.getDimensionKey();
        if (world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, loc)) {
            return true;
        }
        return false;
    }
}
