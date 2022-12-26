package net.seagullboi.originofspirits.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;

public class GlobalVarUtil {

    public static int getMana(PlayerEntity entity) {
        return (int) (entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana;
    }

    public static int getMaxMana(PlayerEntity entity) {
        return (int) (entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new OriginofspiritsModVariables.PlayerVariables())).MaxMana;
    }

    public static int getManaCooldown(PlayerEntity entity) {
        return (int) (entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new OriginofspiritsModVariables.PlayerVariables())).ManaCooldown;
    }

    public static void setMana(PlayerEntity entity, int manaCount) {
        entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.Mana = manaCount;
            capability.syncPlayerVariables(entity);
        });

    }
    public static void setManaCooldown(PlayerEntity entity, int cooldown) {
        entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.ManaCooldown = cooldown;
            capability.syncPlayerVariables(entity);
        });
    }

    public static boolean gotBag(PlayerEntity entity) {
        return (entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new OriginofspiritsModVariables.PlayerVariables())).gotBag;
    }

    public static void setGotBag(PlayerEntity entity, boolean value) {
        entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.gotBag = value;
            capability.syncPlayerVariables(entity);
        });
    }

    public static boolean getSpite(World world) {
        return OriginofspiritsModVariables.WorldVariables.get(world).Spite;
    }

    public static void setSpite(World world, boolean value) {
        OriginofspiritsModVariables.WorldVariables.get(world).Spite = value;
        OriginofspiritsModVariables.WorldVariables.get(world).syncData(world);
    }

}
