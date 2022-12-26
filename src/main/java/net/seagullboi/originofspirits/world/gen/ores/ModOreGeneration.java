package net.seagullboi.originofspirits.world.gen.ores;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.api.WorldGenUtils;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID)
public class ModOreGeneration {

    @SubscribeEvent
    public static void biomeLoadOres(BiomeLoadingEvent event) {

        //Abyss & Colorful Abyss
        if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.ABYSS) || WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.COLORFUL_ABYSS)) {
            //Sand
            generateOre(event.getGeneration(), TOOSOreFeatureConfig.FillerBlockType.BLACK_SAND, TOOSBlocks.WHITE_SAND.get().getDefaultState(), 32, 0, 256, 32);
            generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, TOOSBlocks.WHITE_SAND.get().getDefaultState(), 32, 20, 256, 32);
            generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, TOOSBlocks.BLACK_SAND.get().getDefaultState(), 32, 20, 256, 32);
            //Stone
            generateOre(event.getGeneration(), TOOSOreFeatureConfig.FillerBlockType.BLACK_SAND, TOOSBlocks.ABYSSAL_STONE.get().getDefaultState(), 32, 0, 256, 32);
            generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, TOOSBlocks.ABYSSAL_STONE.get().getDefaultState(), 32, 20, 256, 32);
        }

        //Colorful Abyss
        if((WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.COLORFUL_ABYSS))) {
            generateOre(event.getGeneration(), TOOSOreFeatureConfig.FillerBlockType.BLACK_SAND, TOOSBlocks.PINK_SAND.get().getDefaultState(), 32, 0, 256, 32);
            generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, TOOSBlocks.PINK_SAND.get().getDefaultState(), 32, 20, 256, 32);
        }

        //Sky Realm
        if(WorldGenUtils.isSkyRealm(event)) {
            generateOre(event.getGeneration(), TOOSOreFeatureConfig.FillerBlockType.AIR, TOOSBlocks.CLOUD_BLOCK.get().getDefaultState(), 32, 5, 40, 1);
            generateOre(event.getGeneration(), TOOSOreFeatureConfig.FillerBlockType.AIR, TOOSBlocks.RAINY_CLOUD_BLOCK.get().getDefaultState(), 32, 5, 40, 1);
            generateOre(event.getGeneration(), TOOSOreFeatureConfig.FillerBlockType.BLESSED_STONE, TOOSBlocks.SMENEREL_ORE.get().getDefaultState(), 9, 5, 255, 13);

        }
        if ((WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.DECEPTIVE_ISLANDS))) {
            generateOre(event.getGeneration(), TOOSOreFeatureConfig.FillerBlockType.AIR, TOOSBlocks.CURSED_CLOUD_BLOCK.get().getDefaultState(), 32, 5, 40, 1);
            generateOre(event.getGeneration(), TOOSOreFeatureConfig.FillerBlockType.BLESSED_STONE, TOOSBlocks.CURSED_STEEL_ORE.get().getDefaultState(), 9, 5, 255, 10);
            generateOre(event.getGeneration(), TOOSOreFeatureConfig.FillerBlockType.BLESSED_STONE, TOOSBlocks.DECEPTONE.get().getDefaultState(), 32, 5, 255, 32);
        }
    }

    private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int minHeight, int maxHeight, int amount) {
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(new OreFeatureConfig(fillerType, state, veinSize))
                        .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .square().func_242731_b(amount));
    }
}