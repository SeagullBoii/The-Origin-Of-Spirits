package net.seagullboi.originofspirits;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.seagullboi.originofspirits.block.tile_entities.screen.GunsmithingTableScreen;
import net.seagullboi.originofspirits.block.tile_entities.screen.PressingMachineScreen;
import net.seagullboi.originofspirits.block.tile_entities.screen.PressingMachineTileRenderer;
import net.seagullboi.originofspirits.client.renderer.*;
import net.seagullboi.originofspirits.registry.*;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientRegister {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(final FMLClientSetupEvent event) {
        // Tile Entities
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.SIGN_TILE_ENTITIES.get(), SignTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.PRESSING_MACHINE.get(), PressingMachineTileRenderer::new);

        //Boats
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.SACREDWOOD_BOAT.get(), SacredwoodBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.SWIRLWOOD_BOAT.get(), SwirlwoodBoatRenderer::new);

        //Entities
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.CABADOR.get(), ((IRenderFactory) CabadorRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.CAECANUS.get(), ((IRenderFactory) CaecanusEntityRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.CLUFF.get(), ((IRenderFactory) CluffEntityRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.HOBAYOSH.get(), ((IRenderFactory) HobayoshRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.LAZOCULUS.get(), ((IRenderFactory) LazoculusEntityRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.SAC_FROG.get(), ((IRenderFactory) SacFrogEntityRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.SAC_FROG_EGGS.get(), ((IRenderFactory) SacFrogEggsRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.CURSED_EYE.get(), ((IRenderFactory) CursedEyeRenderer::new));

        //Projectiles
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.CURSED_LASER.get(), ((IRenderFactory) EmptyRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.CLUFF_CLOUD.get(), ((IRenderFactory) CluffCloudRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.BULLET.get(), ((IRenderFactory) BulletRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.MAGNETIC_RAY.get(), ((IRenderFactory) EmptyRenderer::new));
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerBlockRenderTypes(final FMLClientSetupEvent event) {
        //Block
        RenderTypeLookup.setRenderLayer(TOOSBlocks.ABYSSAL_SPAWNER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.ALCYONEUM_POLYPS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.BARLEY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.DEEPSEA_ALGAE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.DEEP_ALCYONEUM_POLYPS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.DUCKWEED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.GLOWKELP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.GLOWKELP_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.MAGIC_MAGNOLIA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.POTTED_MAGIC_MAGNOLIA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.PRESSING_MACHINE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.SWEET_POTATOES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.BLESSED_ROSE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.POTTED_BLESSED_ROSE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.CRYSTAL_LILY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.POTTED_CRYSTAL_LILY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.VIOLET_PERIWINKLE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.POTTED_VIOLET_PERIWINKLE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.RED_PERIWINKLE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.POTTED_RED_PERIWINKLE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.ORANGE_PERIWINKLE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.POTTED_ORANGE_PERIWINKLE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.PEACE_LILY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.POTTED_PEACE_LILY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.SACRED_VIOLET.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.POTTED_SACRED_VIOLET.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.BLUEBERRY_BUSH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.CURSED_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.CURSED_LAVA.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.CURSED_LAVA_FLOWING.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.CURSED_LAVA_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.BUDDHAS_HAND_PLANT.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.HARD_SUGAR_CANE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.BEAN_PLANT.get(), RenderType.getCutout());

    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerScreens(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(TOOSContainers.GUNSMITHING_TABLE_CONTAINER.get(), GunsmithingTableScreen::new);
        ScreenManager.registerFactory(TOOSContainers.PRESSING_MACHINE_CONTAINER.get(), PressingMachineScreen::new);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerWoodTypes(final FMLClientSetupEvent event) {
        Atlases.addWoodType(ModWoodTypes.SACREDWOOD);
        Atlases.addWoodType(ModWoodTypes.SWIRLWOOD);
    }


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerKeybindings(final FMLClientSetupEvent event) {
        TOOSKeybinds.register(event);
    }
}
