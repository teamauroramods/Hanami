package com.teamaurora.hanami.common.world.biome;

import com.teamaurora.hanami.core.registry.HanamiBiomes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SakuraValleyEdgeBiome extends Biome {
    public SakuraValleyEdgeBiome() {
        super(new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
                .precipitation(RainType.RAIN).category(Category.FOREST)
                .depth(0.45F)
                .scale(0.3F)
                .temperature(0.6F)
                .downfall(0.7F)
                .func_235097_a_((new BiomeAmbience.Builder())
                        .func_235246_b_(6051302)
                        .func_235248_c_(1184056)
                        .func_235239_a_(12638463)
                        .func_235243_a_(MoodSoundAmbience.field_235027_b_)
                        .func_235238_a_())
                .parent((String)null));

        DefaultBiomeFeatures.func_235196_b_(this);
        this.func_235063_a_(DefaultBiomeFeatures.field_235187_y_);
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addMonsterRooms(this);

        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);

        HanamiBiomeFeatures.addKoiPonds(this);
        DefaultBiomeFeatures.addSprings(this);

        HanamiBiomeFeatures.addSakuraFeatures(this);

        HanamiBiomeFeatures.addSakuraFlowers(this);
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addGrass(this);
        HanamiBiomeFeatures.addSakuraWaterFoliage(this);

        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 10, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COW, 8, 4, 4));
        addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));

        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
    }

    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(double posX, double posZ)
    {
        return 0x74BA59;
    }

    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor()
    {
        return 0x7ab85c;
    }

    @OnlyIn(Dist.CLIENT)
    public int getSkyColor() {
        return 15061743;
    }

    @Override
    public Biome getHill(net.minecraft.world.gen.INoiseRandom rand) {
        return HanamiBiomes.SAKURA_VALLEY.get();
    }
}
