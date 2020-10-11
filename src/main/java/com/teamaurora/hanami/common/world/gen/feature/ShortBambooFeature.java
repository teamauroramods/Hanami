package com.teamaurora.hanami.common.world.gen.feature;

import com.mojang.serialization.Codec;
import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class ShortBambooFeature extends Feature<ProbabilityConfig> {
    private static final BlockState BAMBOO_BASE = Blocks.BAMBOO.getDefaultState().with(BambooBlock.PROPERTY_AGE, 1).with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.NONE).with(BambooBlock.PROPERTY_STAGE, 0);
    private static final BlockState BAMBOO_LARGE_LEAVES = BAMBOO_BASE.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.LARGE).with(BambooBlock.PROPERTY_STAGE, 1);
    private static final BlockState BAMBOO_SMALL_LEAVES = BAMBOO_BASE.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.SMALL).with(BambooBlock.PROPERTY_STAGE, 1);

    public ShortBambooFeature(Codec<ProbabilityConfig> configCodec) {
        super(configCodec);
    }

    public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, ProbabilityConfig config) {
        if (pos.getY() > 1) {
            if (world.getBlockState(pos.down()).getBlock() == Blocks.GRASS_BLOCK) {
                int i = rand.nextInt(5) + 2;
                // if i < 4 only do 1 leaf
                BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable();
                boolean flag = true;
                for (int j = 0; j < i && flag; ++j) {
                    mutableBlockPos.setPos(pos.up(j));
                    if (isAir(world, mutableBlockPos)) {
                        if ((i < 4 && j == i - 1) || (i >= 4 && j == i - 2)) {
                            world.setBlockState(mutableBlockPos, BAMBOO_SMALL_LEAVES, 2);
                        } else if (i >= 4 && j == i - 1) {
                            world.setBlockState(mutableBlockPos, BAMBOO_LARGE_LEAVES, 2);
                        } else {
                            world.setBlockState(mutableBlockPos, BAMBOO_BASE, 2);
                        }
                    } else {
                        flag = false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isAir(IWorldGenerationBaseReader world, BlockPos pos) {
        if (world instanceof IWorldReader) {
            return world.hasBlockState(pos, state -> state.canBeReplacedByLeaves((IWorldReader) world, pos));
        }
        return world.hasBlockState(pos, AbstractBlock.AbstractBlockState::isAir);
    }
}