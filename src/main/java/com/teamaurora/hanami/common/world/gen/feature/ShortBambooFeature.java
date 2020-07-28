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
        /*int i = 0;
        BlockPos.Mutable blockpos$mutable = p_230362_5_.func_239590_i_();
        BlockPos.Mutable blockpos$mutable1 = p_230362_5_.func_239590_i_();
        if (p_230362_1_.isAirBlock(blockpos$mutable)) {
            if (Blocks.BAMBOO.getDefaultState().isValidPosition(p_230362_1_, blockpos$mutable)) {
                int j = p_230362_4_.nextInt(3) + 3;
                if (p_230362_4_.nextFloat() < p_230362_6_.probability) {
                    int k = p_230362_4_.nextInt(2) + 1;

                    for(int l = p_230362_5_.getX() - k; l <= p_230362_5_.getX() + k; ++l) {
                        for(int i1 = p_230362_5_.getZ() - k; i1 <= p_230362_5_.getZ() + k; ++i1) {
                            int j1 = l - p_230362_5_.getX();
                            int k1 = i1 - p_230362_5_.getZ();
                            if (j1 * j1 + k1 * k1 <= k * k) {
                                blockpos$mutable1.setPos(l, p_230362_1_.getHeight(Heightmap.Type.WORLD_SURFACE, l, i1) - 1, i1);
                                if (isDirt(p_230362_1_.getBlockState(blockpos$mutable1).getBlock())) {
                                    p_230362_1_.setBlockState(blockpos$mutable1, Blocks.PODZOL.getDefaultState(), 2);
                                }
                            }
                        }
                    }
                }

                for(int l1 = 0; l1 < j && p_230362_1_.isAirBlock(blockpos$mutable); ++l1) {
                    p_230362_1_.setBlockState(blockpos$mutable, BAMBOO_BASE, 2);
                    blockpos$mutable.move(Direction.UP, 1);
                }

                if (blockpos$mutable.getY() - p_230362_5_.getY() >= 3) {
                    p_230362_1_.setBlockState(blockpos$mutable, BAMBOO_LARGE_LEAVES_GROWN, 2);
                    p_230362_1_.setBlockState(blockpos$mutable.move(Direction.DOWN, 1), BAMBOO_LARGE_LEAVES, 2);
                    p_230362_1_.setBlockState(blockpos$mutable.move(Direction.DOWN, 1), BAMBOO_SMALL_LEAVES, 2);
                }
            }

            ++i;
        }

        return i > 0;*/
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