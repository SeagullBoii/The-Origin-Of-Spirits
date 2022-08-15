package net.seagullboi.originofspirits.events;

import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.seagullboi.originofspirits.api.WorldGenUtils;
import net.seagullboi.originofspirits.registry.TOOSItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonEvents {

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if(event != null && event.getEntity() != null) {
            Entity entity = event.getEntity();
            Entity sourceEntity = event.getSource().getTrueSource();
            World world = entity.world;
            int x = (int) entity.getPosX();
            int y = (int) entity.getPosY();
            int z = (int) entity.getPosZ();

            boolean abyss = world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(entity.getPosition())).equals(WorldGenUtils.getCustomBiome(WorldGenUtils.ABYSS));
            boolean colorful_abyss = world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(entity.getPosition())).equals(WorldGenUtils.getCustomBiome(WorldGenUtils.COLORFUL_ABYSS));
            boolean biomeCheck = world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos(x, y, z))) != null && world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos(x, y, z))).equals(new ResourceLocation("originofspirits:abyss")) || world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos(x, y, z))) != null && world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos(x, y, z))).equals(new ResourceLocation("originofspirits:colorful_abyss"));
            if (biomeCheck && Math.random() < 0.6 && OriginofspiritsModVariables.WorldVariables.get(world).AbyssalFishing) {
                if (world instanceof World && !world.isRemote()) {
                    ItemEntity entityToSpawn = new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), new ItemStack(TOOSItems.ABYSSAL_ESSENCE.get()));
                    entityToSpawn.setPickupDelay(10);
                    world.addEntity(entityToSpawn);
                }
            }
        }
    }
}
