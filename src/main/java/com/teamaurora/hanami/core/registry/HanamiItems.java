package com.teamaurora.hanami.core.registry;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiItems {
    public static final RegistryHelper HELPER = Hanami.REGISTRY_HELPER;

    public static final RegistryObject<Item> SAKURA_BOAT = HELPER.createBoatItem("sakura", HanamiBlocks.SAKURA_PLANKS);
}
