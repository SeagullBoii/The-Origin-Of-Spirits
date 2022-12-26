package net.seagullboi.originofspirits.registry;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.block.*;
import net.seagullboi.originofspirits.item.BasicDescriptionBlockItem;
import net.seagullboi.originofspirits.item.SpitePedestalItem;
import net.seagullboi.originofspirits.potion.CursedPotionEffect;
import net.seagullboi.originofspirits.util.ColorUtil;
import net.seagullboi.originofspirits.util.TOOSMaterials;
import net.seagullboi.originofspirits.util.TOOSSoundTypes;

import java.util.function.Supplier;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TOOSBlocks {
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, OriginOfSpirits.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OriginOfSpirits.MOD_ID);
	public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OriginOfSpirits.MOD_ID);

	//Abyssal Stone
	public static final RegistryObject<Block> ABYSSAL_STONE = registerBlock("abyssal_stone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);

	//Blessed Stone
	public static final RegistryObject<Block> BLESSED_STONE = registerBlock("blessed_stone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_SLAB = registerBlock("blessed_stone_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_STAIRS = registerBlock("blessed_stone_stairs", () -> new StairsBlock(() -> BLESSED_STONE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_WALL = registerBlock("blessed_stone_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_BRICKS = registerBlock("blessed_stone_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_BRICKS_SLAB = registerBlock("blessed_stone_bricks_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_BRICKS_STAIRS = registerBlock("blessed_stone_bricks_stairs", () -> new StairsBlock(() -> BLESSED_STONE_BRICKS.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_BRICKS_WALL = registerBlock("blessed_stone_bricks_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_BRICKS_SMALL = registerBlock("blessed_stone_small", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_BRICKS_SMALL_SLAB = registerBlock("blessed_stone_bricks_small_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_BRICKS_SMALL_STAIRS = registerBlock("blessed_stone_bricks_small_stairs", () -> new StairsBlock(() -> BLESSED_STONE_BRICKS_SMALL.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_BRICKS_SMALL_WALL = registerBlock("blessed_stone_bricks_small_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_TILES = registerBlock("blessed_stone_tiles", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_TILES_SLAB = registerBlock("blessed_stone_tiles_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_TILES_STAIRS = registerBlock("blessed_stone_tiles_stairs", () -> new StairsBlock(() -> BLESSED_STONE_TILES.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> BLESSED_STONE_TILES_WALL = registerBlock("blessed_stone_tiles_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CHISELED_BLESSED_STONE = registerBlock("chiseled_blessed_stone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CRACKED_BLESSED_STONE_BRICKS = registerBlock("cracked_blessed_stone_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CUT_BLESSED_STONE = registerBlock("cut_blessed_stone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> POLISHED_BLESSED_STONE = registerBlock("polished_blessed_stone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> POLISHED_BLESSED_STONE_SLAB = registerBlock("polised_blessed_stone_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> POLISHED_BLESSED_STONE_STAIRS = registerBlock("polised_blessed_stone_stairs", () -> new StairsBlock(() -> POLISHED_BLESSED_STONE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> POLISHED_BLESSED_STONE_WALL = registerBlock("polished_blessed_stone_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);

	//Deceptone
	public static final RegistryObject<Block> DECEPTONE = registerBlock("deceptone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DECEPTONE_SLAB = registerBlock("deceptone_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DECEPTONE_STAIRS = registerBlock("deceptone_stairs", () -> new StairsBlock(() -> DECEPTONE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DECEPTONE_WALL = registerBlock("deceptone_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DECEPTONE_BRICKS = registerBlock("deceptone_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DECEPTONE_BRICKS_SLAB = registerBlock("deceptone_bricks_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DECEPTONE_BRICKS_STAIRS = registerBlock("deceptone_bricks_stairs", () -> new StairsBlock(() -> DECEPTONE_BRICKS.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DECEPTONE_BRICKS_WALL = registerBlock("deceptone_bricks_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DECEPTONE_BRICKS_SMALL = registerBlock("deceptone_bricks_small", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DECEPTONE_BRICKS_SMALL_SLAB = registerBlock("deceptone_bricks_small_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DECEPTONE_BRICKS_SMALL_STAIRS = registerBlock("deceptone_bricks_small_stairs", () -> new StairsBlock(() -> DECEPTONE_BRICKS_SMALL.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DECEPTONE_BRICKS_SMALL_WALL = registerBlock("deceptone_bricks_small_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);

	public static final RegistryObject<Block> CHISELED_DECEPTONE = registerBlock("chiseled_deceptone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CRACKED_DECEPTONE_BRICKS = registerBlock("cracked_deceptone_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CUT_DECEPTONE = registerBlock("cut_deceptone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> POLISHED_DECEPTONE = registerBlock("polished_deceptone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> POLISHED_DECEPTONE_SLAB = registerBlock("polished_deceptone_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> POLISHED_DECEPTONE_STAIRS = registerBlock("polished_deceptone_stairs", () -> new StairsBlock(() -> POLISHED_DECEPTONE.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> POLISHED_DECEPTONE_WALL = registerBlock("polished_deceptone_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);

	//Grass and Dirt

	public static final RegistryObject<Block> SACRED_SOIL = registerBlock("sacred_soil", () -> new Block(AbstractBlock.Properties.from(Blocks.DIRT)), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> SACRED_GRASS = registerBlock("sacred_grass_block", () -> new TOOSGrassBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK), SACRED_SOIL.get()), TOOSItemGroup.BLOCK_GROUP);


	//Gore
	public static final RegistryObject<Block> CURSED_GORE = registerBlock("cursed_gore_block", () -> new Block(AbstractBlock.Properties.create(TOOSMaterials.CURSED_GORE).sound(TOOSSoundTypes.GORE).hardnessAndResistance(0.5f, 0.5f).setLightLevel(s -> 0).harvestTool(ToolType.SHOVEL)), TOOSItemGroup.BLOCK_GROUP);

	//Sand
	public static final RegistryObject<Block> BLACK_SAND = registerBlock("black_sand", () -> new SandBlock(15265262, AbstractBlock.Properties.create(Material.SAND, MaterialColor.BLACK).sound(SoundType.SAND).hardnessAndResistance(0.5F).setLightLevel(s -> 0).harvestTool(ToolType.SHOVEL)), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> WHITE_SAND = registerBlock("white_sand", () -> new SandBlock(2324344, AbstractBlock.Properties.create(Material.SAND, MaterialColor.SNOW).sound(SoundType.SAND).hardnessAndResistance(0.5F).setLightLevel(s -> 0).harvestTool(ToolType.SHOVEL)), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> PINK_SAND = registerBlock("pink_sand", () -> new PinkSandBlock(2324344, AbstractBlock.Properties.create(Material.SAND, MaterialColor.PINK).sound(SoundType.SAND).hardnessAndResistance(0.5F).setLightLevel(s -> 0).harvestTool(ToolType.SHOVEL)), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CRYSTALLIZED_PINK_SAND = registerBlock("crystalized_pink_sand", () -> new Block(AbstractBlock.Properties.create(Material.SAND, MaterialColor.PINK).sound(SoundType.SAND).hardnessAndResistance(0.5F).harvestTool(ToolType.SHOVEL)), TOOSItemGroup.BLOCK_GROUP);

	//Ores
	public static final RegistryObject<Block> CURSED_STEEL_ORE = registerBlock("cursed_steel_ore", () -> new OreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 100.0f).setLightLevel(s -> 0).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> RAW_CURSED_STEEL_BLOCK = registerBlock("raw_cursed_steel_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 100.0f).setLightLevel(s -> 0).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CURSED_STEEL_BLOCK = registerBlock("cursed_steel_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.LIGHT_GRAY_TERRACOTTA).sound(SoundType.METAL).hardnessAndResistance(1.5F, 100.0f).setLightLevel(s -> 0).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> SMENEREL_ORE = registerBlock("smenerel_ore", () -> new OreBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.SAND).sound(SoundType.METAL).hardnessAndResistance(1, 10.0f).setLightLevel(s -> 0).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> SMENEREL_BLOCK = registerBlock("smenerel_block", () -> new OreBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.SAND).sound(SoundType.NETHER_BRICK).hardnessAndResistance(1, 10.0f).setLightLevel(s -> 0).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);

	//MISC
	public static final RegistryObject<Block> DEEP_ALCYONEUM_TOP = registerBlock("deep_alcyonium_top", () -> new Block(AbstractBlock.Properties.create(Material.CORAL, MaterialColor.GREEN_TERRACOTTA).sound(SoundType.CORAL).hardnessAndResistance(0.6F).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE)), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> DEEP_ALCYONEUM = registerBlock("green_alcyoneum", () -> new Block(AbstractBlock.Properties.create(Material.CORAL, MaterialColor.GREEN_TERRACOTTA).sound(SoundType.CORAL).hardnessAndResistance(0.6F).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE)), TOOSItemGroup.BLOCK_GROUP);

	public static final RegistryObject<Block> CLAM_BLOCK = registerBlock("clam_block", () -> new ClamBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CLOUD_BLOCK = registerBlock("cloud_block", () -> new CloudBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS, MaterialColor.SNOW).sound(SoundType.CLOTH).hardnessAndResistance(0.3f).notSolid().doesNotBlockMovement().speedFactor(0.4f).jumpFactor(0.4f).setLightLevel(s -> 0).harvestLevel(1).harvestTool(ToolType.SHOVEL), false), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> RAINY_CLOUD_BLOCK = registerBlock("rainy_cloud_block", () -> new CloudBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS, MaterialColor.LIGHT_BLUE_TERRACOTTA).sound(SoundType.CLOTH).hardnessAndResistance(0.3f).notSolid().doesNotBlockMovement().speedFactor(0.4f).jumpFactor(0.4f).setLightLevel(s -> 0).harvestLevel(1).harvestTool(ToolType.SHOVEL), true), TOOSItemGroup.BLOCK_GROUP);
    public static final RegistryObject<Block> CURSED_CLOUD_BLOCK = registerBlock("cursed_cloud_block", () -> new CursedCloudBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS, MaterialColor.SNOW).sound(SoundType.CLOTH).hardnessAndResistance(0.3f).notSolid().doesNotBlockMovement().speedFactor(0.4f).jumpFactor(0.4f).setLightLevel(s -> 0).harvestLevel(1).harvestTool(ToolType.SHOVEL), false,CursedPotionEffect.potion), TOOSItemGroup.BLOCK_GROUP);
    public static final RegistryObject<Block> TEMPEST_CRYSTAL = registerBlock("tempest_crystal", () -> new GlassBlock(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.LIGHT_BLUE).sound(SoundType.GLASS).zeroHardnessAndResistance().notSolid().setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CURSED_FIRE = BLOCKS.register("cursed_fire", () -> new CursedFireBlock(AbstractBlock.Properties.create(Material.FIRE, MaterialColor.PURPLE).speedFactor(0.9f).jumpFactor(0.9f).notSolid().setNeedsPostProcessing(TOOSBlocks::needsPostProcessing).setEmmisiveRendering(TOOSBlocks::needsPostProcessing).setLightLevel((state) -> 10)));

	//Pedestals
	public static final RegistryObject<Block> SPITE_PEDESTAL = BLOCKS.register("spite_pedestal", () -> new SpitePedestalBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.RED).sound(SoundType.METAL).hardnessAndResistance(0.6F).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).notSolid().setRequiresTool()));
	public static final RegistryObject<Item> SPITE_PEDESTAL_ITEM = BLOCK_ITEMS.register("spite_pedestal", () -> new SpitePedestalItem(SPITE_PEDESTAL.get(), new Item.Properties().group(TOOSItemGroup.BLOCK_GROUP)));

	//Cave Generators
	public static final RegistryObject<Block> CURSED_CAVE_GENERATOR = BLOCKS.register("cursed_cave_gen", () -> new BasicCaveGenBlock(AbstractBlock.Properties.from(Blocks.BARRIER)));

	//Advanced Blocks
	public static final RegistryObject<Block> GUNSMITHING_TABLE_BLOCK = registerBlock("gunsmithing_table", () -> new GunsmithingTableBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.LIGHT_GRAY).sound(SoundType.METAL).hardnessAndResistance(3.5F).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> PRESSING_MACHINE = registerBlock("pressing_machine", () -> new PressingMachineBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.LIGHT_GRAY).sound(SoundType.METAL).hardnessAndResistance(3.5F).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid()), TOOSItemGroup.BLOCK_GROUP, 64, ColorUtil.GRAY + "Right click to activate");

	public static final RegistryObject<Block> ABYSSAL_SPAWNER = BLOCKS.register("abyssal_spawner", () -> new AbyssalSpawnerBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS, MaterialColor.GRAY).sound(SoundType.METAL).hardnessAndResistance(5f, 20f).notSolid().harvestTool(ToolType.PICKAXE)));

	//Plants
    public static final RegistryObject<Block> DUCKWEED = BLOCKS.register("duckweed_plant", () -> new DuckweedBlock(AbstractBlock.Properties.create(Material.PLANTS).zeroHardnessAndResistance().sound(SoundType.LILY_PADS).notSolid().doesNotBlockMovement().setLightLevel(s -> 10)));
	public static final RegistryObject<Block> GLOWKELP = BLOCKS.register("glowkelp", () -> new GlowkelpTopBlock(AbstractBlock.Properties.create(Material.OCEAN_PLANT).zeroHardnessAndResistance().sound(SoundType.WET_GRASS).notSolid().doesNotBlockMovement().setLightLevel(s -> 1).setNeedsPostProcessing(TOOSBlocks::needsPostProcessing).setEmmisiveRendering(TOOSBlocks::needsPostProcessing)));
	public static final RegistryObject<Block> GLOWKELP_PLANT = BLOCKS.register("glow_kelp_plant", () -> new GlowkelpBlock(AbstractBlock.Properties.create(Material.OCEAN_PLANT).zeroHardnessAndResistance().sound(SoundType.WET_GRASS).notSolid().doesNotBlockMovement().setLightLevel(s -> 1).setNeedsPostProcessing(TOOSBlocks::needsPostProcessing).setEmmisiveRendering(TOOSBlocks::needsPostProcessing)));
	public static final RegistryObject<Block> ALCYONEUM_POLYPS = registerBlock("alcyonium_polyps", () -> new SeaPolypBlock(AbstractBlock.Properties.create(Material.CORAL, MaterialColor.PURPLE_TERRACOTTA).zeroHardnessAndResistance().sound(SoundType.CORAL).notSolid().doesNotBlockMovement().setLightLevel(s -> 0)), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> DEEP_ALCYONEUM_POLYPS = registerBlock("deep_alcyonium_polyps", () -> new SeaPolypBlock(AbstractBlock.Properties.create(Material.CORAL, MaterialColor.PURPLE_TERRACOTTA).zeroHardnessAndResistance().sound(SoundType.CORAL).notSolid().doesNotBlockMovement().setLightLevel(s -> 0)), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> DEEPSEA_ALGAE = registerBlock("deepsea_algae", () -> new DeepseaAlgaeBlock(AbstractBlock.Properties.create(Material.SEA_GRASS, MaterialColor.GREEN).zeroHardnessAndResistance().sound(SoundType.WET_GRASS).notSolid().doesNotBlockMovement().setLightLevel(s -> 0)), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> MAGIC_MAGNOLIA = registerBlock("magic_magnolia", () -> new FlowerBlock(Effects.ABSORPTION, 6, AbstractBlock.Properties.from(Blocks.CORNFLOWER)), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> BLESSED_ROSE = registerBlock("blessed_rose", () -> new FlowerBlock(Effects.REGENERATION, 10, AbstractBlock.Properties.from(Blocks.POPPY).notSolid()), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> CRYSTAL_LILY = registerBlock("crystal_lily", () -> new FlowerBlock(Effects.SPEED, 10, AbstractBlock.Properties.from(Blocks.BLUE_ORCHID).notSolid()), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> VIOLET_PERIWINKLE = registerBlock("violet_periwinkle", () -> new FlowerBlock(Effects.HASTE, 11, AbstractBlock.Properties.from(Blocks.PINK_TULIP).notSolid()), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> RED_PERIWINKLE = registerBlock("red_periwinkle", () -> new FlowerBlock(Effects.SATURATION, 11, AbstractBlock.Properties.from(Blocks.RED_TULIP).notSolid()), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> ORANGE_PERIWINKLE = registerBlock("orange_periwinkle", () -> new FlowerBlock(Effects.HEALTH_BOOST, 11, AbstractBlock.Properties.from(Blocks.DANDELION).notSolid()), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> PEACE_LILY = registerBlock("peace_lily", () -> new FlowerBlock(Effects.HEALTH_BOOST, 20, AbstractBlock.Properties.from(Blocks.LILY_OF_THE_VALLEY).notSolid()), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> SACRED_VIOLET = registerBlock("sacret_violet", () -> new FlowerBlock(Effects.SLOWNESS, 5, AbstractBlock.Properties.from(Blocks.LILY_OF_THE_VALLEY).notSolid()), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> BLUEBERRY_BUSH = BLOCKS.register("blueberry_bush", () -> new BlueberryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH)));
	public static final RegistryObject<Block> BUDDHAS_HAND_PLANT = BLOCKS.register("buddhas_hand_plant", () -> new BuddhasHandPlantBlock(AbstractBlock.Properties.create(Material.PLANTS).zeroHardnessAndResistance().setLightLevel(s -> 0).doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT).setOpaque((a, b, c) -> false)));
	public static final RegistryObject<Block> HARD_SUGAR_CANE = registerBlock("hard_sugar_cane", () -> new SugarCaneBlock(AbstractBlock.Properties.create(Material.PLANTS).zeroHardnessAndResistance().setLightLevel(s -> 0).doesNotBlockMovement().tickRandomly().setOpaque((a, b, c) -> false).sound(SoundType.PLANT)), TOOSItemGroup.PLANT_GROUP);

	//Flower Pots
	public static final RegistryObject<Block> POTTED_MAGIC_MAGNOLIA = BLOCKS.register("potted_magic_magnolia", () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), MAGIC_MAGNOLIA, AbstractBlock.Properties.from(Blocks.POTTED_BLUE_ORCHID)));
	public static final RegistryObject<Block> POTTED_BLESSED_ROSE = BLOCKS.register("potted_blessed_rose", () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), BLESSED_ROSE, AbstractBlock.Properties.from(Blocks.POTTED_BLUE_ORCHID)));
	public static final RegistryObject<Block> POTTED_CRYSTAL_LILY = BLOCKS.register("potted_crystal_lily", () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), CRYSTAL_LILY, AbstractBlock.Properties.from(Blocks.POTTED_BLUE_ORCHID)));
	public static final RegistryObject<Block> POTTED_VIOLET_PERIWINKLE = BLOCKS.register("potted_violet_periwinkle", () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), VIOLET_PERIWINKLE, AbstractBlock.Properties.from(Blocks.POTTED_BLUE_ORCHID)));
	public static final RegistryObject<Block> POTTED_RED_PERIWINKLE = BLOCKS.register("potted_red_periwinkle", () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), RED_PERIWINKLE, AbstractBlock.Properties.from(Blocks.POTTED_BLUE_ORCHID)));
	public static final RegistryObject<Block> POTTED_ORANGE_PERIWINKLE = BLOCKS.register("potted_orange_periwinkle", () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ORANGE_PERIWINKLE, AbstractBlock.Properties.from(Blocks.POTTED_BLUE_ORCHID)));
	public static final RegistryObject<Block> POTTED_PEACE_LILY = BLOCKS.register("potted_peace_lily", () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), PEACE_LILY, AbstractBlock.Properties.from(Blocks.POTTED_BLUE_ORCHID)));
	public static final RegistryObject<Block> POTTED_SACRED_VIOLET = BLOCKS.register("potted_sacred_violet", () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), SACRED_VIOLET, AbstractBlock.Properties.from(Blocks.POTTED_BLUE_ORCHID)));

	//Signs
	public static final RegistryObject<Block> SACREDWOOD_SIGN = BLOCKS.register("sacredwood_sign", () -> new ModStandingSignBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).doesNotBlockMovement().hardnessAndResistance(1, 1), ModWoodTypes.SACREDWOOD));
	public static final RegistryObject<Block> SACREDWOOD_WALL_SIGN = BLOCKS.register("sacredwood_wall_sign", () -> new ModWallSignBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).doesNotBlockMovement().hardnessAndResistance(1, 1), ModWoodTypes.SACREDWOOD));
	public static final RegistryObject<Block> SWIRLWOOD_SIGN = BLOCKS.register("swirlwood_sign", () -> new ModStandingSignBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).doesNotBlockMovement().hardnessAndResistance(1, 1), ModWoodTypes.SWIRLWOOD));
	public static final RegistryObject<Block> SWIRLWOOD_WALL_SIGN = BLOCKS.register("swirlwood_wall_sign", () -> new ModWallSignBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).doesNotBlockMovement().hardnessAndResistance(1, 1), ModWoodTypes.SWIRLWOOD));

	//Crops
	public static final RegistryObject<Block> SWEET_POTATOES = BLOCKS.register("sweet_potatoes", () -> new SweetPotatoBlock(AbstractBlock.Properties.from(Blocks.POTATOES)));
	public static final RegistryObject<Block> BARLEY = BLOCKS.register("barley", () -> new BarleyBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
	public static final RegistryObject<Block> BEAN_PLANT = BLOCKS.register("beans_plant", () -> new BeansPlantBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));

	//Fluids
	public static final ResourceLocation CURSED_LAVA_STILL_RL = new ResourceLocation(OriginOfSpirits.MOD_ID, "block/cursed_lava_still");
	public static final ResourceLocation CURSED_LAVA_FLOWING_RL = new ResourceLocation(OriginOfSpirits.MOD_ID, "block/cursed_lava_flow");

	public static final RegistryObject<FlowingFluid> CURSED_LAVA = FLUIDS.register("cursed_lava_still", () -> new ForgeFlowingFluid.Source(TOOSBlocks.CURSED_LAVA_PROPERTIES));
	public static final RegistryObject<FlowingFluid> CURSED_LAVA_FLOWING = FLUIDS.register("cursed_lava_flowing", () -> new ForgeFlowingFluid.Flowing(TOOSBlocks.CURSED_LAVA_PROPERTIES));
	public static final RegistryObject<FlowingFluidBlock> CURSED_LAVA_BLOCK = TOOSBlocks.BLOCKS.register("cursed_lava", () -> new CursedLavaFluid(() -> TOOSBlocks.CURSED_LAVA.get(), AbstractBlock.Properties.create(Material.LAVA, MaterialColor.PURPLE_TERRACOTTA).doesNotBlockMovement().hardnessAndResistance(100f).noDrops().setLightLevel(s -> 5)));
	public static final ForgeFlowingFluid.Properties CURSED_LAVA_PROPERTIES = new ForgeFlowingFluid.Properties(
			() -> CURSED_LAVA.get(), () -> CURSED_LAVA_FLOWING.get(), FluidAttributes.builder(CURSED_LAVA_STILL_RL, CURSED_LAVA_FLOWING_RL)
			.luminosity(5).density(1000).viscosity(1000).temperature(1000).sound(SoundEvents.ITEM_BUCKET_EMPTY_LAVA)).slopeFindDistance(10).levelDecreasePerBlock(1).tickRate(10).explosionResistance(100f)
			.block(() -> TOOSBlocks.CURSED_LAVA_BLOCK.get()).bucket(() -> TOOSItems.CURSED_LAVA_BUCKET.get());

	private static boolean needsPostProcessing(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
		return registerBlock(name, supplier, itemGroup, true);
	}

	public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup, boolean generateItem) {
		return registerBlock(name, supplier, itemGroup, 64, generateItem);
	}

	public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup, int stackSize, boolean generateItem) {
		RegistryObject<B> block = TOOSBlocks.BLOCKS.register(name, supplier);
		if (generateItem)
			BLOCK_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(itemGroup).maxStackSize(stackSize)));
		return block;
	}

	public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup, int stackSize, String description) {
		RegistryObject<B> block = TOOSBlocks.BLOCKS.register(name, supplier);
		BLOCK_ITEMS.register(name, () -> new BasicDescriptionBlockItem(description, block.get(), new Item.Properties().group(itemGroup).maxStackSize(stackSize)));
		return block;
	}
}
