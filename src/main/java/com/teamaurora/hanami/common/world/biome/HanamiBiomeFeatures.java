package com.teamaurora.hanami.common.world.biome;

import com.google.common.collect.ImmutableList;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import com.teamaurora.hanami.core.registry.HanamiFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.fml.ModList;

public class HanamiBiomeFeatures {
    private static final BlockState SAKURA_LOG = HanamiBlocks.SAKURA_LOG.get().getDefaultState();
    private static final BlockState SAKURA_LEAVES = HanamiBlocks.SAKURA_LEAVES.get().getDefaultState();
    private static final BlockState CHERRY_SAKURA_LEAVES = HanamiBlocks.CHERRY_SAKURA_LEAVES.get().getDefaultState();
    private static final BlockState ALLIUM = Blocks.ALLIUM.getDefaultState();
    private static final BlockState PINK_TULIP = Blocks.PINK_TULIP.getDefaultState();
    private static final BlockState WHITE_TULIP = Blocks.WHITE_TULIP.getDefaultState();
    private static final BlockState ORANGE_TULIP = Blocks.ORANGE_TULIP.getDefaultState();
    private static final BlockState AZALEA_BUSH = HanamiBlocks.AZALEA_BUSH.get().getDefaultState();

    public static final BaseTreeFeatureConfig SAKURA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), null, null, null)).func_236700_a_().build();
    public static final BaseTreeFeatureConfig SAKURA_TREE_WITH_CHERRIES_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), (new WeightedBlockStateProvider()).addWeightedBlockstate(SAKURA_LEAVES,99).addWeightedBlockstate(CHERRY_SAKURA_LEAVES,1), null, null, null)).func_236700_a_().build();
    public static final BaseTreeFeatureConfig DENSE_SAKURA_TREE_WITH_CHERRIES_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), (new WeightedBlockStateProvider()).addWeightedBlockstate(SAKURA_LEAVES,8).addWeightedBlockstate(CHERRY_SAKURA_LEAVES,2), null, null, null)).func_236700_a_().build();


    public static final BlockClusterFeatureConfig TULIP_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).addWeightedBlockstate(PINK_TULIP, 2).addWeightedBlockstate(WHITE_TULIP, 2).addWeightedBlockstate(ORANGE_TULIP, 1), SimpleBlockPlacer.field_236447_c_)).tries(64).build();
    public static final BlockClusterFeatureConfig AZALEA_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AZALEA_BUSH), SimpleBlockPlacer.field_236447_c_)).tries(64).build();
    public static final BlockClusterFeatureConfig ALLIUM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ALLIUM), SimpleBlockPlacer.field_236447_c_)).tries(64).build();

    public static void addKoiPonds(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.LAKES, HanamiFeatures.KOI_POND.get().withConfiguration(NoFeatureConfig.field_236559_b_).withPlacement(Placement.CHANCE_TOP_SOLID_HEIGHTMAP.configure(new ChanceConfig(6))));
    }

    public static void addSakuraFeatures(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.SHORT_BAMBOO.get().withConfiguration(new ProbabilityConfig(0.0F)).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.SAKURA_TREE_WITH_FALLEN_LEAVES.get().withConfiguration(ModList.get().isLoaded("fruitful") ? SAKURA_TREE_WITH_CHERRIES_CONFIG : SAKURA_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(6, 0.1F, 1))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.FALLEN_SAKURA_LEAVES.get().withConfiguration(NoFeatureConfig.field_236559_b_).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(32))));
    }

    public static void addSakuraHillFeatures(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.SHORT_BAMBOO.get().withConfiguration(new ProbabilityConfig(0.0F)).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(30))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.SAKURA_TREE_WITH_FALLEN_LEAVES.get().withConfiguration(ModList.get().isLoaded("fruitful") ? SAKURA_TREE_WITH_CHERRIES_CONFIG : SAKURA_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(4, 0.1F, 1))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.FALLEN_SAKURA_LEAVES.get().withConfiguration(NoFeatureConfig.field_236559_b_).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(24))));
    }

    public static void addSparseSakuraFeatures(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.SHORT_BAMBOO.get().withConfiguration(new ProbabilityConfig(0.0F)).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(25))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.SAKURA_TREE_WITH_FALLEN_LEAVES.get().withConfiguration(ModList.get().isLoaded("fruitful") ? SAKURA_TREE_WITH_CHERRIES_CONFIG : SAKURA_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.FALLEN_SAKURA_LEAVES.get().withConfiguration(NoFeatureConfig.field_236559_b_).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(16))));
    }

    public static void addSakuraWaterFoliage(Biome biome) {
        // copied from Atmospheric's addRainforestWaterFoliage, credit to bageldotjpg for the parameters here
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LILY_PAD_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new SeaGrassConfig(64, 0.6D)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }

    public static void addSakuraFlowers(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_RANDOM_SELECTOR.withConfiguration(
                new MultipleWithChanceRandomFeatureConfig(ImmutableList.of(
                        Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LILAC_CONFIG),
                        Feature.RANDOM_PATCH.withConfiguration(AZALEA_CONFIG),
                        Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.PEONY_CONFIG)), 0))
                .withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(2))));
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_RANDOM_SELECTOR.withConfiguration(
                new MultipleWithChanceRandomFeatureConfig(ImmutableList.of(
                        Feature.RANDOM_PATCH.withConfiguration(TULIP_CONFIG),
                        Feature.RANDOM_PATCH.withConfiguration(ALLIUM_CONFIG),
                        Feature.FLOWER.withConfiguration(DefaultBiomeFeatures.LILY_OF_THE_VALLEY_CONFIG)), 0))
                .withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(2))));
        }
}
