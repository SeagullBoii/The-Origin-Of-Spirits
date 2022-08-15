package net.seagullboi.originofspirits.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.item.TempestRunnerEggItem;

import java.util.Map;

public class TempestRunnerOnEntityTickUpdateProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure TempestRunnerOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure TempestRunnerOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure TempestRunnerOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure TempestRunnerOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure TempestRunnerOnEntityTickUpdate!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("eggTimer", (entity.getPersistentData().getDouble("eggTimer") + 1));
		if (entity.getPersistentData().getDouble("eggTimer") >= 800) {
			if (Math.random() < 0.3) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(TempestRunnerEggItem.block));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
			}
			entity.getPersistentData().putDouble("eggTimer", 0);
		}
	}
}
