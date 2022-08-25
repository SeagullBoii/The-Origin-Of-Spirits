package net.seagullboi.originofspirits;

import net.minecraft.client.gui.ScreenManager;
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
import net.seagullboi.originofspirits.entity.renderer.*;
import net.seagullboi.originofspirits.registry.TOOSBlocks;
import net.seagullboi.originofspirits.registry.TOOSContainers;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;
import net.seagullboi.originofspirits.registry.ModTileEntities;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientRegister {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(final FMLClientSetupEvent event) {
        // Tile Entities
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.SIGN_TILE_ENTITIES.get(), SignTileEntityRenderer::new);
        //Boats
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.SACREDWOOD_BOAT.get(), SacredwoodBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.SWIRLWOOD_BOAT.get(), SwirlwoodBoatRenderer::new);
        //Entities
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.CAECANUS.get(), ((IRenderFactory) CaecanusEntityRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.HOBAYOSH.get(), ((IRenderFactory) HobayoshRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.SAC_FROG.get(), ((IRenderFactory) SacFrogEntityRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.SAC_FROG_EGGS.get(), ((IRenderFactory) SacFrogEggsRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.LAZOCULUS.get(), ((IRenderFactory) LazoculusEntityRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.CLUFF.get(), ((IRenderFactory) CluffEntityRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.CABADOR.get(), ((IRenderFactory) CabadorRenderer::new));

        //Projectiles
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.CURSED_LASER.get(), ((IRenderFactory) EmptyRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.CLUFF_CLOUD.get(), ((IRenderFactory) CluffCloudRenderer::new));
        RenderingRegistry.registerEntityRenderingHandler(TOOSEntityTypes.BULLET.get(), ((IRenderFactory) BulletRenderer::new));

        //Block
        RenderTypeLookup.setRenderLayer(TOOSBlocks.ALCYONEUM_POLYPS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.DEEP_ALCYONEUM_POLYPS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.DEEPSEA_ALGAE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.GLOWKELP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(TOOSBlocks.GLOWKELP_PLANT.get(), RenderType.getCutout());
    }

    public static void registerTileEntities(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(TOOSContainers.GUNSMITHING_TABLE_CONTAINER.get(), GunsmithingTableScreen::new);
    }
}