package com.teamaurora.hanami.client.particle;

import com.teamaurora.hanami.core.Hanami;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

// credit to Team Abnormals for a lot of this code. particles are very jank lmao
public class HanamiParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Hanami.MODID);

    public static final RegistryObject<BasicParticleType> BLOSSOM_PETAL = createBasicParticleType(true, "blossom_petal");

    private static RegistryObject<BasicParticleType> createBasicParticleType(boolean alwaysShow, String name) {
        RegistryObject<BasicParticleType> particleType = PARTICLES.register(name, ()->new BasicParticleType(alwaysShow));
        return particleType;
    }

    @Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegisterParticleFactories {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
            if (checkForNonNullWithReflectionCauseForgeIsBaby(BLOSSOM_PETAL)) {
                Minecraft.getInstance().particles.registerFactory(BLOSSOM_PETAL.get(), BlossomPetalParticle.Factory::new);
            }
        }
    }

    private static boolean checkForNonNullWithReflectionCauseForgeIsBaby(RegistryObject<BasicParticleType> registryObject) {
        return ObfuscationReflectionHelper.getPrivateValue(RegistryObject.class, registryObject, "value") != null;
    }
}
