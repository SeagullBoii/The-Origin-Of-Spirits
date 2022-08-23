package net.seagullboi.originofspirits.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.seagullboi.originofspirits.block.ClamBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.block.*;
import net.seagullboi.originofspirits.potion.CursedPotionEffect;
import net.seagullboi.originofspirits.potion.ManaHealingPotionEffect;
import org.lwjgl.system.CallbackI;

import java.util.function.Supplier;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TOOSBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OriginOfSpirits.MOD_ID);
	public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OriginOfSpirits.MOD_ID);

	//Blocks
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
	
	//Sand
	public static final RegistryObject<Block> BLACK_SAND = registerBlock("black_sand", () -> new SandBlock(15265262, AbstractBlock.Properties.create(Material.SAND, MaterialColor.BLACK).sound(SoundType.SAND).hardnessAndResistance(0.5F).setLightLevel(s -> 0).harvestTool(ToolType.SHOVEL)), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> WHITE_SAND = registerBlock("white_sand", () -> new SandBlock(2324344, AbstractBlock.Properties.create(Material.SAND, MaterialColor.SNOW).sound(SoundType.SAND).hardnessAndResistance(0.5F).setLightLevel(s -> 0).harvestTool(ToolType.SHOVEL)), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> PINK_SAND = registerBlock("pink_sand", () -> new PinkSandBlock(2324344, AbstractBlock.Properties.create(Material.SAND, MaterialColor.PINK).sound(SoundType.SAND).hardnessAndResistance(0.5F).setLightLevel(s -> 0).harvestTool(ToolType.SHOVEL)), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CRYSTALLIZED_PINK_SAND = registerBlock("crystalized_pink_sand", () -> new Block(AbstractBlock.Properties.create(Material.SAND, MaterialColor.PINK).sound(SoundType.SAND).hardnessAndResistance(0.5F).harvestTool(ToolType.SHOVEL)), TOOSItemGroup.BLOCK_GROUP);

	//Ores
	public static final RegistryObject<Block> CURSED_STEEL_ORE = registerBlock("cursed_steel_ore", () -> new OreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 100.0f).setLightLevel(s -> 0).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> RAW_CURSED_STEEL_BLOCK = registerBlock("raw_cursed_steel_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.5F, 100.0f).setLightLevel(s -> 0).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CURSED_STEEL_BLOCK = registerBlock("cursed_steel_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.LIGHT_GRAY_TERRACOTTA).sound(SoundType.METAL).hardnessAndResistance(1.5F, 100.0f).setLightLevel(s -> 0).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);


	//MISC
	public static final RegistryObject<Block> DEEP_ALCYONEUM_TOP = registerBlock("deep_alcyonium_top", () -> new Block(AbstractBlock.Properties.create(Material.CORAL, MaterialColor.GREEN_TERRACOTTA).sound(SoundType.CORAL).hardnessAndResistance(0.6F).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE)), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CLAM_BLOCK = registerBlock("clam_block", () -> new ClamBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0f).setLightLevel(s -> 0).harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> CLOUD_BLOCK = registerBlock("cloud_block", () -> new CloudBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS, MaterialColor.SNOW).sound(SoundType.CLOTH).hardnessAndResistance(0.3f).notSolid().doesNotBlockMovement().speedFactor(0.4f).jumpFactor(0.4f).setLightLevel(s -> 0).harvestLevel(1).harvestTool(ToolType.SHOVEL), false), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> RAINY_CLOUD_BLOCK = registerBlock("rainy_cloud_block", () -> new CloudBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS, MaterialColor.LIGHT_BLUE_TERRACOTTA).sound(SoundType.CLOTH).hardnessAndResistance(0.3f).notSolid().doesNotBlockMovement().speedFactor(0.4f).jumpFactor(0.4f).setLightLevel(s -> 0).harvestLevel(1).harvestTool(ToolType.SHOVEL), true), TOOSItemGroup.BLOCK_GROUP);
    public static final RegistryObject<Block> CURSED_CLOUD_BLOCK = registerBlock("cursed_cloud_block", () -> new CursedCloudBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS, MaterialColor.SNOW).sound(SoundType.CLOTH).hardnessAndResistance(0.3f).notSolid().doesNotBlockMovement().speedFactor(0.4f).jumpFactor(0.4f).setLightLevel(s -> 0).harvestLevel(1).harvestTool(ToolType.SHOVEL), false,CursedPotionEffect.potion), TOOSItemGroup.BLOCK_GROUP);
    public static final RegistryObject<Block> TEMPEST_CRYSTAL = registerBlock("tempest_crystal", () -> new GlassBlock(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.LIGHT_BLUE).sound(SoundType.GLASS).hardnessAndResistance(1.0F, 3.0f).notSolid().setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);


	//Advanced Blocks
	public static final RegistryObject<Block> GUNSMITHING_TABLE_BLOCK = registerBlock("gunsmithing_table", () -> new GunsmithingTableBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.LIGHT_GRAY).sound(SoundType.METAL).hardnessAndResistance(3.5F).setLightLevel(s -> 0).harvestTool(ToolType.PICKAXE).setRequiresTool()), TOOSItemGroup.BLOCK_GROUP);
	public static final RegistryObject<Block> ABYSSAL_SPAWNER = BLOCKS.register("abyssal_spawner", () -> new AbyssalSpawnerBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS, MaterialColor.GRAY).sound(SoundType.METAL).hardnessAndResistance(5f, 20f).notSolid().harvestTool(ToolType.PICKAXE)));

	//Plants
    public static final RegistryObject<Block> DUCKWEED = BLOCKS.register("duckweed_plant", () -> new DuckweedBlock(AbstractBlock.Properties.create(Material.PLANTS).zeroHardnessAndResistance().sound(SoundType.LILY_PADS).notSolid().doesNotBlockMovement().setLightLevel(s -> 10)));
	public static final RegistryObject<Block> GLOWKELP = BLOCKS.register("glowkelp", () -> new GlowkelpTopBlock(AbstractBlock.Properties.create(Material.OCEAN_PLANT).zeroHardnessAndResistance().sound(SoundType.WET_GRASS).notSolid().doesNotBlockMovement().setLightLevel(s -> 1)));
	public static final RegistryObject<Block> GLOWKELP_PLANT = BLOCKS.register("glow_kelp_plant", () -> new GlowkelpBlock(AbstractBlock.Properties.create(Material.OCEAN_PLANT).zeroHardnessAndResistance().sound(SoundType.WET_GRASS).notSolid().doesNotBlockMovement().setLightLevel(s -> 1)));
	public static final RegistryObject<Block> ALCYONEUM_POLYPS = registerBlock("alcyonium_polyps", () -> new SeaPolypBlock(AbstractBlock.Properties.create(Material.CORAL, MaterialColor.PURPLE_TERRACOTTA).zeroHardnessAndResistance().sound(SoundType.CORAL).notSolid().doesNotBlockMovement().setLightLevel(s -> 0)), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> DEEP_ALCYONEUM_POLYPS = registerBlock("deep_alcyonium_polyps", () -> new SeaPolypBlock(AbstractBlock.Properties.create(Material.CORAL, MaterialColor.PURPLE_TERRACOTTA).zeroHardnessAndResistance().sound(SoundType.CORAL).notSolid().doesNotBlockMovement().setLightLevel(s -> 0)), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> DEEPSEA_ALGAE = registerBlock("deepsea_algae", () -> new DeepseaAlgaeBlock(AbstractBlock.Properties.create(Material.SEA_GRASS, MaterialColor.GREEN).zeroHardnessAndResistance().sound(SoundType.WET_GRASS).notSolid().doesNotBlockMovement().setLightLevel(s -> 0)), TOOSItemGroup.PLANT_GROUP);
	public static final RegistryObject<Block> MAGIC_MAGNOLIA = registerBlock("magic_magnolia", () -> new FlowerBlock(Effects.ABSORPTION, 6, AbstractBlock.Properties.from(Blocks.CORNFLOWER)), TOOSItemGroup.PLANT_GROUP);

	//Signs
	public static final RegistryObject<Block> SACREDWOOD_SIGN = BLOCKS.register("sacredwood_sign", () -> new ModStandingSignBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).doesNotBlockMovement().hardnessAndResistance(1, 1), ModWoodTypes.SACREDWOOD));
	public static final RegistryObject<Block> SACREDWOOD_WALL_SIGN = BLOCKS.register("sacredwood_wall_sign", () -> new ModWallSignBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).doesNotBlockMovement().hardnessAndResistance(1, 1), ModWoodTypes.SACREDWOOD));
	public static final RegistryObject<Block> SWIRLWOOD_SIGN = BLOCKS.register("swirlwood_sign", () -> new ModStandingSignBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).doesNotBlockMovement().hardnessAndResistance(1, 1), ModWoodTypes.SWIRLWOOD));
	public static final RegistryObject<Block> SWIRLWOOD_WALL_SIGN = BLOCKS.register("swirlwood_wall_sign", () -> new ModWallSignBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).doesNotBlockMovement().hardnessAndResistance(1, 1), ModWoodTypes.SWIRLWOOD));

	//Crops
	public static final RegistryObject<Block> SWEET_POTATOES = BLOCKS.register("sweet_potatoes", () -> new SweetPotatoBlock(AbstractBlock.Properties.from(Blocks.POTATOES)));
	public static final RegistryObject<Block> BARLEY = BLOCKS.register("barley", () -> new BarleyBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));

	//Flower Pots
	public static final RegistryObject<Block> POTTED_MAGIC_MAGNOLIA = BLOCKS.register("potted_magic_magnolia", () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), MAGIC_MAGNOLIA, AbstractBlock.Properties.from(Blocks.POTTED_CORNFLOWER)));

	//Registry
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

	private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
		TOOSItems.ITEMS.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().group(TOOSItemGroup.BLOCK_GROUP)));
	}
}
