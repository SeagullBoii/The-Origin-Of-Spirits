
package net.seagullboi.originofspirits.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.seagullboi.originofspirits.entity.MagneticRayProjectile;
import net.seagullboi.originofspirits.events.TOOSSoundEvents;
import net.seagullboi.originofspirits.registry.TOOSItems;
import net.seagullboi.originofspirits.util.ColorUtil;
import net.seagullboi.originofspirits.util.GlobalVarUtil;

import java.util.List;

public class MagneticWandItem extends AbstractMagicStaffItem{

	public MagneticWandItem(float attackDamageIn, float attackSpeedIn, IItemTier tier, Properties builderIn) {
		super(attackDamageIn, attackSpeedIn, tier, builderIn);
	}

	@Override
	public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(itemstack, world, list, flag);
		list.add(new StringTextComponent(ColorUtil.AQUA + "6 Magic Damage"));
		list.add(new StringTextComponent(ColorUtil.AQUA + "[1 Mana Cost]"));
		list.add(new StringTextComponent(ColorUtil.RED + "Damage Deviates With The Magnetic Ray"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entityIn, Hand hand) {
		super.onItemRightClick(world, entityIn, hand);
		if (entityIn instanceof PlayerEntity) {
			shootRays(entityIn, world);
		}
		return super.onItemRightClick(world, entityIn, hand);
	}

	public void shootRays(PlayerEntity player, World world) {
		if (!player.isCreative()) {
			if (1 <= GlobalVarUtil.getMana(player)) {
				GlobalVarUtil.setMana(player, GlobalVarUtil.getMana(player) - 1);
				GlobalVarUtil.setManaCooldown(player, 40);
				MagneticRayProjectile.spreadProjectiles(player, 5, 5, 6);
				world.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), TOOSSoundEvents.MAGIC_STAFF_USE.get(), SoundCategory.PLAYERS, 2, 1, true);

				if (player.getHeldItemMainhand().getItem() == this) {
					player.getHeldItemMainhand().damageItem(1, player, (entityIn) -> player.sendBreakAnimation(EquipmentSlotType.MAINHAND));
					player.swing(Hand.MAIN_HAND, true);
				} else if (player.getHeldItemOffhand().getItem() == this) {
					player.getHeldItemOffhand().damageItem(1, player, (entityIn) -> player.sendBreakAnimation(EquipmentSlotType.OFFHAND));
					player.swing(Hand.OFF_HAND, true);
				}

				player.getCooldownTracker().setCooldown(TOOSItems.MAGNETIC_WAND.get(), 20);
				player.stopActiveHand();
			}
		} else {
			MagneticRayProjectile.spreadProjectiles(player, 5, 5, 6);
			world.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), TOOSSoundEvents.MAGIC_STAFF_USE.get(), SoundCategory.PLAYERS, 2, 1, true);
			player.getCooldownTracker().setCooldown(TOOSItems.MAGNETIC_WAND.get(), 30);
			if (player.getHeldItemMainhand().getItem() == this) {
				player.swing(Hand.MAIN_HAND, true);
			} else if (player.getHeldItemOffhand().getItem() == this) {
				player.swing(Hand.OFF_HAND, true);
			}
			player.stopActiveHand();
		}
	}

	public void swing(PlayerEntity player) {
		if (player.getHeldItemMainhand().getItem() == TOOSItems.MAGNETIC_WAND.get()) {
			player.stopActiveHand();
		}
	}
}