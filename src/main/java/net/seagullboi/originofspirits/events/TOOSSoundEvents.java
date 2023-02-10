package net.seagullboi.originofspirits.events;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginOfSpirits;

public class TOOSSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, OriginOfSpirits.MOD_ID);

    //Entities
    public static final RegistryObject<SoundEvent> HEXAYOSH_HURT = registerSoundEvent("hexayosh_hurt");
    public static final RegistryObject<SoundEvent> HEXAYOSH_DEATH = registerSoundEvent("hexayosh_death");
    public static final RegistryObject<SoundEvent> HEXAYOSH_AMBIENT = registerSoundEvent("hexayosh_ambient");
    public static final RegistryObject<SoundEvent> HEXAYOSH_CHARGE = registerSoundEvent("hexayosh_charge");

    public static final RegistryObject<SoundEvent> MANATEE_HURT = registerSoundEvent("manatee_hurt");
    public static final RegistryObject<SoundEvent> MANATEE_DEATH = registerSoundEvent("manatee_death");
    public static final RegistryObject<SoundEvent> MANATEE_AMBIENT = registerSoundEvent("manatee_ambient");

    //Weapons
    public static final RegistryObject<SoundEvent> MAGIC_STAFF_USE = registerSoundEvent("magic_staff_use");
    public static final RegistryObject<SoundEvent> GUN = registerSoundEvent("gunshot");
    public static final RegistryObject<SoundEvent> SHOTGUN = registerSoundEvent("shotgun_shot");

    //Music
    public static final RegistryObject<SoundEvent> CURSED_ISLANDS_MUSIC = registerSoundEvent("cursed_islands_music");
    public static final RegistryObject<SoundEvent> PINK_DESERT = registerSoundEvent("pink_desert_music");
    public static final RegistryObject<SoundEvent> VOID = registerSoundEvent("archie_the_abyss");

    //Elements
    public static final RegistryObject<SoundEvent> ELECTRICAL_SHOCK = registerSoundEvent("electrical_shock");
    public static final RegistryObject<SoundEvent> ELECTRICAL_SHOCK_EXPLOSION = registerSoundEvent("electric_shock_explosion");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(OriginOfSpirits.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
