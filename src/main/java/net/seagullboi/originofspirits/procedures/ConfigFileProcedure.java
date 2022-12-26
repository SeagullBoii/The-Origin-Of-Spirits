package net.seagullboi.originofspirits.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Collections;
import java.util.Map;

public class ConfigFileProcedure {
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void init(FMLCommonSetupEvent event) {
			executeProcedure(Collections.emptyMap());
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		/*com.google.gson.JsonObject configobject = new com.google.gson.JsonObject();
		File config = new File("");
		config = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/originfospirits"), File.separator + "config.json");
		if (!config.exists()) {
			try {
				config.getParentFile().mkdirs();
				config.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			configobject.addProperty("Starter Bag", (true));
			configobject.addProperty("Spite", (true));
			configobject.addProperty("Consume Fairy Blood Effect On Eradicating a Curse", (false));
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(config);
					fileWriter.write(mainGSONBuilderVariable.toJson(configobject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}*/
	}
}
