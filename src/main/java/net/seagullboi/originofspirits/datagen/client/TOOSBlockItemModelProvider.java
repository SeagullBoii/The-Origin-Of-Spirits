package net.seagullboi.originofspirits.datagen.client;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

public class TOOSBlockItemModelProvider extends BlockModelProvider {
    public TOOSBlockItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, OriginOfSpirits.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        wallItem(TOOSBlocks.POLISHED_DECEPTONE_WALL.get(), new ResourceLocation("originofspirits:block/polished_deceptone"));
    }

    protected BlockModelBuilder wallItem(Block block, ResourceLocation texture) {
        return wallInventory(block.getRegistryName().toString(), texture);
    }

    protected BlockModelBuilder fenceItem(Block block, ResourceLocation texture) {
        return fenceInventory(block.toString(), texture);
    }

}