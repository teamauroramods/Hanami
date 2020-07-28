package com.teamaurora.hanami.core.registry;

import com.teamaurora.hanami.common.world.gen.feature.FallenSakuraLeavesFeature;
import com.teamaurora.hanami.common.world.gen.feature.SakuraTreeFeature;
import net.minecraft.world.gen.feature.*;

public class HanamiFeatures {
    public static final Feature<BaseTreeFeatureConfig> SAKURA_TREE = new SakuraTreeFeature(BaseTreeFeatureConfig.field_236676_a_);
    public static final Feature<ProbabilityConfig> SHORT_BAMBOO = new BambooFeature(ProbabilityConfig.field_236576_b_);
    public static final Feature<NoFeatureConfig> FALLEN_SAKURA_LEAVES = new FallenSakuraLeavesFeature(NoFeatureConfig.field_236558_a_);
}
