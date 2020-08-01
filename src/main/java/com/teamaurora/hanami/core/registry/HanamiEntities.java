package com.teamaurora.hanami.core.registry;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamaurora.hanami.common.entity.block.BlombEntity;
import com.teamaurora.hanami.common.entity.SakuraBlossomEntity;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiEntities {
    private static final RegistryHelper HELPER = Hanami.REGISTRY_HELPER;
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Hanami.MODID);

    public static final RegistryObject<EntityType<SakuraBlossomEntity>> SAKURA_BLOSSOM = HELPER.createLivingEntity("sakura_blossom", SakuraBlossomEntity::new, EntityClassification.MISC, 1F, 0.1F);
    public static final RegistryObject<EntityType<BlombEntity>> BLOMB = ENTITIES.register("blomb", ()->EntityType.Builder.<BlombEntity>create(BlombEntity::new, EntityClassification.MISC).immuneToFire().size(0.98F, 0.98F).build("hanami:blomb"));
}
