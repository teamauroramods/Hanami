package com.teamaurora.hanami.common.world.biome.sakura;

import com.teamaurora.hanami.common.world.biome.HanamiBiomeFeatures;
import com.teamaurora.hanami.core.registry.HanamiBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class SakuraForestBiome extends AbstractSakuraForestBiome {
    public SakuraForestBiome(Biome.Builder builder) {
        super(builder);
    }

    public void addFeatures() {
        HanamiBiomeFeatures.addSakuraFeatures(this);
    }

    @Override
    public Biome getHill(net.minecraft.world.gen.INoiseRandom rand) {
        return HanamiBiomes.SAKURA_PLATEAU.get();
    }

    @Override
    public Biome getEdge(INoiseRandom rand, Biome northBiome, Biome westBiome, Biome southBiome, Biome eastBiome) {
        if (northBiome == this && westBiome == this && southBiome == this && eastBiome == this) {
            return null;
        } else {
            return HanamiBiomes.SAKURA_FOREST_EDGE.get();
        }
    }
}
