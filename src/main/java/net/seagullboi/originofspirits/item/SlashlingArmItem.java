
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.client.util.ITooltipFlag;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsCombatItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

import java.util.List;

@OriginofspiritsModElements.ModElement.Tag
public class SlashlingArmItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:slashling_arm")
	public static final Item block = null;

	public SlashlingArmItem(OriginofspiritsModElements instance) {
		super(instance, 465);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 1662;
			}

			public float getEfficiency() {
				return 9f;
			}

			public float getAttackDamage() {
				return 3.5f;
			}

			public int getHarvestLevel() {
				return 3;
			}

			public int getEnchantability() {
				return 13;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Items.ENDER_PEARL));
			}
		}, 3, -2.5f, new Item.Properties().group(OriginOfSpiritsCombatItemGroup.tab)) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A79Press R To Perform A Dash"));
				list.add(new StringTextComponent("\u00A79 15 Second Cooldown"));
			}
		}.setRegistryName("slashling_arm"));
	}
}
