package net.seagullboi.originofspirits.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.item.ItemStack;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class EndPearlHeadPickaxeLevelProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure EndPearlHeadPickaxeLevel!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure EndPearlHeadPickaxeLevel!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure EndPearlHeadPickaxeLevel!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure EndPearlHeadPickaxeLevel!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency itemstack for procedure EndPearlHeadPickaxeLevel!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		itemstack.getOrCreateTag().putDouble("CustomModelData", (itemstack.getOrCreateTag().getDouble("level")));
		itemstack.getOrCreateTag().putDouble("progress", (itemstack.getOrCreateTag().getDouble("progress") + 1));
		if (itemstack.getOrCreateTag().getDouble("progress") == 59) {
			itemstack.getOrCreateTag().putDouble("level", (itemstack.getOrCreateTag().getDouble("level") + 1));
			(itemstack).setDisplayName(new StringTextComponent(("\u00A7f\u00A7c" + "Mediocre Ender Pearl-Head Pickaxe")));
		} else if (itemstack.getOrCreateTag().getDouble("progress") == 89) {
			itemstack.getOrCreateTag().putDouble("level", (itemstack.getOrCreateTag().getDouble("level") + 1));
			(itemstack).setDisplayName(new StringTextComponent(("\u00A7f\u00A7c" + "Graduate Ender Eye-Head Pickaxe")));
		} else if (itemstack.getOrCreateTag().getDouble("progress") == 119) {
			itemstack.getOrCreateTag().putDouble("level", (itemstack.getOrCreateTag().getDouble("level") + 1));
			(itemstack).setDisplayName(new StringTextComponent(("\u00A7f\u00A7c" + "Scholarly Ender Eye-Head Pickaxe")));
		}
		if (itemstack.getOrCreateTag().getDouble("level") >= 1) {
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						"summon experience_orb ~ ~ ~ {Value:1}");
			}
		}
		if (Math.random() < 0.7) {
			if (itemstack.getOrCreateTag().getDouble("level") >= 2) {
				for (int index0 = 0; index0 < (int) (3); index0++) {
					if (world instanceof ServerWorld) {
						((World) world).getServer().getCommandManager().handleCommand(
								new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
										new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
								"summon experience_orb ~ ~ ~ {Value:1}");
					}
				}
			}
		}
		if (Math.random() < 0.1) {
			if (itemstack.getOrCreateTag().getDouble("level") >= 3) {
				for (int index1 = 0; index1 < (int) (4); index1++) {
					if (world instanceof ServerWorld) {
						((World) world).getServer().getCommandManager().handleCommand(
								new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
										new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
								"summon experience_orb ~ ~ ~ {Value:1}");
					}
				}
			}
		}
	}
}
