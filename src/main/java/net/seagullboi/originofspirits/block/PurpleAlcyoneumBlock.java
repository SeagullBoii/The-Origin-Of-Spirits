
package net.seagullboi.originofspirits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ObjectHolder;
import net.seagullboi.originofspirits.OriginofspiritsModElements;
import net.seagullboi.originofspirits.registry.TOOSItemGroup;

import java.util.Collections;
import java.util.List;

@OriginofspiritsModElements.ModElement.Tag
public class PurpleAlcyoneumBlock extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:purple_alcyoneum")
	public static final Block block = null;

	public PurpleAlcyoneumBlock(OriginofspiritsModElements instance) {
		super(instance, 20);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(TOOSItemGroup.BLOCK_GROUP)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.BLUE_TERRACOTTA).sound(SoundType.WET_GRASS).hardnessAndResistance(1.5f, 6f).setLightLevel(s -> 0)
					.harvestLevel(0).harvestTool(ToolType.PICKAXE).setRequiresTool());
			setRegistryName("purple_alcyoneum");
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 15;
		}

		@Override
		public MaterialColor getMaterialColor() {
			return MaterialColor.BLUE;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
