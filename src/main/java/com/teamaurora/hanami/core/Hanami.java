package com.teamaurora.hanami.core;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamaurora.hanami.client.particle.HanamiParticles;
import com.teamaurora.hanami.client.render.entity.BlombRenderer;
import com.teamaurora.hanami.client.render.entity.SakuraBlossomRender;
import com.teamaurora.hanami.common.entity.SakuraBlossomEntity;
import com.teamaurora.hanami.core.other.HanamiData;
import com.teamaurora.hanami.core.other.HanamiRender;
import com.teamaurora.hanami.core.registry.HanamiBiomes;
import com.teamaurora.hanami.core.registry.HanamiEffects;
import com.teamaurora.hanami.core.registry.HanamiEntities;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.teamaurora.hanami.core.Hanami.MODID;

@Mod(MODID)
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("deprecation")
public class Hanami
{
    public static final String MODID = "hanami";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

    public Hanami() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRY_HELPER.getDeferredItemRegister().register(eventBus);
        REGISTRY_HELPER.getDeferredBlockRegister().register(eventBus);
        REGISTRY_HELPER.getDeferredEntityRegister().register(eventBus);

        HanamiParticles.PARTICLES.register(eventBus);
        HanamiEntities.ENTITIES.register(eventBus);
        HanamiBiomes.BIOMES.register(eventBus);
        HanamiEffects.EFFECTS.register(eventBus);
        HanamiEffects.POTIONS.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);

        eventBus.addListener(this::commonSetup);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            eventBus.addListener(this::clientSetup);
        });
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            HanamiBiomes.addBiomeTypes();
            HanamiBiomes.registerBiomesToDictionary();
            HanamiData.registerCompostables();
            HanamiData.registerFlammables();
            HanamiData.registerDispenserBehaviors();
            HanamiEffects.registerBrewingRecipes();
            GlobalEntityTypeAttributes.put(HanamiEntities.SAKURA_BLOSSOM.get(), SakuraBlossomEntity.setCustomAttributes().func_233813_a_());
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            HanamiRender.setupRenderLayer();

            RenderingRegistry.registerEntityRenderingHandler(HanamiEntities.SAKURA_BLOSSOM.get(), SakuraBlossomRender::new);
            RenderingRegistry.registerEntityRenderingHandler(HanamiEntities.BLOMB.get(), BlombRenderer::new);
        });
    }


}
