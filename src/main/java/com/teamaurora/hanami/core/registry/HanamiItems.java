package com.teamaurora.hanami.core.registry;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiItems {
    public static final RegistryHelper HELPER = Hanami.REGISTRY_HELPER;

    public static final RegistryObject<Item> SAKURA_BOAT = HELPER.createBoatItem("sakura", HanamiBlocks.SAKURA_PLANKS);

    public static final RegistryObject<Item> CHERRIES = HELPER.createItem("cherries", ()->new Item(new Item.Properties().food(HanamiFoods.CHERRIES).group(ItemGroup.FOOD)));
    //public static final
}
