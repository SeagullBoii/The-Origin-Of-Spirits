package net.seagullboi.originofspirits.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.IWorld;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.item.YellowPixieItem;
import net.seagullboi.originofspirits.item.RedPixieItem;
import net.seagullboi.originofspirits.item.GreenPixieItem;
import net.seagullboi.originofspirits.item.CursedSteelNetItem;
import net.seagullboi.originofspirits.item.BluePixieItem;
import net.seagullboi.originofspirits.entity.PixieEntity;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class PixieCaptureProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
			Entity entity = event.getTarget();
			PlayerEntity sourceentity = event.getPlayer();
			if (event.getHand() != sourceentity.getActiveHand()) {
				return;
			}
			double i = event.getPos().getX();
			double j = event.getPos().getY();
			double k = event.getPos().getZ();
			IWorld world = event.getWorld();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure PixieCapture!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency sourceentity for procedure PixieCapture!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == CursedSteelNetItem.block) {
			if (entity instanceof PixieEntity.CustomEntity) {
				if (((PixieEntity.CustomEntity) entity).getVariant() == 1) {
					if (sourceentity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(BluePixieItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
					}
					if (sourceentity instanceof LivingEntity) {
						((LivingEntity) sourceentity).swing(Hand.MAIN_HAND, true);
					}
					if (!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false)) {
						{
							ItemStack _ist = ((sourceentity instanceof LivingEntity)
									? ((LivingEntity) sourceentity).getHeldItemMainhand()
									: ItemStack.EMPTY);
							if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
								_ist.shrink(1);
								_ist.setDamage(0);
							}
						}
					}
				}
				if (((PixieEntity.CustomEntity) entity).getVariant() == 2) {
					if (sourceentity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(GreenPixieItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
					}
					if (sourceentity instanceof LivingEntity) {
						((LivingEntity) sourceentity).swing(Hand.MAIN_HAND, true);
					}
					if (!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false)) {
						{
							ItemStack _ist = ((sourceentity instanceof LivingEntity)
									? ((LivingEntity) sourceentity).getHeldItemMainhand()
									: ItemStack.EMPTY);
							if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
								_ist.shrink(1);
								_ist.setDamage(0);
							}
						}
					}
				}
				if (((PixieEntity.CustomEntity) entity).getVariant() == 3) {
					if (sourceentity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(RedPixieItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
					}
					if (sourceentity instanceof LivingEntity) {
						((LivingEntity) sourceentity).swing(Hand.MAIN_HAND, true);
					}
					if (!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false)) {
						{
							ItemStack _ist = ((sourceentity instanceof LivingEntity)
									? ((LivingEntity) sourceentity).getHeldItemMainhand()
									: ItemStack.EMPTY);
							if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
								_ist.shrink(1);
								_ist.setDamage(0);
							}
						}
					}
				}
				if (((PixieEntity.CustomEntity) entity).getVariant() == 4) {
					if (sourceentity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(YellowPixieItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
					}
					if (sourceentity instanceof LivingEntity) {
						((LivingEntity) sourceentity).swing(Hand.MAIN_HAND, true);
					}
					if (!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false)) {
						{
							ItemStack _ist = ((sourceentity instanceof LivingEntity)
									? ((LivingEntity) sourceentity).getHeldItemMainhand()
									: ItemStack.EMPTY);
							if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
								_ist.shrink(1);
								_ist.setDamage(0);
							}
						}
					}
				}
				if (!entity.world.isRemote())
					entity.remove();
			}
		} else if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == CursedSteelNetItem.block) {
			if (entity instanceof PixieEntity.CustomEntity) {
				if (((PixieEntity.CustomEntity) entity).getVariant() == 1) {
					if (sourceentity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(BluePixieItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
					}
					if (sourceentity instanceof LivingEntity) {
						((LivingEntity) sourceentity).swing(Hand.OFF_HAND, true);
					}
					if (!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false)) {
						{
							ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY);
							if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
								_ist.shrink(1);
								_ist.setDamage(0);
							}
						}
					}
				}
				if (((PixieEntity.CustomEntity) entity).getVariant() == 2) {
					if (sourceentity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(GreenPixieItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
					}
					if (sourceentity instanceof LivingEntity) {
						((LivingEntity) sourceentity).swing(Hand.OFF_HAND, true);
					}
					if (!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false)) {
						{
							ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY);
							if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
								_ist.shrink(1);
								_ist.setDamage(0);
							}
						}
					}
				}
				if (((PixieEntity.CustomEntity) entity).getVariant() == 3) {
					if (sourceentity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(RedPixieItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
					}
					if (sourceentity instanceof LivingEntity) {
						((LivingEntity) sourceentity).swing(Hand.OFF_HAND, true);
					}
					if (!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false)) {
						{
							ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY);
							if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
								_ist.shrink(1);
								_ist.setDamage(0);
							}
						}
					}
				}
				if (((PixieEntity.CustomEntity) entity).getVariant() == 4) {
					if (sourceentity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(YellowPixieItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
					}
					if (sourceentity instanceof LivingEntity) {
						((LivingEntity) sourceentity).swing(Hand.OFF_HAND, true);
					}
					if (!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false)) {
						{
							ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY);
							if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
								_ist.shrink(1);
								_ist.setDamage(0);
							}
						}
					}
				}
				if (!entity.world.isRemote())
					entity.remove();
			}
		}
	}
}
