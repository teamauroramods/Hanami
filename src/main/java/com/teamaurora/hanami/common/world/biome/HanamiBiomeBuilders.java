package com.teamaurora.hanami.common.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class HanamiBiomeBuilders {

    public static final Biome.Builder SAKURA_FOREST        = createSakuraBiome(0.125F, 0.09F);
    public static final Biome.Builder SAKURA_FOREST_EDGE   = createSakuraBiome(0.125F, 0.075F);
    public static final Biome.Builder SAKURA_MOUNTAINS     = createSakuraBiome(1.0F, 0.25F);
    public static final Biome.Builder SAKURA_PLATEAU       = createSakuraBiome(1.0F, 0.025F);
    public static final Biome.Builder SAKURA_ROLLING_HILLS = createSakuraBiome(0.175F, 0.04F);
    public static final Biome.Builder SAKURA_VALLEY        = createSakuraBiome(0.125F, 0.03F);

    private static Biome.Builder createSakuraBiome(float depth, float scale) {
        return (new Biome.Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
                .precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST)
                .depth(depth)
                .scale(scale)
                .temperature(0.6F)
                .downfall(0.7F)
                .func_235097_a_((new BiomeAmbience.Builder())
                        .func_235246_b_(6051302)
                        .func_235248_c_(1184056)
                        .func_235239_a_(12638463)
                        .func_235243_a_(MoodSoundAmbience.field_235027_b_)
                        .func_235238_a_())
                .parent(null));
    }
}
