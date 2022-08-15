package net.seagullboi.originofspirits.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.state.Property;
import net.minecraft.state.IntegerProperty;
import net.minecraft.block.BlockState;

import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class BuddhasHandPlantUpdateTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure BuddhasHandPlantUpdateTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure BuddhasHandPlantUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure BuddhasHandPlantUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure BuddhasHandPlantUpdateTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if ((new Object() {
			public int get(BlockState _bs, String property) {
				Property<?> _prop = _bs.getBlock().getStateContainer().getProperty(property);
				return _prop instanceof IntegerProperty ? _bs.get((IntegerProperty) _prop) : -1;
			}
		}.get((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))), "age")) < 3) {
			{
				int _value = (int) ((new Object() {
					public int get(BlockState _bs, String property) {
						Property<?> _prop = _bs.getBlock().getStateContainer().getProperty(property);
						return _prop instanceof IntegerProperty ? _bs.get((IntegerProperty) _prop) : -1;
					}
				}.get((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))), "age")) + 1);
				BlockPos _pos = new BlockPos((int) x, (int) y, (int) z);
				BlockState _bs = world.getBlockState(_pos);
				Property<?> _property = _bs.getBlock().getStateContainer().getProperty("age");
				if (_property instanceof IntegerProperty && _property.getAllowedValues().contains(_value))
					world.setBlockState(_pos, _bs.with((IntegerProperty) _property, _value), 3);
			}
		}
	}
}
