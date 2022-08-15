package net.seagullboi.originofspirits.datagen.client;


import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

public class TOOSBlockStateProvider extends BlockStateProvider {

    public TOOSBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, OriginOfSpirits.MOD_ID, exFileHelper);
    }

    public ResourceLocation blockFolder(Block block) {
        ResourceLocation name = block.getRegistryName();
        return new ResourceLocation(name.getNamespace(), "blocks" + "/" + name.getPath());
    }

    @Override
    protected void registerStatesAndModels() {
        stairsBlock((StairsBlock) TOOSBlocks.POLISHED_DECEPTONE_STAIRS.get(), blockTexture(TOOSBlocks.POLISHED_DECEPTONE.get()));
        slabBlock((SlabBlock) TOOSBlocks.POLISHED_DECEPTONE_SLAB.get(), blockTexture(TOOSBlocks.POLISHED_DECEPTONE.get()), blockTexture(TOOSBlocks.POLISHED_DECEPTONE.get()));
        wallBlock((WallBlock) TOOSBlocks.POLISHED_DECEPTONE_WALL.get(), blockTexture(TOOSBlocks.POLISHED_DECEPTONE.get()));
    }


}
