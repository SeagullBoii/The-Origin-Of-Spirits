package net.seagullboi.originofspirits.item;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.seagullboi.originofspirits.entity.BulletEntity;
import net.seagullboi.originofspirits.events.TOOSSoundEvents;
import net.seagullboi.originofspirits.registry.TOOSItems;

import java.util.List;
import java.util.Random;

public class ShotgunItem extends Item {

    double damage = 2;
    float inaccuracy = 5f;
    int bulletCount = 5;

    public ShotgunItem(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(itemstack, world, list, flag);
        list.add(new StringTextComponent("\u00A722.5 - 12.5 \u00A72Ranged \u00A72Damage"));
        list.add(new StringTextComponent("\u00A77Level: \u00A7fIron"));
        //Weapon Mod
        if (itemstack.getOrCreateTag().getInt("weapon_mod") <= 0) {
            list.add(new StringTextComponent("\u00A77" + "Weapon Mod : Null"));
        } else if (itemstack.getOrCreateTag().getInt("weapon_mod") >= 1) {
            list.add(new StringTextComponent("\u00A77" + "Weapon Mod : Sawed Off"));
            if (Screen.hasShiftDown() || Screen.hasControlDown()) {
                list.add(new StringTextComponent(""));
                list.add(new StringTextComponent("\u00A72" + "Doubles the amount of bullets"));
                list.add(new StringTextComponent("\u00A7c" + "Less accurate"));

            } else {
                list.add(new StringTextComponent(""));
                list.add(new StringTextComponent("\u00A77" + "Press [Shift] or [Ctrl] to view mod descriptions"));
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
        super.onItemRightClick(world, entity, hand);
        Random rand = new Random();
        ItemStack shells = new ItemStack(TOOSItems.SHOTGUN_SHELLS.get());

        if (entity instanceof PlayerEntity) {
            PlayerEntity player = entity;

            if (player.getHeldItemMainhand().getOrCreateTag().getInt("weapon_mod") >= 1 || player.getHeldItemOffhand().getOrCreateTag().getInt("weapon_mod") >= 1) {
                this.setBulletCount(10);
                this.setInaccurasy(10f);
            } else {
                this.setBulletCount(5);
                this.setInaccurasy(5f);
            }

            if (player.inventory.hasItemStack(shells)) {
                if (!player.abilities.isCreativeMode) {
                    entity.inventory.func_234564_a_(stack -> shells.getItem() == stack.getItem(), 1, entity.container.func_234641_j_());

                    if (player.getHeldItemMainhand().getItem() == this) {
                        entity.getHeldItemMainhand().damageItem(1, player, (entityIn) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                    } else if (player.getHeldItemOffhand().getItem() == this) {
                        entity.getHeldItemOffhand().damageItem(1, player, (entityIn) -> entity.sendBreakAnimation(EquipmentSlotType.OFFHAND));
                    }

                    for (int i = 0; i < this.getBulletCount(); i++ ) {
                        BulletEntity.shoot(world, entity, rand, 2, 1.25, 0, 5, this.getInaccurassy(), "shotgun");
                    }

                    world.playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), TOOSSoundEvents.SHOTGUN.get(), SoundCategory.PLAYERS, 2, 1f, true);
                    player.getCooldownTracker().setCooldown(TOOSItems.SHOTGUN.get(), 17);
                }
            }

            if (player.abilities.isCreativeMode) {

                for (int i = 0; i < this.getBulletCount(); i++ ) {
                    BulletEntity.shoot(world, entity, rand, 2, 1.25, 0, 5, this.getInaccurassy(), "shotgun");
                }

                world.playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), TOOSSoundEvents.SHOTGUN.get(), SoundCategory.PLAYERS, 2, 1f, true);
                player.getCooldownTracker().setCooldown(TOOSItems.SHOTGUN.get(), 17);
            }
            entity.stopActiveHand();
        }
        return super.onItemRightClick(world, entity, hand);
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double dmg) {
        this.damage = dmg;
    }

    public int getBulletCount() {
        return bulletCount;
    }

    public void setBulletCount(int count) {
        this.bulletCount = count;
    }

    public float getInaccurassy() {
        return this.inaccuracy;
    }

    public void setInaccurasy(float inaccurasy) {
        this.inaccuracy = inaccurasy;
    }
}
