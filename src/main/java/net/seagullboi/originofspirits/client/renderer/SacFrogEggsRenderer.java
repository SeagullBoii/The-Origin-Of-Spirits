package net.seagullboi.originofspirits.client.renderer;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.SacFrogEggsEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.seagullboi.originofspirits.client.model.SacFrogEggsModel;

public class SacFrogEggsRenderer extends MobRenderer<SacFrogEggsEntity, SacFrogEggsModel<SacFrogEggsEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/entity/sac_frog_egg.png");

        public SacFrogEggsRenderer(EntityRendererManager renderManagerIn) {
            super(renderManagerIn, new SacFrogEggsModel<>(), 0.5F);
        }

        @Override
      public ResourceLocation getEntityTexture(SacFrogEggsEntity entity) {
        return TEXTURE;
    }
}