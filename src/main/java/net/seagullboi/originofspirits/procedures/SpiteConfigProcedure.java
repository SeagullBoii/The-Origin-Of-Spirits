package net.seagullboi.originofspirits.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

import net.minecraft.world.IWorld;

import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class SpiteConfigProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldLoad(WorldEvent.Load event) {
			IWorld world = event.getWorld();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("world", world);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure SpiteConfig!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		File config = new File("");
		com.google.gson.JsonObject configobject = new com.google.gson.JsonObject();
		config = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/originfospirits"), File.separator + "config.json");
		if (config.exists()) {
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(config));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					configobject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					OriginofspiritsModVariables.WorldVariables.get(world).Spite = configobject.get("Spite").getAsBoolean();
					OriginofspiritsModVariables.WorldVariables.get(world).syncData(world);
					OriginofspiritsModVariables.MapVariables.get(world).ConsumeFairyBloodEffect = configobject
							.get("Consume Fairy Blood Effect On Eradicating a Curse").getAsBoolean();
					OriginofspiritsModVariables.MapVariables.get(world).syncData(world);
					OriginofspiritsModVariables.WorldVariables.get(world).BagConfig = configobject.get("Starter Bag").getAsBoolean();
					OriginofspiritsModVariables.WorldVariables.get(world).syncData(world);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
