package net.seagullboi.originofspirits.events;

import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.WoodType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.network.TOOSNetwork;
import net.seagullboi.originofspirits.registry.ModStructures;
import net.seagullboi.originofspirits.registry.ModWoodTypes;
import net.seagullboi.originofspirits.registry.TOOSBlocks;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupEvent {

    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            //Entity Spawn
            EntitySpawnPlacementRegistry.register(TOOSEntityTypes.LAZOCULUS.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);

            //Wood Types
            WoodType.register(ModWoodTypes.SACREDWOOD);
            WoodType.register(ModWoodTypes.SWIRLWOOD);

            //Flower Pots
            FlowerPotBlock pot = ((FlowerPotBlock) Blocks.FLOWER_POT);
            pot.addPlant(TOOSBlocks.MAGIC_MAGNOLIA.getId(), TOOSBlocks.POTTED_MAGIC_MAGNOLIA);
            pot.addPlant(TOOSBlocks.BLESSED_ROSE.getId(), TOOSBlocks.POTTED_BLESSED_ROSE);
            pot.addPlant(TOOSBlocks.CRYSTAL_LILY.getId(), TOOSBlocks.POTTED_CRYSTAL_LILY);
            pot.addPlant(TOOSBlocks.ORANGE_PERIWINKLE.getId(), TOOSBlocks.POTTED_ORANGE_PERIWINKLE);
            pot.addPlant(TOOSBlocks.RED_PERIWINKLE.getId(), TOOSBlocks.POTTED_RED_PERIWINKLE);
            pot.addPlant(TOOSBlocks.VIOLET_PERIWINKLE.getId(), TOOSBlocks.POTTED_VIOLET_PERIWINKLE);
            pot.addPlant(TOOSBlocks.PEACE_LILY.getId(), TOOSBlocks.POTTED_PEACE_LILY);
            pot.addPlant(TOOSBlocks.SACRED_VIOLET.getId(), TOOSBlocks.POTTED_SACRED_VIOLET);

            ModStructures.setupStructures();
            TOOSNetwork.initializeNetwork();
        });
    }
}
