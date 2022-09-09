package net.seagullboi.originofspirits.client.renderer;


import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.CaecanusEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.ResourceLocation;
import net.seagullboi.originofspirits.client.model.CaecanusEntityModel;

public class CaecanusEntityRenderer extends MobRenderer<CaecanusEntity, CaecanusEntityModel<CaecanusEntity>> {
    public CaecanusEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CaecanusEntityModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(CaecanusEntity entity) {
        if (entity.getVillagerData().getProfession() == VillagerProfession.FARMER) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_farmer.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.FISHERMAN) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_fisherman.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.SHEPHERD) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_shepherd.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.FLETCHER) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_fletcher.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.LIBRARIAN) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_libarian.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.CARTOGRAPHER) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_cartographer.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.CLERIC) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_cleric.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.ARMORER) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_armorer.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.WEAPONSMITH) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_weaponsmith.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.TOOLSMITH) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_toolsmith.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.BUTCHER) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_butcher.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.LEATHERWORKER) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_leatherworker.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.MASON) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_mason.png");
        }
        if (entity.getVillagerData().getProfession() == VillagerProfession.NITWIT) {
            return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus_nitwit.png");
        }

        return new ResourceLocation(OriginOfSpirits.MOD_ID,"textures/caecanus/caecanus.png");
    }
}