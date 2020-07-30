package com.teamaurora.hanami.core.registry;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamaurora.hanami.common.entity.SakuraBlossomEntity;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiEntities {
    private static final RegistryHelper HELPER = Hanami.REGISTRY_HELPER;

    public static final RegistryObject<EntityType<SakuraBlossomEntity>> SAKURA_BLOSSOM = HELPER.createLivingEntity("sakura_blossom", SakuraBlossomEntity::new, EntityClassification.MISC, 1F, 0.1F);
}
