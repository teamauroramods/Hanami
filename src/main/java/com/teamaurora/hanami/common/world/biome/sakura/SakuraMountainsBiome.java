package com.teamaurora.hanami.common.world.biome.sakura;

import com.teamaurora.hanami.common.world.biome.HanamiBiomeFeatures;
import com.teamaurora.hanami.core.registry.HanamiBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class SakuraMountainsBiome extends AbstractSakuraForestBiome {
    public SakuraMountainsBiome(Biome.Builder builder) {
        super(builder);
    }

    public void addFeatures() {
        super.addFeatures();
        HanamiBiomeFeatures.addSakuraFeatures(this);
    }

    @Override
    public Biome getHill(net.minecraft.world.gen.INoiseRandom rand) {
        return HanamiBiomes.SAKURA_VALLEY.get();
    }
}
