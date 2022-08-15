package net.seagullboi.originofspirits.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.seagullboi.originofspirits.OriginOfSpirits;

public class TOOSTags {

    public static final class Entities {

        public static final ITag.INamedTag<EntityType<?>> PINK_DESERT_ENTITIES = forgeLoc("glass_desert_mobs");

        private static ITag.INamedTag<EntityType<?>> toosLoc(String path) {
            return EntityTypeTags.getTagById(new ResourceLocation(OriginOfSpirits.MOD_ID, path).toString());
        }

        private static ITag.INamedTag<EntityType<?>> mcLoc(String path) {
            return EntityTypeTags.getTagById(new ResourceLocation("minecraft", path).toString());
        }

        private static ITag.INamedTag<EntityType<?>> forgeLoc(String path) {
            return EntityTypeTags.getTagById(new ResourceLocation("forge", path).toString());
        }
    }

}
