package com.teamaurora.hanami.common.block.trees;

import com.teamaurora.hanami.common.world.biome.HanamiBiomeFeatures;
import com.teamaurora.hanami.core.registry.HanamiFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class SakuraTree extends Tree {
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return HanamiFeatures.SAKURA_TREE.withConfiguration(HanamiBiomeFeatures.SAKURA_TREE_CONFIG);
    }
}
