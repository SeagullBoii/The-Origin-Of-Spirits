package net.seagullboi.originofspirits.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.state.Property;
import net.minecraft.state.IntegerProperty;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.item.BuddhasHandItem;

import java.util.Map;

public class BuddhasHandPlantOnBlockRightClickedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure BuddhasHandPlantOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure BuddhasHandPlantOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure BuddhasHandPlantOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure BuddhasHandPlantOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure BuddhasHandPlantOnBlockRightClicked!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if ((new Object() {
			public int get(BlockState _bs, String property) {
				Property<?> _prop = _bs.getBlock().getStateContainer().getProperty(property);
				return _prop instanceof IntegerProperty ? _bs.get((IntegerProperty) _prop) : -1;
			}
		}.get((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))), "age")) == 3) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, (x + 0.5), (y + 0.5), (z + 0.5), new ItemStack(BuddhasHandItem.block));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
			{
				int _value = 0;
				BlockPos _pos = new BlockPos((int) x, (int) y, (int) z);
				BlockState _bs = world.getBlockState(_pos);
				Property<?> _property = _bs.getBlock().getStateContainer().getProperty("age");
				if (_property instanceof IntegerProperty && _property.getAllowedValues().contains(_value))
					world.setBlockState(_pos, _bs.with((IntegerProperty) _property, _value), 3);
			}
		} else if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == Items.BONE_MEAL) {
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
			}
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
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), (int) 5, 0.1, 0.1, 0.1, 1);
			}
			if (!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Items.BONE_MEAL);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
		} else if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem() == Items.BONE_MEAL) {
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swing(Hand.OFF_HAND, true);
			}
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
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), (int) 5, 0.1, 0.1, 0.1, 1);
			}
			if (!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Items.BONE_MEAL);
					((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
							((PlayerEntity) entity).container.func_234641_j_());
				}
			}
		}
	}
}
