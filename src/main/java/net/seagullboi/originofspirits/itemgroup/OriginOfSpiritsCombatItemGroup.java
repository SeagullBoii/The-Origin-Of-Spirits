
package net.seagullboi.originofspirits.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.seagullboi.originofspirits.item.TridacnaBladeItem;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

@OriginofspiritsModElements.ModElement.Tag
public class OriginOfSpiritsCombatItemGroup extends OriginofspiritsModElements.ModElement {
	public OriginOfSpiritsCombatItemGroup(OriginofspiritsModElements instance) {
		super(instance, 256);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("taborigin_of_spirits_combat") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(TridacnaBladeItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}

	public static ItemGroup tab;
}
