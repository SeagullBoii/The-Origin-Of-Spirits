
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsCombatItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;
import net.seagullboi.originofspirits.registry.TOOSItems;

@OriginofspiritsModElements.ModElement.Tag
public class CursedSteelSwordItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:cursed_steel_sword")
	public static final Item block = null;

	public CursedSteelSwordItem(OriginofspiritsModElements instance) {
		super(instance, 685);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 1800;
			}

			public float getEfficiency() {
				return 10f;
			}

			public float getAttackDamage() {
				return 5f;
			}

			public int getHarvestLevel() {
				return 4;
			}

			public int getEnchantability() {
				return 25;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(TOOSItems.CURSED_STEEL.get()));
			}
		}, 3, -2.4f, new Item.Properties().group(OriginOfSpiritsCombatItemGroup.tab).isImmuneToFire()) {
		}.setRegistryName("cursed_steel_sword"));
	}
}
