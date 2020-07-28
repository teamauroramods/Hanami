package com.teamaurora.hanami.common.world.gen.feature;

import com.mojang.serialization.Codec;
import java.util.Random;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class ShortBambooFeature extends Feature<ProbabilityConfig> {
    private static final BlockState BAMBOO_BASE = Blocks.BAMBOO.getDefaultState().with(BambooBlock.PROPERTY_AGE, Integer.valueOf(1)).with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.NONE).with(BambooBlock.PROPERTY_STAGE, Integer.valueOf(0));
    private static final BlockState BAMBOO_LARGE_LEAVES_GROWN = BAMBOO_BASE.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.LARGE).with(BambooBlock.PROPERTY_STAGE, Integer.valueOf(1));
    private static final BlockState BAMBOO_LARGE_LEAVES = BAMBOO_BASE.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.LARGE);
    private static final BlockState BAMBOO_SMALL_LEAVES = BAMBOO_BASE.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.SMALL);

    public ShortBambooFeature(Codec<ProbabilityConfig> p_i231924_1_) {
        super(p_i231924_1_);
    }

    public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, ProbabilityConfig config) {
        if (pos.getY() > 1) {
            if (!isAir(world, pos.down())) {
                int i = rand.nextInt(5) + 2;
                // if i < 4 only do 1 leaf
                BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
                boolean flag = true;
                for (int j = 0; j < i && flag; ++j) {
                    blockpos$mutableblockpos.setPos(pos.up(j));
                    if (isAir(world, blockpos$mutableblockpos)) {
                        if ((i < 4 && j == i - 1) || (i >= 4 && j == i - 2)) {
                            world.setBlockState(blockpos$mutableblockpos, BAMBOO_SMALL_LEAVES, 2);
                        } else if (i >= 4 && j == i - 1) {
                            world.setBlockState(blockpos$mutableblockpos, BAMBOO_LARGE_LEAVES, 2);
                        } else {
                            world.setBlockState(blockpos$mutableblockpos, BAMBOO_BASE, 2);
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

    public static boolean isAir(IWorldGenerationBaseReader worldIn, BlockPos pos)
    {
        if (worldIn instanceof net.minecraft.world.IWorldReader)
        {
            return worldIn.hasBlockState(pos, state -> state.canBeReplacedByLeaves((net.minecraft.world.IWorldReader) worldIn, pos));
        }
        return worldIn.hasBlockState(pos, (state) -> {
            return state.isAir();
        });
    }
}