package net.seagullboi.originofspirits.datagen;

import com.google.common.collect.ImmutableSet;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.functions.*;
import net.minecraft.state.Property;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.RegistryObject;
import net.seagullboi.originofspirits.block.BarleyBlock;
import net.seagullboi.originofspirits.block.SweetPotatoBlock;
import net.seagullboi.originofspirits.registry.TOOSBlocks;
import net.seagullboi.originofspirits.registry.TOOSItems;

import java.util.Set;
import java.util.stream.Stream;

public class TOOSBlockLootTables extends BlockLootTables {

    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.inverted();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
    private static final Set<Item> IMMUNE_TO_EXPLOSIONS = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(IItemProvider::asItem).collect(ImmutableSet.toImmutableSet());
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};
    private static final ILootCondition.IBuilder SWEET_POTATOES_COND = BlockStateProperty.builder(TOOSBlocks.SWEET_POTATOES.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(SweetPotatoBlock.AGE, 7));
    private static final ILootCondition.IBuilder BARLEY_COND = BlockStateProperty.builder(TOOSBlocks.BARLEY.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(BarleyBlock.AGE, 7));

    protected static LootTable.Builder dropping(IItemProvider item) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(item, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(item))));
    }

    protected static LootTable.Builder dropping(Block block, ILootCondition.IBuilder conditionBuilder, LootEntry.Builder<?> p_218494_2_) {
        return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block).acceptCondition(conditionBuilder).alternatively(p_218494_2_)));
    }

    protected static LootTable.Builder droppingWithSilkTouch(Block block, LootEntry.Builder<?> builder) {
        return dropping(block, SILK_TOUCH, builder);
    }

    protected static LootTable.Builder droppingWithShears(Block block, LootEntry.Builder<?> noShearAlternativeEntry) {
        return dropping(block, SHEARS, noShearAlternativeEntry);
    }

    protected static LootTable.Builder droppingWithSilkTouchOrShears(Block block, LootEntry.Builder<?> alternativeLootEntry) {
        return dropping(block, SILK_TOUCH_OR_SHEARS, alternativeLootEntry);
    }

    protected static LootTable.Builder droppingWithSilkTouch(Block block, IItemProvider noSilkTouch) {
        return droppingWithSilkTouch(block, withSurvivesExplosion(block, ItemLootEntry.builder(noSilkTouch)));
    }

    protected static LootTable.Builder droppingRandomly(IItemProvider item, IRandomRange range) {
        return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(withExplosionDecay(item, ItemLootEntry.builder(item).acceptFunction(SetCount.builder(range)))));
    }

    protected static LootTable.Builder droppingWithSilkTouchOrRandomly(Block block, IItemProvider item, IRandomRange range) {
        return droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(item).acceptFunction(SetCount.builder(range))));
    }

    protected static LootTable.Builder onlyWithSilkTouch(IItemProvider item) {
        return LootTable.builder().addLootPool(LootPool.builder().acceptCondition(SILK_TOUCH).rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(item)));
    }

    protected static LootTable.Builder droppingAndFlowerPot(IItemProvider flower) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(Blocks.FLOWER_POT, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Blocks.FLOWER_POT)))).addLootPool(withSurvivesExplosion(flower, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(flower))));
    }

    protected static LootTable.Builder droppingSlab(Block slab) {
        return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(withExplosionDecay(slab, ItemLootEntry.builder(slab).acceptFunction(SetCount.builder(ConstantRange.of(2)).acceptCondition(BlockStateProperty.builder(slab).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(SlabBlock.TYPE, SlabType.DOUBLE)))))));
    }

    protected static <T extends Comparable<T> & IStringSerializable> LootTable.Builder droppingWhen(Block block, Property<T> property, T value) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(block, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block).acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(property, value))))));
    }

    protected static LootTable.Builder droppingWithName(Block block) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(block, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)))));
    }

    protected static LootTable.Builder droppingWithContents(Block shulker) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(shulker, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(shulker).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)).acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY).replaceOperation("Lock", "BlockEntityTag.Lock").replaceOperation("LootTable", "BlockEntityTag.LootTable").replaceOperation("LootTableSeed", "BlockEntityTag.LootTableSeed")).acceptFunction(SetContents.builderIn().addLootEntry(DynamicLootEntry.func_216162_a(ShulkerBoxBlock.CONTENTS))))));
    }

    protected static LootTable.Builder droppingItemWithFortune(Block block, Item item) {
        return droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(item).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))));
    }

    public void registerFlowerPot(Block flowerPot) {
        this.registerLootTable(flowerPot, (pot) -> droppingAndFlowerPot(((FlowerPotBlock)pot).getFlower()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return TOOSBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    @Override
    protected void addTables() {
        //Stone

        //Abyssal Stone
        this.registerDropSelfLootTable(TOOSBlocks.ABYSSAL_STONE.get());

        //Blessed Stone
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE.get());
        this.registerLootTable(TOOSBlocks.BLESSED_STONE_SLAB.get(), TOOSBlockLootTables::droppingSlab);
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE_STAIRS.get());
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE_WALL.get());
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE_BRICKS.get());
        this.registerLootTable(TOOSBlocks.BLESSED_STONE_BRICKS_SLAB.get(), TOOSBlockLootTables::droppingSlab);
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE_BRICKS_STAIRS.get());
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE_BRICKS_WALL.get());
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE_BRICKS_SMALL.get());
        this.registerLootTable(TOOSBlocks.BLESSED_STONE_BRICKS_SMALL_SLAB.get(), TOOSBlockLootTables::droppingSlab);
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE_BRICKS_SMALL_STAIRS.get());
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE_BRICKS_SMALL_WALL.get());
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE_TILES.get());
        this.registerLootTable(TOOSBlocks.BLESSED_STONE_TILES_SLAB.get(), TOOSBlockLootTables::droppingSlab);
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE_TILES_STAIRS.get());
        this.registerDropSelfLootTable(TOOSBlocks.BLESSED_STONE_TILES_WALL.get());
        this.registerDropSelfLootTable(TOOSBlocks.CRACKED_BLESSED_STONE_BRICKS.get());
        this.registerDropSelfLootTable(TOOSBlocks.CHISELED_BLESSED_STONE.get());
        this.registerDropSelfLootTable(TOOSBlocks.CUT_BLESSED_STONE.get());
        this.registerDropSelfLootTable(TOOSBlocks.POLISHED_BLESSED_STONE.get());
        this.registerLootTable(TOOSBlocks.POLISHED_BLESSED_STONE_SLAB.get(), TOOSBlockLootTables::droppingSlab);
        this.registerDropSelfLootTable(TOOSBlocks.POLISHED_BLESSED_STONE_STAIRS.get());
        this.registerDropSelfLootTable(TOOSBlocks.POLISHED_BLESSED_STONE_WALL.get());

        //Deceptone
        this.registerDropSelfLootTable(TOOSBlocks.DECEPTONE.get());
        this.registerLootTable(TOOSBlocks.DECEPTONE_SLAB.get(), TOOSBlockLootTables::droppingSlab);
        this.registerDropSelfLootTable(TOOSBlocks.DECEPTONE_STAIRS.get());
        this.registerDropSelfLootTable(TOOSBlocks.DECEPTONE_WALL.get());
        this.registerDropSelfLootTable(TOOSBlocks.DECEPTONE_BRICKS.get());
        this.registerLootTable(TOOSBlocks.DECEPTONE_BRICKS_SLAB.get(), TOOSBlockLootTables::droppingSlab);
        this.registerDropSelfLootTable(TOOSBlocks.DECEPTONE_BRICKS_STAIRS.get());
        this.registerDropSelfLootTable(TOOSBlocks.DECEPTONE_BRICKS_WALL.get());
        this.registerDropSelfLootTable(TOOSBlocks.DECEPTONE_BRICKS_SMALL.get());
        this.registerLootTable(TOOSBlocks.DECEPTONE_BRICKS_SMALL_SLAB.get(), TOOSBlockLootTables::droppingSlab);
        this.registerDropSelfLootTable(TOOSBlocks.DECEPTONE_BRICKS_SMALL_STAIRS.get());
        this.registerDropSelfLootTable(TOOSBlocks.DECEPTONE_BRICKS_SMALL_WALL.get());
        this.registerDropSelfLootTable(TOOSBlocks.CRACKED_DECEPTONE_BRICKS.get());
        this.registerDropSelfLootTable(TOOSBlocks.CHISELED_DECEPTONE.get());
        this.registerDropSelfLootTable(TOOSBlocks.CUT_DECEPTONE.get());
        this.registerDropSelfLootTable(TOOSBlocks.POLISHED_DECEPTONE.get());
        this.registerLootTable(TOOSBlocks.POLISHED_DECEPTONE_SLAB.get(), TOOSBlockLootTables::droppingSlab);
        this.registerDropSelfLootTable(TOOSBlocks.POLISHED_DECEPTONE_STAIRS.get());
        this.registerDropSelfLootTable(TOOSBlocks.POLISHED_DECEPTONE_WALL.get());

        //Sand
        this.registerDropSelfLootTable(TOOSBlocks.BLACK_SAND.get());
        this.registerDropSelfLootTable(TOOSBlocks.WHITE_SAND.get());
        this.registerDropSelfLootTable(TOOSBlocks.PINK_SAND.get());
        this.registerLootTable(TOOSBlocks.CRYSTALLIZED_PINK_SAND.get(), LootTable.builder()
                .addLootPool(LootPool.builder().acceptCondition(SILK_TOUCH).addEntry(ItemLootEntry.builder(TOOSBlocks.CRYSTALLIZED_PINK_SAND.get())))
                .addLootPool(LootPool.builder().acceptCondition(NO_SILK_TOUCH).addEntry(ItemLootEntry.builder(TOOSBlocks.PINK_SAND.get())))
                .addLootPool(LootPool.builder().acceptCondition(NO_SILK_TOUCH).addEntry(ItemLootEntry.builder(TOOSItems.CRYSTALIZED_GLASS_SHARD.get()).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 5.0F))))));

        //Ores
        this.registerLootTable(TOOSBlocks.CURSED_STEEL_ORE.get(), (raw_ore) -> droppingItemWithFortune(raw_ore, TOOSItems.RAW_CURSED_STEEL.get()));
        this.registerDropSelfLootTable(TOOSBlocks.RAW_CURSED_STEEL_BLOCK.get());
        this.registerDropSelfLootTable(TOOSBlocks.CURSED_STEEL_BLOCK.get());

        //Clouds
        this.registerDropSelfLootTable(TOOSBlocks.CLOUD_BLOCK.get());
        this.registerDropSelfLootTable(TOOSBlocks.RAINY_CLOUD_BLOCK.get());
        this.registerDropSelfLootTable(TOOSBlocks.CURSED_CLOUD_BLOCK.get());

        //Plants
        this.registerDropSelfLootTable(TOOSBlocks.ALCYONEUM_POLYPS.get());
        this.registerDropSelfLootTable(TOOSBlocks.DEEP_ALCYONEUM_POLYPS.get());
        this.registerLootTable(TOOSBlocks.DUCKWEED.get(), (duckweed) -> droppingWithSilkTouchOrShears(duckweed, withSurvivesExplosion(duckweed, ItemLootEntry.builder(TOOSBlocks.DUCKWEED.get()))));
        this.registerLootTable(TOOSBlocks.DEEPSEA_ALGAE.get(), (deepsea_algae) -> droppingWithSilkTouchOrShears(deepsea_algae, withSurvivesExplosion(deepsea_algae, ItemLootEntry.builder(TOOSBlocks.DEEPSEA_ALGAE.get()))));
        this.registerDropping(TOOSBlocks.GLOWKELP_PLANT.get(),TOOSBlocks.GLOWKELP.get());
        this.registerDropSelfLootTable(TOOSBlocks.GLOWKELP.get());
        this.registerDropSelfLootTable(TOOSBlocks.MAGIC_MAGNOLIA.get());

        //Flower Pots
        this.registerFlowerPot(TOOSBlocks.POTTED_MAGIC_MAGNOLIA.get());

        //Crops
        this.registerLootTable(TOOSBlocks.SWEET_POTATOES.get(), withExplosionDecay(TOOSBlocks.SWEET_POTATOES.get(), LootTable.builder()
                .addLootPool(LootPool.builder().acceptCondition(SWEET_POTATOES_COND).addEntry(ItemLootEntry.builder(TOOSItems.SWEET_POTATO.get()).acceptFunction(ApplyBonus.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286F, 3))))
                .addLootPool(LootPool.builder().addEntry(ItemLootEntry.builder(TOOSItems.SWEET_POTATO.get())))));
        this.registerLootTable(TOOSBlocks.BARLEY.get(), withExplosionDecay(TOOSBlocks.BARLEY.get(), LootTable.builder()
                .addLootPool(LootPool.builder().acceptCondition(BARLEY_COND).addEntry(ItemLootEntry.builder(TOOSItems.BARLEY_SEEDS.get()).acceptFunction(ApplyBonus.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286F, 3))))
                .addLootPool(LootPool.builder().addEntry(ItemLootEntry.builder(TOOSItems.BARLEY_SEEDS.get())))
                .addLootPool(LootPool.builder().acceptCondition(BARLEY_COND).addEntry(ItemLootEntry.builder(TOOSItems.BARLEY_STACK.get())))));



        //WOOD

        //SIGNS
        this.registerDropSelfLootTable(TOOSBlocks.SACREDWOOD_SIGN.get());
        this.registerDropSelfLootTable(TOOSBlocks.SACREDWOOD_WALL_SIGN.get());
        this.registerDropSelfLootTable(TOOSBlocks.SWIRLWOOD_SIGN.get());
        this.registerDropSelfLootTable(TOOSBlocks.SWIRLWOOD_WALL_SIGN.get());

        //MISC
        this.registerDropSelfLootTable(TOOSBlocks.DEEP_ALCYONEUM_TOP.get());
        this.registerDropSelfLootTable(TOOSBlocks.CLAM_BLOCK.get());
        this.registerDropSelfLootTable(TOOSBlocks.TEMPEST_CRYSTAL.get());
        this.registerDropSelfLootTable(TOOSBlocks.GUNSMITHING_TABLE_BLOCK.get());
        this.registerLootTable(TOOSBlocks.ABYSSAL_SPAWNER.get(), blockNoDrop());

    }
}
