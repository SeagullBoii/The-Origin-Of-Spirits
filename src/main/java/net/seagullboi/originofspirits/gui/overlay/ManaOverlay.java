package net.seagullboi.originofspirits.gui.overlay;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.seagullboi.originofspirits.procedures.ManaCheck0Procedure;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Mod.EventBusSubscriber
public class ManaOverlay {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void eventHandler(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
            int w = event.getWindow().getScaledWidth();
            int h = event.getWindow().getScaledHeight();

            int posX = w / 2;
            int posY = h / 2;

            World _world = null;
            double _x = 0;
            double _y = 0;
            double _z = 0;

            PlayerEntity entity = Minecraft.getInstance().player;
            if (entity != null) {
                _world = entity.world;
                _x = entity.getPosX();
                _y = entity.getPosY();
                _z = entity.getPosZ();
            }

            World world = _world;
            double x = _x;
            double y = _y;
            double z = _z;

            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                    GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableAlphaTest();
            if (!entity.isSpectator()) {

                int mana = (int) (entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana;

                Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("originofspirits:textures/overlay/manaoverlay/manaoverlay" + mana + ".png"));
                Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), 0, 0, 0, 0, w, h, w, h);
            }

            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.enableAlphaTest();
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}

