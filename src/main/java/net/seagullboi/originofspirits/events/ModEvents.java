package net.seagullboi.originofspirits.events;

import com.mrcrayfish.obfuscate.client.event.PlayerModelEvent;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.commands.AbyssalFishingConditionCheckCommand;
import net.seagullboi.originofspirits.commands.AbyssalFishingConditionToggleCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.seagullboi.originofspirits.item.EyeCanonItem;
import net.seagullboi.originofspirits.item.RedstoneHandgunItem;
import net.seagullboi.originofspirits.item.ShotgunItem;
import net.seagullboi.originofspirits.registry.TOOSItems;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new AbyssalFishingConditionToggleCommand(event.getDispatcher());
        new AbyssalFishingConditionCheckCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void setupPlayerRotations(PlayerModelEvent.SetupAngles.Post event) {
        Entity entity = event.getEntity();
        PlayerModel playerModel = event.getModelPlayer();

        if (entity instanceof PlayerEntity) {
            //Pistol
            PlayerEntity player = (PlayerEntity) entity;
            if (player.getHeldItemMainhand().getItem() == TOOSItems.PISTOL.get()) {
                playerModel.bipedRightArm.rotateAngleX = 67.5f;
            }
            if (player.getHeldItemOffhand().getItem() == TOOSItems.PISTOL.get()) {
                playerModel.bipedLeftArm.rotateAngleX = 67.5f;
            }

            //Shotgun
            if (player.getHeldItemMainhand().getItem() instanceof ShotgunItem) {
                    playerModel.bipedRightArm.rotateAngleX = 67.5f;
                }
            if (player.getHeldItemOffhand().getItem() instanceof ShotgunItem) {
                playerModel.bipedLeftArm.rotateAngleX = 67.5f;
            }

            //Redstone Handgun
            if (player.getHeldItemMainhand().getItem() instanceof RedstoneHandgunItem) {
                if (player.getHeldItemMainhand().getOrCreateTag().getInt("weapon_mod") == 1) {
                    playerModel.bipedRightArm.rotateAngleX = 67.5f;
                    playerModel.bipedLeftArm.rotateAngleX = 67.5f;
                    playerModel.bipedLeftArm.rotateAngleY = (float) Math.toRadians(45);
                } else {
                    playerModel.bipedRightArm.rotateAngleX = 67.5f;
                }
            }
            if (player.getHeldItemOffhand().getItem() instanceof RedstoneHandgunItem) {
                if (player.getHeldItemOffhand().getOrCreateTag().getInt("weapon_mod") == 1) {
                    playerModel.bipedLeftArm.rotateAngleX = 67.5f;
                    playerModel.bipedRightArm.rotateAngleX = 67.5f;
                    playerModel.bipedRightArm.rotateAngleY = (float) Math.toRadians(-45);
                } else {
                    playerModel.bipedLeftArm.rotateAngleX = 67.5f;
                }
            }

            //Eye Cannon
            if (player.getHeldItemMainhand().getItem() instanceof EyeCanonItem) {
                EyeCanonItem cannon = (EyeCanonItem) player.getHeldItemMainhand().getItem();
                playerModel.bipedRightArm.rotateAngleX = 67.5f;
                playerModel.bipedLeftArm.rotateAngleX = 67.5f;
                playerModel.bipedLeftArm.rotateAngleY = (float) Math.toRadians(45);

            }
            if (player.getHeldItemOffhand().getItem() instanceof EyeCanonItem) {
                EyeCanonItem cannon = (EyeCanonItem) player.getHeldItemOffhand().getItem();
                playerModel.bipedRightArm.rotateAngleX = 67.5f;
                playerModel.bipedLeftArm.rotateAngleX = 67.5f;
                playerModel.bipedRightArm.rotateAngleY = (float) Math.toRadians(-45);
            }
        }
    }
}

