package net.seagullboi.originofspirits.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.registry.TOOSBlocks;
import net.seagullboi.originofspirits.registry.TOOSItems;

public class TOOSLangProvider extends LanguageProvider {
    public TOOSLangProvider(DataGenerator gen, String locale) {
        super(gen, OriginOfSpirits.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        addItem(TOOSItems.IRON_BULLET, "Iron Bullet");
        addItem(TOOSItems.SHOTGUN_SHELLS, "Shotgun Shells");
        addItem(TOOSItems.GOLD_GUN_UPGRADE_TOKEN, "Gold Weapon Upgrade Token");
        addItem(TOOSItems.PISTOL, "Pistol");

        addBlock(TOOSBlocks.POLISHED_DECEPTONE_SLAB, "Polished Deceptone Slab");
        addBlock(TOOSBlocks.POLISHED_DECEPTONE_STAIRS, "Polished Deceptone Stairs");
        addBlock(TOOSBlocks.POLISHED_DECEPTONE_WALL, "Polished Deceptone Wall");
    }
}
