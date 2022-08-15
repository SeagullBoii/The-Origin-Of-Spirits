package net.seagullboi.originofspirits.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IConsumesMana {
     int getManaConsumed();

     default void consumeMana(PlayerEntity player, World worldIn) {

    }

}
