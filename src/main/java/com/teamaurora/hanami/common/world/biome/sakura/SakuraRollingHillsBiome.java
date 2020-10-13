package com.teamaurora.hanami.common.world.biome.sakura;

import com.teamaurora.hanami.common.world.biome.HanamiBiomeFeatures;
import com.teamaurora.hanami.core.registry.HanamiBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class SakuraRollingHillsBiome extends AbstractSakuraForestBiome {
    public SakuraRollingHillsBiome(Biome.Builder builder) {
        super(builder);
    }

    public void addFeatures() {
        HanamiBiomeFeatures.addSakuraHillFeatures(this);
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
