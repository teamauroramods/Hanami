package com.teamaurora.hanami.common.world.gen.feature;

import com.mojang.serialization.Codec;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class KoiPondFeature extends Feature<NoFeatureConfig> {
    public KoiPondFeature(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean func_230362_a_(ISeedReader worldIn, StructureManager structureManager, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int poolChance = 4;
        boolean pond1 = rand.nextInt(20) < poolChance; //  3,0, 1
        boolean pond2 = rand.nextInt(20) < poolChance; //  3,0,-1
        boolean pond3 = rand.nextInt(20) < poolChance; // -3,0, 1
        boolean pond4 = rand.nextInt(20) < poolChance; // -3,0,-1
        boolean pond5 = rand.nextInt(20) < poolChance; //  1,0, 3
        boolean pond6 = rand.nextInt(20) < poolChance; // -1,0, 3
        boolean pond7 = rand.nextInt(20) < poolChance; //  1,0,-3
        boolean pond8 = rand.nextInt(20) < poolChance; // -1,0,-3

        if (pos.getY() <= 63) return false;

        if (!checkPondLoc(worldIn, pos)) return false;
        if (pond1 && !checkPondLoc(worldIn, pos.add(3,0,1))) pond1 = false;
        if (pond2 && !checkPondLoc(worldIn, pos.add(3,0,-1))) pond2 = false;
        if (pond3 && !checkPondLoc(worldIn, pos.add(-3,0,1))) pond3 = false;
        if (pond4 && !checkPondLoc(worldIn, pos.add(-3,0,-1))) pond4 = false;
        if (pond5 && !checkPondLoc(worldIn, pos.add(1,0,3))) pond5 = false;
        if (pond6 && !checkPondLoc(worldIn, pos.add(-1,0,3))) pond6 = false;
        if (pond7 && !checkPondLoc(worldIn, pos.add(1,0,-3))) pond7 = false;
        if (pond8 && !checkPondLoc(worldIn, pos.add(-1,0,-3))) pond8 = false;

        boolean flowers = (rand.nextInt(4) == 0);

        int flowerType = rand.nextInt(4);

        BlockState flower;
        if (flowerType == 0) {
            flower = Blocks.ALLIUM.getDefaultState();
        } else if (flowerType == 1) {
            flower = Blocks.PINK_TULIP.getDefaultState();
        } else if (flowerType == 2) {
            flower = Blocks.WHITE_TULIP.getDefaultState();
        } else {
            if (rand.nextBoolean()) {
                flower = Blocks.ORANGE_TULIP.getDefaultState();
            } else {
                flower = Blocks.LILY_OF_THE_VALLEY.getDefaultState();
            }
        }

        placePondAt(worldIn, pos, rand, flowers, flower);
        if (pond1) placePondAt(worldIn, pos.add(3,0,1), rand, flowers, flower);
        if (pond2) placePondAt(worldIn, pos.add(3,0,-1), rand, flowers, flower);
        if (pond3) placePondAt(worldIn, pos.add(-3,0,1), rand, flowers, flower);
        if (pond4) placePondAt(worldIn, pos.add(-3,0,-1), rand, flowers, flower);
        if (pond5) placePondAt(worldIn, pos.add(1,0,3), rand, flowers, flower);
        if (pond6) placePondAt(worldIn, pos.add(-1,0,3), rand, flowers, flower);
        if (pond7) placePondAt(worldIn, pos.add(1,0,-3), rand, flowers, flower);
        if (pond8) placePondAt(worldIn, pos.add(-1,0,-3), rand, flowers, flower);


        return true;
    }

    private boolean checkPondLoc(ISeedReader worldIn, BlockPos pos) {
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1,-2,-1), pos.add(1,-2,1))) {
            if (!isRightGround(worldIn, blockPos)) return false;
        }
        for (BlockPos blockPos1 : BlockPos.getAllInBoxMutable(pos.add(-2,-1,-2), pos.add(2,-1,2))) {
            if (!isRightGround(worldIn, blockPos1)) return false;
        }
        for (BlockPos blockPos2 : BlockPos.getAllInBoxMutable(pos.add(3,-1,-1), pos.add(3,-1,1))) {
            if (!isRightGround(worldIn, blockPos2)) return false;
        }
        for (BlockPos blockPos3 : BlockPos.getAllInBoxMutable(pos.add(-3,-1,-1), pos.add(-3,-1,1))) {
            if (!isRightGround(worldIn, blockPos3)) return false;
        }
        for (BlockPos blockPos4 : BlockPos.getAllInBoxMutable(pos.add(-1,-1,3), pos.add(1,-1,3))) {
            if (!isRightGround(worldIn, blockPos4)) return false;
        }
        for (BlockPos blockPos5 : BlockPos.getAllInBoxMutable(pos.add(-1,-1,-3), pos.add(1,-1,-3))) {
            if (!isRightGround(worldIn, blockPos5)) return false;
        }
        return true;
    }

    private void placePondAt(ISeedReader worldIn, BlockPos pos, Random rand, boolean flowers, BlockState flower) {
        placeWaterAt(worldIn, pos.add(0,-2,0));
        placeWaterAt(worldIn, pos.add(1,-2,0));
        placeWaterAt(worldIn, pos.add(-1,-2,0));
        placeWaterAt(worldIn, pos.add(0,-2,1));
        placeWaterAt(worldIn, pos.add(0,-2,-1));

        placeWaterRandomlyAt(worldIn, pos.add(-1,-2,-1), rand);
        placeWaterRandomlyAt(worldIn, pos.add(-1,-2,1), rand);
        placeWaterRandomlyAt(worldIn, pos.add(1,-2,-1), rand);
        placeWaterRandomlyAt(worldIn, pos.add(1,-2,1), rand);

        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-2,-1,-2), pos.add(2,-1,2))) {
            placeWaterAt(worldIn, blockPos);
        }
        for (BlockPos blockPos1 : BlockPos.getAllInBoxMutable(pos.add(3,-1,-1), pos.add(3,-1,1))) {
            placeWaterAt(worldIn, blockPos1);
        }
        for (BlockPos blockPos2 : BlockPos.getAllInBoxMutable(pos.add(-3,-1,-1), pos.add(-3,-1,1))) {
            placeWaterAt(worldIn, blockPos2);
        }
        for (BlockPos blockPos3 : BlockPos.getAllInBoxMutable(pos.add(-1,-1,3), pos.add(1,-1,3))) {
            placeWaterAt(worldIn, blockPos3);
        }
        for (BlockPos blockPos4 : BlockPos.getAllInBoxMutable(pos.add(-1,-1,-3), pos.add(1,-1,-3))) {
            placeWaterAt(worldIn, blockPos4);
        }

        for (BlockPos blockPos5 : BlockPos.getAllInBoxMutable(pos.add(3,0,2), pos.add(-3,3,-2))) {
            placeAirAt(worldIn, blockPos5);
        }
        for (BlockPos blockPos6 : BlockPos.getAllInBoxMutable(pos.add(2,0,3), pos.add(-2,3,-3))) {
            placeAirAt(worldIn, blockPos6);
        }
        for (BlockPos blockPos7 : BlockPos.getAllInBoxMutable(pos.add(4,0,1), pos.add(4,3,-1))) {
            placeAirAt(worldIn, blockPos7);
        }
        for (BlockPos blockPos8 : BlockPos.getAllInBoxMutable(pos.add(-4,0,1), pos.add(-4,3,-1))) {
            placeAirAt(worldIn, blockPos8);
        }
        for (BlockPos blockPos9 : BlockPos.getAllInBoxMutable(pos.add(1,0,4), pos.add(-1,3,4))) {
            placeAirAt(worldIn, blockPos9);
        }
        for (BlockPos blockPos10 : BlockPos.getAllInBoxMutable(pos.add(1,0,-4), pos.add(-1,3,-4))) {
            placeAirAt(worldIn, blockPos10);
        }

        placeLilyPadAt(worldIn, pos.add(2,0,1), rand);
        placeLilyPadAt(worldIn, pos.add(2,0,-1), rand);
        placeLilyPadAt(worldIn, pos.add(-2,0,1), rand);
        placeLilyPadAt(worldIn, pos.add(-2,0,-1), rand);
        placeLilyPadAt(worldIn, pos.add(1,0,2), rand);
        placeLilyPadAt(worldIn, pos.add(-1,0,2), rand);
        placeLilyPadAt(worldIn, pos.add(1,0,-2), rand);
        placeLilyPadAt(worldIn, pos.add(-1,0,-2), rand);

        if (flowers) {
            placeBambooOrFlowerAt(worldIn, pos.add(4, 0, 1), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(4, 0, -1), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(-4, 0, 1), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(-4, 0, -1), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(1, 0, 4), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(-1, 0, 4), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(1, 0, -4), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(-1, 0, -4), rand, flower);

            placeBambooOrFlowerAt(worldIn, pos.add(3, 0, 2), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(3, 0, -2), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(-3, 0, 2), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(-3, 0, -2), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(2, 0, 3), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(-2, 0, 3), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(2, 0, -3), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(-2, 0, -3), rand, flower);

            placeBambooOrFlowerAt(worldIn, pos.add(4, 0, 0), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(-4, 0, 0), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(0, 0, 4), rand, flower);
            placeBambooOrFlowerAt(worldIn, pos.add(0, 0, -4), rand, flower);
        } else {
            placeBambooAt(worldIn, pos.add(4, 0, 1), rand);
            placeBambooAt(worldIn, pos.add(4, 0, -1), rand);
            placeBambooAt(worldIn, pos.add(-4, 0, 1), rand);
            placeBambooAt(worldIn, pos.add(-4, 0, -1), rand);
            placeBambooAt(worldIn, pos.add(1, 0, 4), rand);
            placeBambooAt(worldIn, pos.add(-1, 0, 4), rand);
            placeBambooAt(worldIn, pos.add(1, 0, -4), rand);
            placeBambooAt(worldIn, pos.add(-1, 0, -4), rand);

            placeBambooAt(worldIn, pos.add(3, 0, 2), rand);
            placeBambooAt(worldIn, pos.add(3, 0, -2), rand);
            placeBambooAt(worldIn, pos.add(-3, 0, 2), rand);
            placeBambooAt(worldIn, pos.add(-3, 0, -2), rand);
            placeBambooAt(worldIn, pos.add(2, 0, 3), rand);
            placeBambooAt(worldIn, pos.add(-2, 0, 3), rand);
            placeBambooAt(worldIn, pos.add(2, 0, -3), rand);
            placeBambooAt(worldIn, pos.add(-2, 0, -3), rand);

            placeBambooAt(worldIn, pos.add(4, 0, 0), rand);
            placeBambooAt(worldIn, pos.add(-4, 0, 0), rand);
            placeBambooAt(worldIn, pos.add(0, 0, 4), rand);
            placeBambooAt(worldIn, pos.add(0, 0, -4), rand);
        }
    }

    private static final BlockState BAMBOO_BASE = Blocks.BAMBOO.getDefaultState().with(BambooBlock.PROPERTY_AGE, Integer.valueOf(1)).with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.NONE).with(BambooBlock.PROPERTY_STAGE, Integer.valueOf(0));
    private static final BlockState BAMBOO_LARGE_LEAVES = BAMBOO_BASE.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.LARGE).with(BambooBlock.PROPERTY_STAGE, Integer.valueOf(1));
    private static final BlockState BAMBOO_SMALL_LEAVES = BAMBOO_BASE.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.SMALL).with(BambooBlock.PROPERTY_STAGE, Integer.valueOf(1));

    private void placeBambooOrFlowerAt(ISeedReader worldIn, BlockPos pos, Random rand, BlockState flower) {
        this.placeGrassAt(worldIn, pos.down());
        if (rand.nextInt(4) == 0 && worldIn.getBlockState(pos.down()).getBlock() == Blocks.GRASS_BLOCK) {
            int i = rand.nextInt(3) + 1;
            for (int j = 0; j <= i; ++j) {
                if (worldIn.isAirBlock(pos.up(j))) {
                    if ((i < 3 && j == i) || (i == 3 && j == i-1)) {
                        setLogState(worldIn, pos.up(j), BAMBOO_SMALL_LEAVES);
                    } else if (i == 3 && j == i) {
                        setLogState(worldIn, pos.up(j), BAMBOO_LARGE_LEAVES);
                    } else {
                        setLogState(worldIn, pos.up(j), BAMBOO_BASE);
                    }
                }
            }
        } else if (rand.nextBoolean() && worldIn.getBlockState(pos.down()).getBlock() == Blocks.GRASS_BLOCK) {
            setLogState(worldIn, pos, flower);
        }
    }

    private void placeBambooAt(ISeedReader worldIn, BlockPos pos, Random rand) {
        this.placeGrassAt(worldIn, pos.down());
        if (rand.nextInt(4) == 0 && worldIn.getBlockState(pos.down()).getBlock() == Blocks.GRASS_BLOCK) {
            int i = rand.nextInt(3) + 1;
            for (int j = 0; j <= i; ++j) {
                if (worldIn.isAirBlock(pos.up(j))) {
                    if ((i < 3 && j == i) || (i == 3 && j == i-1)) {
                        setLogState(worldIn, pos.up(j), BAMBOO_SMALL_LEAVES);
                    } else if (i == 3 && j == i) {
                        setLogState(worldIn, pos.up(j), BAMBOO_LARGE_LEAVES);
                    } else {
                        setLogState(worldIn, pos.up(j), BAMBOO_BASE);
                    }
                }
            }
        }
    }

    private void placeDirtAt(ISeedReader worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos).getBlock() == Blocks.AIR || worldIn.getBlockState(pos).getBlock() == Blocks.STONE || worldIn.getBlockState(pos).getBlock() == Blocks.CAVE_AIR) {
            setLogState(worldIn, pos, Blocks.DIRT.getDefaultState());
        }
    }

    private void placeGrassAt(ISeedReader worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos).getBlock() == Blocks.AIR || worldIn.getBlockState(pos).getBlock() == Blocks.DIRT || worldIn.getBlockState(pos).getBlock() == Blocks.STONE || worldIn.getBlockState(pos).getBlock() == Blocks.CAVE_AIR) {
            setLogState(worldIn, pos, Blocks.GRASS_BLOCK.getDefaultState());
        }
    }

    private void placeLilyPadAt(IWorldWriter worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(3) == 0) {
            this.setLogState(worldIn, pos, Blocks.LILY_PAD.getDefaultState());
        }
    }

    private void placeAirAt(ISeedReader worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos).getBlock() != HanamiBlocks.SAKURA_LEAVES.get() && worldIn.getBlockState(pos).getBlock() != HanamiBlocks.SAKURA_LOG.get()) {
            this.setLogState(worldIn, pos, Blocks.AIR.getDefaultState());
        }
    }

    private void placeWaterRandomlyAt(ISeedReader worldIn, BlockPos pos, Random rand) {
        if (rand.nextBoolean()) {
            this.placeWaterAt(worldIn, pos);
        }
    }

    private void placeWaterAt(ISeedReader worldIn, BlockPos pos) {
        this.setLogState(worldIn, pos, Blocks.WATER.getDefaultState());
        this.placeDirtAt(worldIn, pos.down());
    }

    public static boolean isRightGround(IWorldGenerationBaseReader worldIn, BlockPos pos)
    {
        return worldIn.hasBlockState(pos, state -> state.getBlock() == Blocks.GRASS_BLOCK || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.STONE);
    }

    protected final void setLogState(IWorldWriter worldIn, BlockPos pos, BlockState state) {
        worldIn.setBlockState(pos, state, 18);
    }
}
