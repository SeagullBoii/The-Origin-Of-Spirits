package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.seagullboi.originofspirits.api.IConsumesMana;
import net.seagullboi.originofspirits.api.IWeaponMod;
import net.seagullboi.originofspirits.entity.CursedLaserProjectile;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

    public class EyeCanonItem extends Item implements IConsumesMana, IWeaponMod {

        ItemStack canonStack = new ItemStack(this);
        int extraDamage;
        int weaponMod = 0;
        boolean fullyCharged;
        boolean charged;
        boolean marksman;

        public EyeCanonItem(Properties properties) {
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
            int i = this.getUseDuration(stack) - timeLeft;
            World world = entity.world;
            if (i == 30) {
                this.setCharged(true);
                world.playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1, 2, true);
            }

            if (i >= 40 && i < 60) {
                extraDamage = 1;
            } else if (i >= 60) {
                extraDamage = 2;
            }
            if (i >= 70 && stack.getOrCreateTag().getInt("weapon_mod") == 2) {
                if (i == 70 && stack.getOrCreateTag().getInt("weapon_mod") == 2) {
                    world.playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.PLAYERS, 1, 1.5f, true);

                }
                this.setFullyCharged(true);
            }
            if (stack.getOrCreateTag().getInt("weapon_mod") != 2 || i < 70){
                this.setFullyCharged(false);
            }

            if (stack.getOrCreateTag().getInt("weapon_mod") == 1) {
                this.setMarksman(true);
            } else {
                this.setMarksman(false);
            }

        }

        public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityIn, int timeLeft) {
            super.onPlayerStoppedUsing(stack, worldIn, entityIn, timeLeft);
            int i = this.getUseDuration(stack) - timeLeft;
            double dmgExtra = 0;
            int mana = (int) (entityIn.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana;


            if (i >= 30 && mana >= getManaConsumed()) {
                if (!((PlayerEntity) entityIn).abilities.isCreativeMode) {
                    if (entityIn.getHeldItemMainhand() == stack) {
                        stack.damageItem(1, entityIn, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                    } else if (entityIn.getHeldItemOffhand() == stack)
                        stack.damageItem(1, entityIn, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.OFFHAND));
                    // Consumes Mana
                    if (entityIn instanceof PlayerEntity) {
                        consumeMana((PlayerEntity) entityIn, worldIn);
                    }
                }
                System.out.println(this.isFullyCharged());
                shootAt(stack, worldIn, entityIn, 1, 0);
                this.setCharged(false);
                this.extraDamage = 0;
            }
        }

        @Override
        public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
            super.inventoryTick(itemstack, world, entity, slot, selected);
            Item mainHand = ((LivingEntity) entity).getHeldItemMainhand().getItem();
            if (selected) {
                if (mainHand instanceof EyeCanonItem) {
                    EyeCanonItem cannon = (EyeCanonItem) mainHand;
                    if(itemstack.getOrCreateTag().getInt("weapon_mod") > 2) {
                        itemstack.getOrCreateTag().putInt("weapon_mod", 0);
                    }

                    if (itemstack.getOrCreateTag().getInt("weapon_mod") != 2) {
                        this.setFullyCharged(false);
                    }

                    if (entity.isSneaking() && itemstack.getOrCreateTag().getInt("weapon_mod") == 1) {

                        if (!entity.world.isRemote && entity.world.getServer() != null) {
                            entity.world.getServer().getCommandManager().handleCommand(entity.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
                                    "attribute @s minecraft:generic.movement_speed modifier add 91AEAA56-320B-4498-450B-2F7F68079505 SCSLOW -0.08 add");

                        }


                    } else {
                        if (!entity.world.isRemote && entity.world.getServer() != null) {
                            entity.world.getServer().getCommandManager().handleCommand(entity.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
                                    "attribute @s minecraft:generic.movement_speed modifier remove 91AEAA56-320B-4498-450B-2F7F68079505");

                        }

                    }
                }
            } else {
                if (!entity.world.isRemote && entity.world.getServer() != null) {
                    ItemStack mainHandStack = ((LivingEntity) entity).getHeldItemMainhand();
                    if (mainHandStack.getOrCreateTag().getInt("weapon_mod") != 1) {
                        entity.world.getServer().getCommandManager().handleCommand(entity.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
                                "attribute @s minecraft:generic.movement_speed modifier remove 91AEAA56-320B-4498-450B-2F7F68079505");
                    }
                }

            }
        }

        public void shootAt(ItemStack stack, World world, LivingEntity entity, int i, float yRotAddition) {
            CursedLaserProjectile projectile = new CursedLaserProjectile(TOOSEntityTypes.CURSED_LASER.get(), world);
            projectile.func_234612_a_(entity, entity.rotationPitch, entity.rotationYaw, 0.0F, 3.0F, 1.0F);
            projectile.setDamage(projectile.getDamage() + extraDamage);
            projectile.setFire(0);
            projectile.setOwner(entity);
            projectile.setSilent(true);

            projectile.setPosition(entity.getPosX(), entity.getPosY() + 1.3, entity.getPosZ());
            if (((EyeCanonItem) stack.getItem()).isFullyCharged()) {
                projectile.setExplodes(true);
            } else if (((EyeCanonItem) stack.getItem()).isMarksman()) {
                projectile.setMarksman(true);
            }

            world.addEntity(projectile);
            world.playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.PLAYERS, 1, 2, true);
        }


        public boolean isCharged() {
            return this.charged;
        }

        public void setCharged(boolean chargedIn) {
            this.charged = chargedIn;
        }

        public boolean isFullyCharged() {
            return this.fullyCharged;
        }

        public void setFullyCharged(boolean chargedIn) {
            this.fullyCharged = chargedIn;
        }

        public boolean isMarksman() {
            return this.marksman;
        }

        public void setMarksman(boolean marksmanIn) {
            this.marksman = marksmanIn;
        }

        public int getWeaponMod() {
            return this.weaponMod;
        }

        public void setWeaponMod(int mod) {
            this.weaponMod = mod;
        }

        @Override
        public int getManaConsumed() {
            return 2;
        }

        @Override
        public void consumeMana(PlayerEntity player, World worldIn) {
            int mana = (int) (player.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana;

            player.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.Mana = mana - getManaConsumed();
                capability.syncPlayerVariables(player);
            });

            player.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.ManaCooldown = 40;
                capability.syncPlayerVariables(player);
            });
        }

        @Override
        public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
            super.addInformation(itemstack, world, list, flag);

            //Base Info
            list.add(new StringTextComponent("\u00A729-11 \u00A72Magic \u00A72Damage"));
            list.add(new StringTextComponent(""));
            list.add(new StringTextComponent("\u00A7b[2 Mana Cost]"));
            list.add(new StringTextComponent("\u00A7c" + "Must Be Charged"));

            //Weapon Mod
            if (itemstack.getOrCreateTag().getInt("weapon_mod") <= 0) {
                list.add(new StringTextComponent("\u00A77" + "Weapon Mod : Null"));
            } else if (itemstack.getOrCreateTag().getInt("weapon_mod") == 1) {
                list.add(new StringTextComponent("\u00A77" + "Weapon Mod : Scope"));
                if (Screen.hasShiftDown() || Screen.hasControlDown()) {
                    list.add(new StringTextComponent(""));
                    list.add(new StringTextComponent("\u00A77" + "Crouch to zoom in"));
                    list.add(new StringTextComponent("\u00A7c" + "Immobile while zoomed in"));
                    list.add(new StringTextComponent("\u00A72" + "Damage scales with distance"));

                } else {
                    list.add(new StringTextComponent(""));
                    list.add(new StringTextComponent("\u00A77" + "Press [Shift] or [Ctrl] to view mod descriptions"));
                }
            } else if (itemstack.getOrCreateTag().getInt("weapon_mod") == 2) {
                list.add(new StringTextComponent("\u00A77" + "Weapon Mod : Explosive Charge"));
                if (Screen.hasShiftDown() || Screen.hasControlDown()) {
                    list.add(new StringTextComponent(""));
                    list.add(new StringTextComponent("\u00A77" + "Charge to cause an Explosive Blast"));
                    list.add(new StringTextComponent("\u00A7c" + "Takes 3.5 seconds to fully charge"));

                } else {
                    list.add(new StringTextComponent(""));
                    list.add(new StringTextComponent("\u00A77" + "Press [Shift] or [Ctrl] to view mod descriptions"));
                }
            }
        }
    }
