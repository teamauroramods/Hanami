package com.teamaurora.hanami.core.registry;

import com.teamaurora.hanami.common.world.gen.feature.SakuraTreeFeature;
import net.minecraft.world.gen.feature.BambooFeature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class HanamiFeatures {
    public static final Feature<BaseTreeFeatureConfig> SAKURA_TREE = new SakuraTreeFeature(BaseTreeFeatureConfig.field_236676_a_);
    public static final Feature<ProbabilityConfig> SHORT_BAMBOO = new BambooFeature(ProbabilityConfig.field_236576_b_);
}
