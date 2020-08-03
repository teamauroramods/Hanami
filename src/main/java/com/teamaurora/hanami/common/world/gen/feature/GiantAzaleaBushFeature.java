package com.teamaurora.hanami.common.world.gen.feature;

import com.mojang.serialization.Codec;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class GiantAzaleaBushFeature extends Feature<NoFeatureConfig> {
    public GiantAzaleaBushFeature(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean func_230362_a_(ISeedReader worldIn, StructureManager structureManager, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
        boolean large = rand.nextBoolean();

        if (pos.getY() <= 63) return false;

        if (worldIn.getBlockState(pos.add(0, -1, 0)).getBlock() != Blocks.GRASS_BLOCK ||
                worldIn.getBlockState(pos.add(1, -1, 0)).getBlock() != Blocks.GRASS_BLOCK ||
                worldIn.getBlockState(pos.add(-1, -1, 0)).getBlock() != Blocks.GRASS_BLOCK ||
                worldIn.getBlockState(pos.add(0, -1, 1)).getBlock() != Blocks.GRASS_BLOCK ||
                worldIn.getBlockState(pos.add(0, -1, -1)).getBlock() != Blocks.GRASS_BLOCK) {
            return false;
        }

        if (large && (worldIn.getBlockState(pos.add(-1, -1, -1)).getBlock() != Blocks.GRASS_BLOCK ||
                worldIn.getBlockState(pos.add(-1, -1, 1)).getBlock() != Blocks.GRASS_BLOCK ||
                worldIn.getBlockState(pos.add(1, -1, -1)).getBlock() != Blocks.GRASS_BLOCK ||
                worldIn.getBlockState(pos.add(1, -1, 1)).getBlock() != Blocks.GRASS_BLOCK)) {
            return false;
        }

        if (large) {
            this.placeSegmentAt(worldIn, rand, pos, 2);
            this.placeSegmentAt(worldIn, rand, pos.north(), rand.nextInt(2) + 1);
            this.placeSegmentAt(worldIn, rand, pos.east(), rand.nextInt(2) + 1);
            this.placeSegmentAt(worldIn, rand, pos.south(), rand.nextInt(2) + 1);
            this.placeSegmentAt(worldIn, rand, pos.west(), rand.nextInt(2) + 1);
            this.placeSegmentAt(worldIn, rand, pos.north().east(), rand.nextInt(2));
            this.placeSegmentAt(worldIn, rand, pos.south().east(), rand.nextInt(2));
            this.placeSegmentAt(worldIn, rand, pos.south().west(), rand.nextInt(2));
            this.placeSegmentAt(worldIn, rand, pos.north().west(), rand.nextInt(2));
        } else {
            this.placeSegmentAt(worldIn, rand, pos, 1);
            this.placeSegmentAt(worldIn, rand, pos.north(), rand.nextInt(2));
            this.placeSegmentAt(worldIn, rand, pos.east(), rand.nextInt(2));
            this.placeSegmentAt(worldIn, rand, pos.south(), rand.nextInt(2));
            this.placeSegmentAt(worldIn, rand, pos.west(), rand.nextInt(2));
            this.placeSegmentAt(worldIn, rand, pos.north().east(), 0);
            this.placeSegmentAt(worldIn, rand, pos.south().east(), 0);
            this.placeSegmentAt(worldIn, rand, pos.south().west(), 0);
            this.placeSegmentAt(worldIn, rand, pos.north().west(), 0);
        }

        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-7, -7, -7), pos.add(7, 7, 7))) {
            if (worldIn.getBlockState(blockPos).getBlock() == Blocks.AIR && worldIn.getBlockState(blockPos.down()).getBlock() == Blocks.GRASS_BLOCK) {
                if (rand.nextInt(15) == 0) {
                    this.setLogState(worldIn, blockPos, HanamiBlocks.AZALEA_BUSH.get().getDefaultState());
                }
            }
        }

        return true;
    }

    private void placeSegmentAt(ISeedReader worldIn, Random rand, BlockPos pos, int height) {
        for (int i = 0; i <= height; i++) {
            if (i == height) {
                if (rand.nextInt(3) == 0) {
                    setLogState(worldIn, pos.up(i), HanamiBlocks.AZALEA_BUSH.get().getDefaultState());
                }
            } else {
                setLogState(worldIn, pos.up(i), HanamiBlocks.COMPACT_AZALEA.get().getDefaultState());
            }
        }
    }

    protected final void setLogState(IWorldWriter worldIn, BlockPos pos, BlockState state) {
        worldIn.setBlockState(pos, state, 18);
    }
}
