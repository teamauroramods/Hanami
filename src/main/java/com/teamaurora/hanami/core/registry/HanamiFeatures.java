package com.teamaurora.hanami.core.registry;

import com.teamaurora.hanami.common.world.gen.feature.*;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Hanami.MODID);

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> SAKURA_TREE = FEATURES.register("sakura_tree", () -> new SakuraTreeFeature(BaseTreeFeatureConfig.field_236676_a_));
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> SAKURA_TREE_WITH_FALLEN_LEAVES = FEATURES.register("sakura_tree_with_fallen_leaves", () -> new SakuraTreeWithFallenLeavesFeature(BaseTreeFeatureConfig.field_236676_a_));
    public static final RegistryObject<Feature<ProbabilityConfig>> SHORT_BAMBOO = FEATURES.register("short_bamboo", () -> new ShortBambooFeature(ProbabilityConfig.field_236576_b_));
    public static final RegistryObject<Feature<NoFeatureConfig>> FALLEN_SAKURA_LEAVES = FEATURES.register("fallen_sakura_leaves", () -> new FallenSakuraLeavesFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<Feature<NoFeatureConfig>> KOI_POND = FEATURES.register("koi_pond", () -> new KoiPondFeature(NoFeatureConfig.field_236558_a_));
}
