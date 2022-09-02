package net.seagullboi.originofspirits.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.datagen.client.TOOSBlockItemModelProvider;
import net.seagullboi.originofspirits.datagen.client.TOOSBlockStateProvider;
import net.seagullboi.originofspirits.datagen.client.TOOSItemModelProvider;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        final ExistingFileHelper efh = event.getExistingFileHelper();
        if (event.includeServer()) {
            dataGenerator.addProvider(new TOOSBlockStateProvider(dataGenerator, efh));
            dataGenerator.addProvider(new TOOSBlockItemModelProvider(dataGenerator, efh));
            dataGenerator.addProvider(new TOOSItemModelProvider(dataGenerator, efh));
            dataGenerator.addProvider(new TOOSLootTableProvider(dataGenerator));
            dataGenerator.addProvider(new TOOSRecipeProvider(dataGenerator));
            dataGenerator.addProvider(new TOOSBlockTagsProvider(dataGenerator, efh));
            dataGenerator.addProvider(new TOOSLangProvider(dataGenerator, "en_us_test"));
        }
    }
}
