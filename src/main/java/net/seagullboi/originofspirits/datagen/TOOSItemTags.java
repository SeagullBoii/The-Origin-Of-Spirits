package net.seagullboi.originofspirits.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.block.SacredwoodPlanksBlock;
import net.seagullboi.originofspirits.block.SwirlwoodPlanksBlock;

import javax.annotation.Nullable;

public class TOOSItemTags extends ItemTagsProvider {

    public TOOSItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, OriginOfSpirits.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        this.getOrCreateBuilder(ItemTags.PLANKS)
                .add(SwirlwoodPlanksBlock.block.asItem())
                .add(SacredwoodPlanksBlock.block.asItem());
    }

    @Override
    public String getName() {
        return "TOOS Item Tags";
    }
}
