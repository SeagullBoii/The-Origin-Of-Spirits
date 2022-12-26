package net.seagullboi.originofspirits.registry;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginOfSpirits;

public class TOOSParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, OriginOfSpirits.MOD_ID);

    public static final RegistryObject<BasicParticleType> CURSED_FLAME_PARTICLE = PARTICLE_TYPES.register("cursed_flame_particle", () -> new BasicParticleType(false));
}
