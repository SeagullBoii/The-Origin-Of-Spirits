package net.seagullboi.originofspirits.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

public class TOOSBlockTagsProvider extends BlockTagsProvider {

    public TOOSBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, OriginOfSpirits.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        this.getOrCreateBuilder(BlockTags.WALLS)
                .add(TOOSBlocks.BLESSED_STONE_WALL.get())
                .add(TOOSBlocks.BLESSED_STONE_BRICKS_WALL.get())
                .add(TOOSBlocks.BLESSED_STONE_BRICKS_SMALL_WALL.get())
                .add(TOOSBlocks.BLESSED_STONE_TILES_WALL.get())
                .add(TOOSBlocks.POLISHED_BLESSED_STONE_WALL.get())
                .add(TOOSBlocks.DECEPTONE_WALL.get())
                .add(TOOSBlocks.DECEPTONE_BRICKS_WALL.get())
                .add(TOOSBlocks.DECEPTONE_BRICKS_SMALL_WALL.get())
                .add(TOOSBlocks.POLISHED_DECEPTONE_WALL.get());
    }
}
