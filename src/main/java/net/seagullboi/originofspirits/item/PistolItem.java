package net.seagullboi.originofspirits.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
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

import java.util.*;

public class PistolItem extends Item {
    public PistolItem(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(itemstack, world, list, flag);
        list.add(new StringTextComponent("\u00A724 \u00A72Ranged \u00A72Damage"));
        list.add(new StringTextComponent("\u00A77Level: None"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
      super.onItemRightClick(world, entity, hand);
        Random rand = new Random();

        ItemStack ironBullet = new ItemStack(TOOSItems.IRON_BULLET.get());

        if (entity instanceof PlayerEntity) {
            PlayerEntity player = entity;
            if (player.inventory.hasItemStack(ironBullet)) {
                if (!player.abilities.isCreativeMode) {
                    entity.inventory.func_234564_a_(stack -> ironBullet.getItem() == stack.getItem(), 1, entity.container.func_234641_j_());
                    if (player.getHeldItemMainhand().getItem() == this) {
                        entity.getHeldItemMainhand().damageItem(1, player, (entityIn) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                    } else if (player.getHeldItemOffhand().getItem() == this) {
                        entity.getHeldItemOffhand().damageItem(1, player, (entityIn) -> entity.sendBreakAnimation(EquipmentSlotType.OFFHAND));
                    }
                    BulletEntity.shoot(world, entity, rand, 2, 2, 0, 5, 0, "iron");
                    world.playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), TOOSSoundEvents.GUN.get(), SoundCategory.PLAYERS, 1, 1f, true);
                    player.getCooldownTracker().setCooldown(TOOSItems.PISTOL.get(), 10);
                }
            }
            if (player.abilities.isCreativeMode) {
                BulletEntity.shoot(world, entity, rand, 2, 2, 0, 5, 0, "iron");
                world.playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), TOOSSoundEvents.GUN.get(), SoundCategory.PLAYERS, 1, 1f, true);
                player.getCooldownTracker().setCooldown(TOOSItems.PISTOL.get(), 10);
            }
            entity.stopActiveHand();
        }
        return super.onItemRightClick(world, entity, hand);
    }
}
