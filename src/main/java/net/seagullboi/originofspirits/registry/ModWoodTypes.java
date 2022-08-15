/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside net.mcreator.originofspirits as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package net.seagullboi.originofspirits.registry;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.minecraft.block.WoodType;
import net.minecraft.util.ResourceLocation;

public class ModWoodTypes {
	public static final WoodType SACREDWOOD =
			WoodType.create(new ResourceLocation(OriginOfSpirits.MOD_ID, "sacredwood").toString());
	public static final WoodType SWIRLWOOD =
			WoodType.create(new ResourceLocation(OriginOfSpirits.MOD_ID, "swirlwood").toString());
}
