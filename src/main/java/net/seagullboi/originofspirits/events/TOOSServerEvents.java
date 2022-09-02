package net.seagullboi.originofspirits.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.seagullboi.originofspirits.api.IReachWeapon;
import net.seagullboi.originofspirits.api.WorldGenUtils;
import net.seagullboi.originofspirits.network.TOOSNetwork;
import net.seagullboi.originofspirits.network.functions.LeftSwingPacket;
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
public class TOOSServerEvents {

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

      /*
    @credit Bioplethora - [missing link]
     */

    @SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickEmpty event) {

        hitHandler(event.getPlayer(), event.getItemStack());

        if (event.getItemStack().getItem() instanceof IReachWeapon) {
            if (event.getWorld().isRemote()) {
                TOOSNetwork.CHANNEL.sendToServer(new LeftSwingPacket());
            }
        }
    }
    public static void hitHandler(PlayerEntity entity, ItemStack stack) {

        if (stack.getItem() instanceof IReachWeapon) {

            double range = ((IReachWeapon) stack.getItem()).getReachDistance();
            double distance = range * range;
            Vector3d vec = entity.getEyePosition(1);
            Vector3d vec1 = entity.getLook(1);
            Vector3d targetVec = vec.add(vec1.x * range, vec1.y * range, vec1.z * range);
            AxisAlignedBB aabb = entity.getBoundingBox().expand(vec1.scale(range)).grow(4.0D, 4.0D, 4.0D);
            EntityRayTraceResult result = ProjectileHelper.rayTraceEntities(entity, vec, targetVec, aabb, EntityPredicates.CAN_AI_TARGET, distance);

            if ((result != null ? result.getEntity() : null) != null) {
                entity.attackTargetEntityWithCurrentItem(result.getEntity());
            }
        }
    }
}
