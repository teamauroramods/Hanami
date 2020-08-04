package com.teamaurora.hanami.core.registry;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamaurora.hanami.common.item.DrinkItem;
import com.teamaurora.hanami.common.item.CherryKombuchaItem;
import com.teamaurora.hanami.common.item.SakuraBlossomItem;
import com.teamaurora.hanami.core.Hanami;
import com.teamaurora.hanami.core.other.HanamiFoods;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiItems {
    public static final RegistryHelper HELPER = Hanami.REGISTRY_HELPER;

    public static final RegistryObject<Item> SAKURA_BOAT = HELPER.createBoatItem("sakura", HanamiBlocks.SAKURA_PLANKS);

    public static final RegistryObject<Item> SAKURA_CHERRIES = HELPER.createItem("sakura_cherries", ()->new Item(new Item.Properties().food(HanamiFoods.CHERRIES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> SAKURA_CHERRY_JUICE = HELPER.createItem("sakura_cherry_juice", ()->new DrinkItem(new Item.Properties().food(HanamiFoods.CHERRY_JUICE).maxStackSize(16).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> SAKURA_CHERRY_KOMBUCHA = HELPER.createItem("sakura_cherry_kombucha", ()->new CherryKombuchaItem(new Item.Properties().food(HanamiFoods.CHERRY_KOMBUCHA).maxStackSize(16).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> SAKURA_CHERRY_PIE = HELPER.createItem("sakura_cherry_pie", ()->new Item(new Item.Properties().food(HanamiFoods.CHERRY_PIE).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BLACK_FOREST_GATEAU = HELPER.createItem("black_forest_gateau", ()->new BlockItem(HanamiBlocks.BLACK_FOREST_GATEAU.get(), new Item.Properties().maxStackSize(1).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> SAKURA_BLOSSOM = HELPER.createItem("sakura_blossom", ()->new SakuraBlossomItem(new Item.Properties().group(ItemGroup.MISC)));
}
