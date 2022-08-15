
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsCombatItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

@OriginofspiritsModElements.ModElement.Tag
public class TridacnaBladeItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:tridacna_blade")
	public static final Item block = null;

	public TridacnaBladeItem(OriginofspiritsModElements instance) {
		super(instance, 326);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 1600;
			}

			public float getEfficiency() {
				return 7f;
			}

			public float getAttackDamage() {
				return 4f;
			}

			public int getHarvestLevel() {
				return 3;
			}

			public int getEnchantability() {
				return 10;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(TridacnaShardItem.block));
			}
		}, 3, -2.4f, new Item.Properties().group(OriginOfSpiritsCombatItemGroup.tab)) {
		}.setRegistryName("tridacna_blade"));
	}
}
