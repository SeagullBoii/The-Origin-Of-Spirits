
package net.seagullboi.originofspirits.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.seagullboi.originofspirits.item.TempestRunnerEggItem;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

@OriginofspiritsModElements.ModElement.Tag
public class OriginOfSpiritsEntitiesItemGroup extends OriginofspiritsModElements.ModElement {
	public OriginOfSpiritsEntitiesItemGroup(OriginofspiritsModElements instance) {
		super(instance, 259);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("taborigin_of_spirits_entities") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(TempestRunnerEggItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}

	public static ItemGroup tab;
}
