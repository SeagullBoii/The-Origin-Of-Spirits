package net.seagullboi.originofspirits.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.seagullboi.originofspirits.api.IConsumesMana;
import net.seagullboi.originofspirits.registry.TOOSParticles;
import net.seagullboi.originofspirits.util.ColorUtil;
import net.seagullboi.originofspirits.util.GlobalVarUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OcularPiercerItem extends Item implements IConsumesMana {

    public OcularPiercerItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
        entity.setActiveHand(hand);
        return new ActionResult(ActionResultType.SUCCESS, entity.getHeldItem(hand));
    }


    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity entity, int timeLeft) {
        double x = entity.getPosX();
        double y = entity.getPosY() + 1.5;
        double z = entity.getPosZ();
        double xTrace = Math.sin(Math.toRadians(entity.rotationYaw + 180)) / 2;
        double yTrace = Math.sin(Math.toRadians(0 - entity.rotationPitch)) / 2;
        double zTrace = Math.cos(Math.toRadians(entity.rotationYaw)) / 2;
        World world = entity.world;
        int mana = (int) (entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana;



        if (mana >= 3) {
            if (!((PlayerEntity) entity).abilities.isCreativeMode) {
                if (entity.getHeldItemMainhand() == stack) {
                    stack.damageItem(1, entity, (ent) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                } else if (entity.getHeldItemOffhand() == stack)
                    stack.damageItem(1, entity, (ent) -> entity.sendBreakAnimation(EquipmentSlotType.OFFHAND));
                // Consumes Mana
                if (entity instanceof PlayerEntity) {
                    PlayerEntity playerEntity = (PlayerEntity) entity;
                    GlobalVarUtil.setMana(playerEntity, GlobalVarUtil.getMana(playerEntity) - 3);
                    GlobalVarUtil.setManaCooldown(playerEntity,40);
                }
            }


            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                serverWorld.playSound(null, x, y, z, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1, 1.7f);
                serverWorld.playSound(null, x, y, z, SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 0.5f, 1.7f);
                if (world instanceof ServerWorld) {
                    int distance = 0;

                    for (int i = 0; i < 10; i++) {

                        if (!world.getBlockState(new BlockPos(xTrace * distance + x, yTrace * distance + y, zTrace * distance + z)).isSolid()) {
                            distance++;
                        }

                        serverWorld.spawnParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x + xTrace * distance, y + yTrace * distance + 0.25, z + zTrace * distance, 1, 0, 0, 0, 0.01);
                        this.shoot(xTrace * distance + x, yTrace * distance + y, zTrace * distance + z, entity, world);
                    }
                }
            }

            if (entity instanceof PlayerEntity) {
                ((PlayerEntity) entity).getCooldownTracker().setCooldown(this, 40);
            }

        }
    }

    public void shoot(double xTrace, double yTrace, double zTrace, LivingEntity entity, World world) {
        AxisAlignedBB axis = new AxisAlignedBB(xTrace - (2 / 2), yTrace - (2 / 2), zTrace - (2 / 2), xTrace + (2 / 2), yTrace + (2 / 2), zTrace + (2 / 2));

        List<Entity> entityList = world.getEntitiesWithinAABB(Entity.class, axis, null).stream().sorted(new Object() {
            Comparator<Entity> compareDistOf(double cx, double cy, double cz) {
                return Comparator.comparing(comparedEntity -> comparedEntity.getDistanceSq(cx, cy, cz));
            }
        }.compareDistOf(xTrace, yTrace, zTrace)).collect(Collectors.toList());

        for (Entity entityIterator : entityList) {
            if (entityIterator instanceof LivingEntity)
                if (entityIterator != entity && entityIterator.getRidingEntity() != entity && entity.getRidingEntity() != entityIterator)
                    entityIterator.attackEntityFrom(DamageSource.causeMobDamage(entity), 10);
            if (entityIterator instanceof PlayerEntity) {
                ((PlayerEntity) entityIterator).disableShield(true);
            }
        }
    }

    @Override
    public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(itemstack, world, list, flag);
        list.add(new StringTextComponent(ColorUtil.DARK_GREEN + "10 \u00A72Magic \u00A72Damage"));
        list.add(new StringTextComponent(ColorUtil.AQUA + "[3 Mana Cost]"));
        list.add(new StringTextComponent(ColorUtil.DARK_GREEN + "Shoots a piercing beam"));
    }

    @Override
    public int getManaConsumed() {
        return 5;
    }
}
