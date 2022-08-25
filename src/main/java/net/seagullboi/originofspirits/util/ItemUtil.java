package net.seagullboi.originofspirits.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class ItemUtil {
    public static boolean checkCooldownUsable(LivingEntity entity, ItemStack stack) {
        if (entity instanceof PlayerEntity) {
            return !((PlayerEntity) entity).getCooldownTracker().hasCooldown(stack.getItem());
        } else {
            return true;
        }
    }
}
