package net.seagullboi.originofspirits.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.seagullboi.originofspirits.api.IReachWeapon;
import net.seagullboi.originofspirits.network.TOOSNetwork;
import net.seagullboi.originofspirits.network.functions.LeftSwingPacket;

@Mod.EventBusSubscriber
public class TOOSServerEvents {
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
