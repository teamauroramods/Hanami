package com.teamaurora.hanami.core.registry;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamaurora.hanami.common.item.DrinkItem;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiItems {
    public static final RegistryHelper HELPER = Hanami.REGISTRY_HELPER;

    public static final RegistryObject<Item> SAKURA_BOAT = HELPER.createBoatItem("sakura", HanamiBlocks.SAKURA_PLANKS);

    public static final RegistryObject<Item> CHERRIES = HELPER.createItem("cherries", ()->new Item(new Item.Properties().food(HanamiFoods.CHERRIES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> CHERRY_JUICE = HELPER.createItem("cherry_juice", ()->new DrinkItem(new Item.Properties().food(HanamiFoods.CHERRY_JUICE).maxStackSize(16).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> CHERRY_KOMBUCHA = HELPER.createItem("cherry_kombucha", ()->new DrinkItem(new Item.Properties().food(HanamiFoods.CHERRY_KOMBUCHA).maxStackSize(16).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> CHERRY_PIE = HELPER.createItem("cherry_pie", ()->new Item(new Item.Properties().food(HanamiFoods.CHERRY_PIE).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BLACK_FOREST_GATEAU = HELPER.createItem("black_forest_gateau", ()->new BlockItem(HanamiBlocks.BLACK_FOREST_GATEAU.get(), new Item.Properties().maxStackSize(1).group(ItemGroup.FOOD)));
}
