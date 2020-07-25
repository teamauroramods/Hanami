package com.teamaurora.hanami.common.world.biome;

import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;

public class HanamiBiomeFeatures {
    private static final BlockState SAKURA_LOG = HanamiBlocks.SAKURA_LOG.get().getDefaultState();
    private static final BlockState SAKURA_LEAVES = HanamiBlocks.SAKURA_LEAVES.get().getDefaultState();

    public static final BaseTreeFeatureConfig MAPLE_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), null, null, null)).func_236700_a_().build();
}
