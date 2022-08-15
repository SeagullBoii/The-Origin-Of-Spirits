
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.AxeItem;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsToolsItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;
import net.seagullboi.originofspirits.registry.TOOSItems;

@OriginofspiritsModElements.ModElement.Tag
public class CursedSteelAxeItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:cursed_steel_axe")
	public static final Item block = null;

	public CursedSteelAxeItem(OriginofspiritsModElements instance) {
		super(instance, 684);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 569;
			}

			public float getEfficiency() {
				return 10f;
			}

			public float getAttackDamage() {
				return 8f;
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
		}, 1, -3f, new Item.Properties().group(OriginOfSpiritsToolsItemGroup.tab).isImmuneToFire()) {
		}.setRegistryName("cursed_steel_axe"));
	}
}
