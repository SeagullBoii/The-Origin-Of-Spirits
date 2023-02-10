
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

public class MagneticWandItem extends AbstractMagicStaffItem {

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

	public void shootRays(PlayerEntity playerIn, World world) {
		if (!playerIn.isCreative()) {
			if (1 <= GlobalVarUtil.getMana(playerIn)) {
				GlobalVarUtil.setMana(playerIn, GlobalVarUtil.getMana(playerIn) - 1);
				GlobalVarUtil.setManaCooldown(playerIn, 40);
				MagneticRayProjectile.spreadProjectiles(playerIn, 5, 5, 6);
				world.playSound(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), TOOSSoundEvents.MAGIC_STAFF_USE.get(), SoundCategory.PLAYERS, 2, 1, true);

				if (playerIn.getHeldItemMainhand().getItem() == this) {
					playerIn.getHeldItemMainhand().damageItem(1, playerIn, (entityIn) -> playerIn.sendBreakAnimation(EquipmentSlotType.MAINHAND));
					playerIn.swing(Hand.MAIN_HAND, true);
				} else if (playerIn.getHeldItemOffhand().getItem() == this) {
					playerIn.getHeldItemOffhand().damageItem(1, playerIn, (entityIn) -> playerIn.sendBreakAnimation(EquipmentSlotType.OFFHAND));
					playerIn.swing(Hand.OFF_HAND, true);
				}

				playerIn.getCooldownTracker().setCooldown(TOOSItems.MAGNETIC_WAND.get(), 40);

				playerIn.stopActiveHand();
			}
		} else {
			MagneticRayProjectile.spreadProjectiles(playerIn, 5, 5, 6);
			world.playSound(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), TOOSSoundEvents.MAGIC_STAFF_USE.get(), SoundCategory.PLAYERS, 2, 1, true);
			playerIn.getCooldownTracker().setCooldown(TOOSItems.MAGNETIC_WAND.get(), 30);
			if (playerIn.getHeldItemMainhand().getItem() == this) {
				playerIn.swing(Hand.MAIN_HAND, true);
			} else if (playerIn.getHeldItemOffhand().getItem() == this) {
				playerIn.swing(Hand.OFF_HAND, true);
			}
			playerIn.stopActiveHand();
			playerIn.getCooldownTracker().setCooldown(TOOSItems.MAGNETIC_WAND.get(), 40);

		}
	}

	public void swing(PlayerEntity playerIn) {
		if (playerIn.getHeldItemMainhand().getItem() == TOOSItems.MAGNETIC_WAND.get()) {
			playerIn.stopActiveHand();
		}
	}
}