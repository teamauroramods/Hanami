package com.teamaurora.hanami.common.world.biome;

import com.teamaurora.hanami.core.registry.HanamiBlocks;
import com.teamaurora.hanami.core.registry.HanamiFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class HanamiBiomeFeatures {
    private static final BlockState SAKURA_LOG = HanamiBlocks.SAKURA_LOG.get().getDefaultState();
    private static final BlockState SAKURA_LEAVES = HanamiBlocks.SAKURA_LEAVES.get().getDefaultState();
    private static final BlockState BAMBOO = Blocks.BAMBOO.getDefaultState();

    public static final BaseTreeFeatureConfig SAKURA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), null, null, null)).func_236700_a_().build();

    public static void addSakuraFeatures(Biome biomeIn) {
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.SHORT_BAMBOO.withConfiguration(new ProbabilityConfig(0.0F)).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(20))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.SAKURA_TREE.withConfiguration(SAKURA_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(8, 0.1F, 1))));
        biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HanamiFeatures.FALLEN_SAKURA_LEAVES.withConfiguration(NoFeatureConfig.field_236559_b_).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(56))));
    }
}
