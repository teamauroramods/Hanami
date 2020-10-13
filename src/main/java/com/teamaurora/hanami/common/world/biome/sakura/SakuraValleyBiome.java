package com.teamaurora.hanami.common.world.biome.sakura;

import com.teamaurora.hanami.common.world.biome.HanamiBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class SakuraValleyBiome extends AbstractSakuraForestBiome {
    public SakuraValleyBiome(Biome.Builder builder) {
        super(builder);
    }

    public void addFeatures() {
        HanamiBiomeFeatures.addSparseSakuraFeatures(this);
    }
}
