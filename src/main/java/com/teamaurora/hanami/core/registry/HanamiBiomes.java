package com.teamaurora.hanami.core.registry;

import com.teamaurora.hanami.common.world.biome.SakuraForestBiome;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Hanami.MODID);

    public static final RegistryObject<Biome> SAKURA_FOREST = BIOMES.register("sakura_forest", ()->new SakuraForestBiome());

    public static void registerBiomesToDictionary() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SAKURA_FOREST.get(), 2));
    }

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(SAKURA_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
    }
}
