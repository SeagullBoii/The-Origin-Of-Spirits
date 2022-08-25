package net.seagullboi.originofspirits.item;

import net.minecraft.block.SoundType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.seagullboi.originofspirits.entity.BulletEntity;
import net.seagullboi.originofspirits.events.TOOSSoundEvents;
import net.seagullboi.originofspirits.registry.TOOSItems;
import net.seagullboi.originofspirits.util.ColorUtil;
import net.seagullboi.originofspirits.util.ItemUtil;

import java.util.List;
import java.util.Random;

public class RedstoneHandgunItem extends Item {

    int shotsFired = 0;
    int knockback = 0;
    int bulletCount = 1;
    int cooldown = 12;
    double damage = 3;
    float inaccuracy = 0f;

    public RedstoneHandgunItem(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(itemstack, world, list, flag);

        //Base Info
        list.add(new StringTextComponent(ColorUtil.DARK_GREEN + "6 Ranged Damage"));
        list.add(new StringTextComponent(""));
        list.add(new StringTextComponent("Level:" + ColorUtil.GOLD + " Gold"));

        //Weapon Mod
        if (itemstack.getOrCreateTag().getInt("weapon_mod") <= 0) {
            list.add(new StringTextComponent(ColorUtil.GRAY + "Weapon Mod : Null"));
        } else if (itemstack.getOrCreateTag().getInt("weapon_mod") == 1) {
            list.add(new StringTextComponent(ColorUtil.GRAY + "Weapon Mod : Full-Auto"));
            if (Screen.hasShiftDown() || Screen.hasControlDown()) {
                list.add(new StringTextComponent(""));
                list.add(new StringTextComponent(ColorUtil.GRAY + "Hold to keep shooting"));
                list.add(new StringTextComponent(ColorUtil.DARK_GREEN + "Shoots 2 bullets"));
                list.add(new StringTextComponent(ColorUtil.RED + "Less accurate"));
                list.add(new StringTextComponent(ColorUtil.RED + "Overheats after 10 shots"));
            } else {
                list.add(new StringTextComponent(""));
                list.add(new StringTextComponent(ColorUtil.GRAY + "Press [Shift] or [Ctrl] to view mod descriptions"));
            }
        } else if (itemstack.getOrCreateTag().getInt("weapon_mod") == 2) {
            list.add(new StringTextComponent(ColorUtil.GRAY + "Weapon Mod : Knocker"));
            if (Screen.hasShiftDown() || Screen.hasControlDown()) {
                list.add(new StringTextComponent(""));
                list.add(new StringTextComponent(ColorUtil.DARK_GREEN + "+1 Knockback"));
                list.add(new StringTextComponent(ColorUtil.DARK_GREEN + "+1 Damage"));
                list.add(new StringTextComponent(ColorUtil.RED + "+0.3s Cooldown"));

            } else {
                list.add(new StringTextComponent(""));
                list.add(new StringTextComponent(ColorUtil.GRAY + "Press [Shift] or [Ctrl] to view mod descriptions"));
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
        super.onItemRightClick(world, entity, hand);
        Random rand = new Random();
        ItemStack bullet = new ItemStack(TOOSItems.IRON_BULLET.get());

        if (entity instanceof PlayerEntity) {
            PlayerEntity player = entity;

            if (player.getHeldItemMainhand().getOrCreateTag().getInt("weapon_mod") >= 2 || player.getHeldItemOffhand().getOrCreateTag().getInt("weapon_mod") >= 2) {
                this.setKnockback(1);
                this.setInaccurasy(0);
                this.setDamage(3.5);
                this.setCooldown(18);
                this.bulletCount = 1;
            } else if (player.getHeldItemMainhand().getOrCreateTag().getInt("weapon_mod") == 1 || player.getHeldItemOffhand().getOrCreateTag().getInt("weapon_mod") == 1) {
                this.setKnockback(0);
                this.setInaccurasy(5f);
                this.setDamage(3);
                this.setCooldown(0);
                this.bulletCount = 2;
            } else {
                this.setKnockback(0);
                this.setInaccurasy(0);
                this.setDamage(3);
                this.setCooldown(12);
                this.bulletCount = 1;
            }

            if (player.getHeldItemMainhand().getOrCreateTag().getInt("weapon_mod") != 1 || player.getHeldItemOffhand().getOrCreateTag().getInt("weapon_mod") != 1) {
                if (player.inventory.hasItemStack(bullet)) {
                    if (!player.abilities.isCreativeMode) {
                        entity.inventory.func_234564_a_(stack -> bullet.getItem() == stack.getItem(), 1, entity.container.func_234641_j_());

                        if (player.getHeldItemMainhand().getItem() == this) {
                            entity.getHeldItemMainhand().damageItem(1, player, (entityIn) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                        } else if (player.getHeldItemOffhand().getItem() == this) {
                            entity.getHeldItemOffhand().damageItem(1, player, (entityIn) -> entity.sendBreakAnimation(EquipmentSlotType.OFFHAND));
                        }

                        for (int i = 0; i < bulletCount; i++) {
                            BulletEntity.shoot(world, entity, rand, 2, this.getDamage(), this.getKnockback(), 5, this.getInaccurassy(), "redstone");
                        }

                        world.playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), TOOSSoundEvents.GUN.get(), SoundCategory.PLAYERS, 2, 1.75f, true);
                        player.getCooldownTracker().setCooldown(TOOSItems.REDSTONE_HANDGUN.get(), this.getCooldown());
                    }
                }
                if (player.abilities.isCreativeMode) {

                    for (int i = 0; i < bulletCount; i++) {
                        BulletEntity.shoot(world, entity, rand, 2, this.getDamage(), this.getKnockback(), 5, this.getInaccurassy(), "redstone");
                    }

                    world.playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), TOOSSoundEvents.GUN.get(), SoundCategory.PLAYERS, 2, 1.75f, true);
                    player.getCooldownTracker().setCooldown(TOOSItems.REDSTONE_HANDGUN.get(), this.getCooldown());
                }

                if (entity.getHeldItemMainhand().getOrCreateTag().getInt("weapon_mod") == 1) {
                    if (this.getShotsFired() < 10) {
                        this.setShotsFired(this.getShotsFired() + 1);
                    } else {
                        world.playSound(entity.getPosX(), entity.getPosY(), entity.getPosY(), SoundEvents.ENTITY_CREEPER_PRIMED, SoundCategory.PLAYERS, 1, 1, true);
                        entity.getHeldItemMainhand().getOrCreateTag().putBoolean("overheat", true);
                        player.getCooldownTracker().setCooldown(TOOSItems.REDSTONE_HANDGUN.get(), 40);
                        this.setShotsFired(0);
                    }
                }
                entity.stopActiveHand();
            }
        }
        return super.onItemRightClick(world, entity, hand);
    }


    public double getDamage() {
        return damage;
    }

    public void setDamage(double dmg) {
        this.damage = dmg;
    }

    public int getKnockback() {
        return knockback;
    }

    public void setKnockback(int count) {
        this.knockback = count;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int count) {
        this.cooldown = count;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public void setShotsFired(int count) {
        this.shotsFired = count;
    }

    public float getInaccurassy() {
        return this.inaccuracy;
    }

    public void setInaccurasy(float inaccurasy) {
        this.inaccuracy = inaccurasy;
    }

}
