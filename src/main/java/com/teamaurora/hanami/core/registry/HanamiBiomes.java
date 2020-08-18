package com.teamaurora.hanami.core.registry;

import com.teamaurora.hanami.common.world.biome.*;
import com.teamaurora.hanami.core.Hanami;
import com.teamaurora.hanami.core.HanamiConfig;
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

    public static final RegistryObject<Biome> SAKURA_FOREST = BIOMES.register("sakura_forest", SakuraForestBiome::new);
    public static final RegistryObject<Biome> SAKURA_FOREST_EDGE = BIOMES.register("sakura_forest_edge", SakuraForestEdgeBiome::new);
    public static final RegistryObject<Biome> SAKURA_PLATEAU = BIOMES.register("sakura_plateau", SakuraPlateauBiome::new);
    public static final RegistryObject<Biome> SAKURA_ROLLING_HILLS = BIOMES.register("sakura_rolling_hills", SakuraRollingHillsBiome::new);
    public static final RegistryObject<Biome> SAKURA_VALLEY = BIOMES.register("sakura_valley", SakuraValleyBiome::new);
    public static final RegistryObject<Biome> SAKURA_MOUNTAINS = BIOMES.register("sakura_mountains", SakuraMountainsBiome::new);

    public static void registerBiomesToDictionary() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SAKURA_FOREST.get(), HanamiConfig.COMMON.sakuraForestWeight.get()));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SAKURA_ROLLING_HILLS.get(), HanamiConfig.COMMON.sakuraRollingHillsWeight.get()));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SAKURA_MOUNTAINS.get(), HanamiConfig.COMMON.sakuraMountainsWeight.get()));
    }

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(SAKURA_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(SAKURA_FOREST_EDGE.get(), BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(SAKURA_PLATEAU.get(), BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(SAKURA_ROLLING_HILLS.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.RARE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(SAKURA_VALLEY.get(), BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.RARE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(SAKURA_MOUNTAINS.get(), BiomeDictionary.Type.RARE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.OVERWORLD);
    }
}
