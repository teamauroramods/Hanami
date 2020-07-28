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
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class HanamiBiomeFeatures {
    private static final BlockState SAKURA_LOG = HanamiBlocks.SAKURA_LOG.get().getDefaultState();
    private static final BlockState SAKURA_LEAVES = HanamiBlocks.SAKURA_LEAVES.get().getDefaultState();
    private static final BlockState BAMBOO = Blocks.BAMBOO.getDefaultState();
    private static final BlockState ALLIUM = Blocks.ALLIUM.getDefaultState();
    private static final BlockState PINK_TULIP = Blocks.PINK_TULIP.getDefaultState();
    private static final BlockState WHITE_TULIP = Blocks.WHITE_TULIP.getDefaultState();
    private static final BlockState ORANGE_TULIP = Blocks.ORANGE_TULIP.getDefaultState();

    public static final BaseTreeFeatureConfig SAKURA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), null, null, null)).func_236700_a_().build();

    public static final BlockClusterFeatureConfig ALLIUM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ALLIUM), SimpleBlockPlacer.field_236447_c_)).tries(64).build();
    public static final BlockClusterFeatureConfig TULIP_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).addWeightedBlockstate(PINK_TULIP, 2).addWeightedBlockstate(WHITE_TULIP, 2).addWeightedBlockstate(ORANGE_TULIP, 1), SimpleBlockPlacer.field_236447_c_)).tries(64).build();

    public static void addKoiPonds(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.LAKES, HanamiFeatures.KOI_POND.withConfiguration(NoFeatureConfig.field_236559_b_).withPlacement(Placement.CHANCE_TOP_SOLID_HEIGHTMAP.configure(new ChanceConfig(6))));
    }

    public static void addSakuraFeatures(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.SHORT_BAMBOO.withConfiguration(new ProbabilityConfig(0.0F)).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.SAKURA_TREE_WITH_FALLEN_LEAVES.withConfiguration(SAKURA_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(6, 0.1F, 1))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.FALLEN_SAKURA_LEAVES.withConfiguration(NoFeatureConfig.field_236559_b_).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(32))));
    }

    public static void addSakuraFlowers(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_RANDOM_SELECTOR.withConfiguration(
                new MultipleWithChanceRandomFeatureConfig(ImmutableList.of(
                        Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.LILAC_CONFIG),
                        Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.PEONY_CONFIG)), 0))
                .withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(2))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_RANDOM_SELECTOR.withConfiguration(
                new MultipleWithChanceRandomFeatureConfig(ImmutableList.of(
                        Feature.RANDOM_PATCH.withConfiguration(TULIP_CONFIG),
                        Feature.RANDOM_PATCH.withConfiguration(ALLIUM_CONFIG),
                        Feature.FLOWER.withConfiguration(DefaultBiomeFeatures.LILY_OF_THE_VALLEY_CONFIG)), 0))
                .withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(3))));
    }
}
