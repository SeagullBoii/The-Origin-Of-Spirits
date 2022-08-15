package net.seagullboi.originofspirits.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemFishedEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.seagullboi.originofspirits.item.ImmovableShardItem;
import net.seagullboi.originofspirits.item.CrystalOfMagicItem;
import net.seagullboi.originofspirits.enchantment.MagicCatchEnchantment;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;
import java.util.HashMap;

public class ManaStarFishProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerFishItem(ItemFishedEvent event) {
			PlayerEntity entity = event.getPlayer();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			World world = entity.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure ManaStarFish!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure ManaStarFish!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (world.func_241828_r().getRegistry(Registry.BIOME_KEY)
				.getKey(world.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())))) != null
				&& world.func_241828_r().getRegistry(Registry.BIOME_KEY)
						.getKey(world.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ()))))
						.equals(new ResourceLocation("originofspirits:abyss"))
				|| world.func_241828_r().getRegistry(Registry.BIOME_KEY)
						.getKey(world.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())))) != null
						&& world.func_241828_r().getRegistry(Registry.BIOME_KEY)
								.getKey(world.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ()))))
								.equals(new ResourceLocation("originofspirits:shallow_abyss"))) {
			if (Math.random() < 0.1) {
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
							new ItemStack(ImmovableShardItem.block));
					entityToSpawn.setPickupDelay((int) 0);
					world.addEntity(entityToSpawn);
				}
			}
		}
		if (OriginofspiritsModVariables.WorldVariables.get(world).AbyssalFishing == true) {
			if (world.func_241828_r().getRegistry(Registry.BIOME_KEY)
					.getKey(world.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())))) != null
					&& world.func_241828_r().getRegistry(Registry.BIOME_KEY)
							.getKey(world.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ()))))
							.equals(new ResourceLocation("originofspirits:abyss"))
					|| world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(
							world.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())))) != null
							&& world.func_241828_r().getRegistry(Registry.BIOME_KEY)
									.getKey(world
											.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ()))))
									.equals(new ResourceLocation("originofspirits:shallow_abyss"))) {
				if (EnchantmentHelper.getEnchantmentLevel(MagicCatchEnchantment.enchantment,
						((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) > 0) {
					if (Math.random() < EnchantmentHelper.getEnchantmentLevel(MagicCatchEnchantment.enchantment,
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) / 10 + 0.08) {
						if (dependencies.get("event") != null) {
							Object _obj = dependencies.get("event");
							if (_obj instanceof Event) {
								Event _evt = (Event) _obj;
								if (_evt.isCancelable())
									_evt.setCanceled(true);
							}
						}
						if (world instanceof World && !world.isRemote()) {
							ItemEntity entityToSpawn = new ItemEntity((World) world, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
									new ItemStack(CrystalOfMagicItem.block));
							entityToSpawn.setPickupDelay((int) 0);
							world.addEntity(entityToSpawn);
						}
					}
				} else {
					if (Math.random() < 0.08) {
						if (dependencies.get("event") != null) {
							Object _obj = dependencies.get("event");
							if (_obj instanceof Event) {
								Event _evt = (Event) _obj;
								if (_evt.isCancelable())
									_evt.setCanceled(true);
							}
						}
						if (world instanceof World && !world.isRemote()) {
							ItemEntity entityToSpawn = new ItemEntity((World) world, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
									new ItemStack(CrystalOfMagicItem.block));
							entityToSpawn.setPickupDelay((int) 0);
							world.addEntity(entityToSpawn);
						}
					}
				}
			} else if (world.func_241828_r().getRegistry(Registry.BIOME_KEY)
					.getKey(world.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())))) != null
					&& world.func_241828_r().getRegistry(Registry.BIOME_KEY)
							.getKey(world.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ()))))
							.equals(new ResourceLocation("originofspirits:colorful_abyss"))
					|| world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(
							world.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())))) != null
							&& world.func_241828_r().getRegistry(Registry.BIOME_KEY)
									.getKey(world
											.getBiome(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ()))))
									.equals(new ResourceLocation("originofspirits:abyss"))) {
				if (EnchantmentHelper.getEnchantmentLevel(MagicCatchEnchantment.enchantment,
						((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) > 0) {
					if (Math.random() < EnchantmentHelper.getEnchantmentLevel(MagicCatchEnchantment.enchantment,
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) / 10 + 0.1) {
						if (dependencies.get("event") != null) {
							Object _obj = dependencies.get("event");
							if (_obj instanceof Event) {
								Event _evt = (Event) _obj;
								if (_evt.isCancelable())
									_evt.setCanceled(true);
							}
						}
						if (world instanceof World && !world.isRemote()) {
							ItemEntity entityToSpawn = new ItemEntity((World) world, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
									new ItemStack(CrystalOfMagicItem.block));
							entityToSpawn.setPickupDelay((int) 0);
							world.addEntity(entityToSpawn);
						}
					}
				} else {
					if (Math.random() < 0.08) {
						if (dependencies.get("event") != null) {
							Object _obj = dependencies.get("event");
							if (_obj instanceof Event) {
								Event _evt = (Event) _obj;
								if (_evt.isCancelable())
									_evt.setCanceled(true);
							}
						}
						if (world instanceof World && !world.isRemote()) {
							ItemEntity entityToSpawn = new ItemEntity((World) world, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
									new ItemStack(CrystalOfMagicItem.block));
							entityToSpawn.setPickupDelay((int) 0);
							world.addEntity(entityToSpawn);
						}
					}
				}
			}
		}
	}
}
