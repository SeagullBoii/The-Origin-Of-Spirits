
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BowItem;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsCombatItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

@OriginofspiritsModElements.ModElement.Tag
public class TempestBowItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:tempest_bow")
	public static final Item block = null;

	public TempestBowItem(OriginofspiritsModElements instance) {
		super(instance, 431);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends BowItem {
		public float bowdamagebonus = 3.5F;

		public ItemCustom() {
			super(new Item.Properties().group(OriginOfSpiritsCombatItemGroup.tab).maxStackSize(1).rarity(Rarity.COMMON));
			setRegistryName("tempest_bow");
		}

		public AbstractArrowEntity customArrow(AbstractArrowEntity arrow, ItemStack stack) {
			arrow.setDamage(arrow.getDamage() + bowdamagebonus);
			int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
			if (j > 0) {
				arrow.setDamage((arrow.getDamage() + bowdamagebonus) + (double) j * 0.5D + 0.5D);
			}
			int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
			if (k > 0) {
				arrow.setKnockbackStrength(k);
			}
			int m = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack);
			if (m > 0) {
				arrow.setFire(100);
			}
			return super.customArrow(arrow);
		}

		//The distance that hostile entities holding the ranged weapon begin shooting the player at
		public int getDefaultProjectileRange() {
			return 20;
		}

		//Bows always have arbitrarily long usedurations
		@Override
		public int getUseDuration(ItemStack p_77626_1_) {
			return 60000;
		}

		public UseAction getUseAnimation(ItemStack p_77661_1_) {
			return UseAction.BOW;
		}
	}
}
