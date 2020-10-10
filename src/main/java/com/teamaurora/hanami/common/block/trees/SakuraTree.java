package com.teamaurora.hanami.common.block.trees;

import com.teamaurora.hanami.common.world.biome.HanamiBiomeFeatures;
import com.teamaurora.hanami.core.registry.HanamiFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.ModList;

import javax.annotation.Nullable;
import java.util.Random;

public class SakuraTree extends Tree {
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random random, boolean dense) {
        if (ModList.get().isLoaded("fruitful")) {
            if (dense) {
                return HanamiFeatures.SAKURA_TREE.withConfiguration(HanamiBiomeFeatures.DENSE_SAKURA_TREE_WITH_CHERRIES_CONFIG);
            } else {
                return HanamiFeatures.SAKURA_TREE.withConfiguration(HanamiBiomeFeatures.SAKURA_TREE_WITH_CHERRIES_CONFIG);
            }
        } else {
            return HanamiFeatures.SAKURA_TREE.withConfiguration(HanamiBiomeFeatures.SAKURA_TREE_CONFIG);
        }
    }

    @Override
    public boolean func_230339_a_(ServerWorld world, ChunkGenerator gen, BlockPos pos, BlockState block, Random rand) {
        ConfiguredFeature<BaseTreeFeatureConfig, ?> configuredfeature = this.getTreeFeature(rand, world.getBlockState(pos.down()).getBlock() == Blocks.PODZOL);
        if (configuredfeature == null) {
            return false;
        } else {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
            configuredfeature.config.forcePlacement();
            if (configuredfeature.func_236265_a_(world, world.func_241112_a_(), gen, rand, pos)) {
                return true;
            } else {
                world.setBlockState(pos, block, 4);
                return false;
            }
        }
    }
}
