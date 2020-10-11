package com.teamaurora.hanami.common.world.gen.feature;

import com.mojang.serialization.Codec;
import com.teamaurora.hanami.common.entity.SakuraBlossomEntity;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.nbt.CompoundNBT;
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
    public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
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

        if (!checkPondLoc(world, pos)) return false;
        if (pond1 && !checkPondLoc(world, pos.add(3,0,1))) pond1 = false;
        if (pond2 && !checkPondLoc(world, pos.add(3,0,-1))) pond2 = false;
        if (pond3 && !checkPondLoc(world, pos.add(-3,0,1))) pond3 = false;
        if (pond4 && !checkPondLoc(world, pos.add(-3,0,-1))) pond4 = false;
        if (pond5 && !checkPondLoc(world, pos.add(1,0,3))) pond5 = false;
        if (pond6 && !checkPondLoc(world, pos.add(-1,0,3))) pond6 = false;
        if (pond7 && !checkPondLoc(world, pos.add(1,0,-3))) pond7 = false;
        if (pond8 && !checkPondLoc(world, pos.add(-1,0,-3))) pond8 = false;

        boolean flowers = (rand.nextInt(4) == 0);

        int flowerType = rand.nextInt(5);

        BlockState flower;
        if (flowerType == 0) {
            flower = Blocks.ALLIUM.getDefaultState();
        } else if (flowerType == 1) {
            flower = Blocks.PINK_TULIP.getDefaultState();
        } else if (flowerType == 2) {
            flower = Blocks.WHITE_TULIP.getDefaultState();
        } else if (flowerType == 3) {
            flower = HanamiBlocks.AZALEA_BUSH.get().getDefaultState();
        } else {
            if (rand.nextBoolean()) {
                flower = Blocks.ORANGE_TULIP.getDefaultState();
            } else {
                flower = Blocks.LILY_OF_THE_VALLEY.getDefaultState();
            }
        }

        placePondAt(world, pos, rand, flowers, flower);
        if (pond1) placePondAt(world, pos.add(3,0,1), rand, flowers, flower);
        if (pond2) placePondAt(world, pos.add(3,0,-1), rand, flowers, flower);
        if (pond3) placePondAt(world, pos.add(-3,0,1), rand, flowers, flower);
        if (pond4) placePondAt(world, pos.add(-3,0,-1), rand, flowers, flower);
        if (pond5) placePondAt(world, pos.add(1,0,3), rand, flowers, flower);
        if (pond6) placePondAt(world, pos.add(-1,0,3), rand, flowers, flower);
        if (pond7) placePondAt(world, pos.add(1,0,-3), rand, flowers, flower);
        if (pond8) placePondAt(world, pos.add(-1,0,-3), rand, flowers, flower);

        int numFeesh = rand.nextInt(3) + 1;
        for (int i = 0; i < numFeesh; i++) {
            TropicalFishEntity poorFeesh = new TropicalFishEntity(EntityType.TROPICAL_FISH, world.getWorld());
            poorFeesh.setPosition(pos.getX(), pos.getY(), pos.getZ());
            int variantIndx = rand.nextInt(3);
            int variant = 0;
            if (variantIndx == 0) {
                variant = 65536;
            } else if (variantIndx == 1) {
                variant = 117441280;
            } else {
                variant = 117441536;
            }
            CompoundNBT feeshNbt = new CompoundNBT();
            feeshNbt.putInt("BucketVariantTag", variant);
            poorFeesh.onInitialSpawn(world, world.getDifficultyForLocation(pos), SpawnReason.NATURAL, null, feeshNbt);
            poorFeesh.enablePersistence();
            world.addEntity(poorFeesh);
        }
        return true;
    }

    private boolean checkPondLoc(ISeedReader world, BlockPos pos) {
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1,-2,-1), pos.add(1,-2,1))) {
            if (!isRightGround(world, blockPos)) return false;
        }
        for (BlockPos blockPos1 : BlockPos.getAllInBoxMutable(pos.add(-2,-1,-2), pos.add(2,-1,2))) {
            if (!isRightGround(world, blockPos1)) return false;
        }
        for (BlockPos blockPos2 : BlockPos.getAllInBoxMutable(pos.add(3,-1,-1), pos.add(3,-1,1))) {
            if (!isRightGround(world, blockPos2)) return false;
        }
        for (BlockPos blockPos3 : BlockPos.getAllInBoxMutable(pos.add(-3,-1,-1), pos.add(-3,-1,1))) {
            if (!isRightGround(world, blockPos3)) return false;
        }
        for (BlockPos blockPos4 : BlockPos.getAllInBoxMutable(pos.add(-1,-1,3), pos.add(1,-1,3))) {
            if (!isRightGround(world, blockPos4)) return false;
        }
        for (BlockPos blockPos5 : BlockPos.getAllInBoxMutable(pos.add(-1,-1,-3), pos.add(1,-1,-3))) {
            if (!isRightGround(world, blockPos5)) return false;
        }
        return true;
    }

    private void placePondAt(ISeedReader world, BlockPos pos, Random rand, boolean flowers, BlockState flower) {
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-2,-1,-2), pos.add(2,-1,2))) {
            placeWaterAt(world, blockPos);
        }
        for (BlockPos blockPos1 : BlockPos.getAllInBoxMutable(pos.add(3,-1,-1), pos.add(3,-1,1))) {
            placeWaterAt(world, blockPos1);
        }
        for (BlockPos blockPos2 : BlockPos.getAllInBoxMutable(pos.add(-3,-1,-1), pos.add(-3,-1,1))) {
            placeWaterAt(world, blockPos2);
        }
        for (BlockPos blockPos3 : BlockPos.getAllInBoxMutable(pos.add(-1,-1,3), pos.add(1,-1,3))) {
            placeWaterAt(world, blockPos3);
        }
        for (BlockPos blockPos4 : BlockPos.getAllInBoxMutable(pos.add(-1,-1,-3), pos.add(1,-1,-3))) {
            placeWaterAt(world, blockPos4);
        }

        placeWaterAt(world, pos.add(0,-2,0));
        placeWaterAt(world, pos.add(1,-2,0));
        placeWaterAt(world, pos.add(-1,-2,0));
        placeWaterAt(world, pos.add(0,-2,1));
        placeWaterAt(world, pos.add(0,-2,-1));

        placeWaterRandomlyAt(world, pos.add(-1,-2,-1), rand);
        placeWaterRandomlyAt(world, pos.add(-1,-2,1), rand);
        placeWaterRandomlyAt(world, pos.add(1,-2,-1), rand);
        placeWaterRandomlyAt(world, pos.add(1,-2,1), rand);

        for (BlockPos blockPos5 : BlockPos.getAllInBoxMutable(pos.add(3,0,2), pos.add(-3,3,-2))) {
            placeAirAt(world, blockPos5);
        }
        for (BlockPos blockPos6 : BlockPos.getAllInBoxMutable(pos.add(2,0,3), pos.add(-2,3,-3))) {
            placeAirAt(world, blockPos6);
        }
        for (BlockPos blockPos7 : BlockPos.getAllInBoxMutable(pos.add(4,0,1), pos.add(4,3,-1))) {
            placeAirAt(world, blockPos7);
        }
        for (BlockPos blockPos8 : BlockPos.getAllInBoxMutable(pos.add(-4,0,1), pos.add(-4,3,-1))) {
            placeAirAt(world, blockPos8);
        }
        for (BlockPos blockPos9 : BlockPos.getAllInBoxMutable(pos.add(1,0,4), pos.add(-1,3,4))) {
            placeAirAt(world, blockPos9);
        }
        for (BlockPos blockPos10 : BlockPos.getAllInBoxMutable(pos.add(1,0,-4), pos.add(-1,3,-4))) {
            placeAirAt(world, blockPos10);
        }

        placeLilyPadAt(world, pos.add(2,0,1), rand);
        placeLilyPadAt(world, pos.add(2,0,-1), rand);
        placeLilyPadAt(world, pos.add(-2,0,1), rand);
        placeLilyPadAt(world, pos.add(-2,0,-1), rand);
        placeLilyPadAt(world, pos.add(1,0,2), rand);
        placeLilyPadAt(world, pos.add(-1,0,2), rand);
        placeLilyPadAt(world, pos.add(1,0,-2), rand);
        placeLilyPadAt(world, pos.add(-1,0,-2), rand);

        if (flowers) {
            placeBambooOrFlowerAt(world, pos.add(4, 0, 1), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(4, 0, -1), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(-4, 0, 1), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(-4, 0, -1), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(1, 0, 4), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(-1, 0, 4), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(1, 0, -4), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(-1, 0, -4), rand, flower);

            placeBambooOrFlowerAt(world, pos.add(3, 0, 2), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(3, 0, -2), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(-3, 0, 2), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(-3, 0, -2), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(2, 0, 3), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(-2, 0, 3), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(2, 0, -3), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(-2, 0, -3), rand, flower);

            placeBambooOrFlowerAt(world, pos.add(4, 0, 0), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(-4, 0, 0), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(0, 0, 4), rand, flower);
            placeBambooOrFlowerAt(world, pos.add(0, 0, -4), rand, flower);
        } else {
            placeBambooAt(world, pos.add(4, 0, 1), rand);
            placeBambooAt(world, pos.add(4, 0, -1), rand);
            placeBambooAt(world, pos.add(-4, 0, 1), rand);
            placeBambooAt(world, pos.add(-4, 0, -1), rand);
            placeBambooAt(world, pos.add(1, 0, 4), rand);
            placeBambooAt(world, pos.add(-1, 0, 4), rand);
            placeBambooAt(world, pos.add(1, 0, -4), rand);
            placeBambooAt(world, pos.add(-1, 0, -4), rand);

            placeBambooAt(world, pos.add(3, 0, 2), rand);
            placeBambooAt(world, pos.add(3, 0, -2), rand);
            placeBambooAt(world, pos.add(-3, 0, 2), rand);
            placeBambooAt(world, pos.add(-3, 0, -2), rand);
            placeBambooAt(world, pos.add(2, 0, 3), rand);
            placeBambooAt(world, pos.add(-2, 0, 3), rand);
            placeBambooAt(world, pos.add(2, 0, -3), rand);
            placeBambooAt(world, pos.add(-2, 0, -3), rand);

            placeBambooAt(world, pos.add(4, 0, 0), rand);
            placeBambooAt(world, pos.add(-4, 0, 0), rand);
            placeBambooAt(world, pos.add(0, 0, 4), rand);
            placeBambooAt(world, pos.add(0, 0, -4), rand);
        }
    }

    private static final BlockState BAMBOO_BASE = Blocks.BAMBOO.getDefaultState().with(BambooBlock.PROPERTY_AGE, 1).with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.NONE).with(BambooBlock.PROPERTY_STAGE, 0);
    private static final BlockState BAMBOO_LARGE_LEAVES = BAMBOO_BASE.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.LARGE).with(BambooBlock.PROPERTY_STAGE, 1);
    private static final BlockState BAMBOO_SMALL_LEAVES = BAMBOO_BASE.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.SMALL).with(BambooBlock.PROPERTY_STAGE, 1);

    private void placeBambooOrFlowerAt(ISeedReader world, BlockPos pos, Random rand, BlockState flower) {
        this.placeGrassAt(world, pos.down());

        if (rand.nextInt(4) == 0 && world.getBlockState(pos.down()).getBlock() == Blocks.GRASS_BLOCK) {
            int randHeight = rand.nextInt(3) + 1;

            for (int height = 0; height <= randHeight; ++height) {

                if (world.isAirBlock(pos.up(height))) {

                    if ((randHeight < 3 && height == randHeight) || (randHeight == 3 && height == randHeight-1)) {
                        setLogState(world, pos.up(height), BAMBOO_SMALL_LEAVES);
                    } else if (randHeight == 3 && height == randHeight) {
                        setLogState(world, pos.up(height), BAMBOO_LARGE_LEAVES);
                    } else {
                        setLogState(world, pos.up(height), BAMBOO_BASE);
                    }
                }
            }
        } else if (rand.nextInt(3) == 0 && world.getBlockState(pos.down()).getBlock() == Blocks.GRASS_BLOCK && world.isAirBlock(pos)) {
            setLogState(world, pos, flower);
        }
    }

    private void placeBambooAt(ISeedReader world, BlockPos pos, Random rand) {
        this.placeGrassAt(world, pos.down());

        if (rand.nextInt(4) == 0 && world.getBlockState(pos.down()).getBlock() == Blocks.GRASS_BLOCK) {
            int randHeight = rand.nextInt(3) + 1;

            for (int height = 0; height <= randHeight; ++height) {
                if (world.isAirBlock(pos.up(height))) {
                    if ((randHeight < 3 && height == randHeight) || (randHeight == 3 && height == randHeight - 1)) {
                        setLogState(world, pos.up(height), BAMBOO_SMALL_LEAVES);
                    } else if (randHeight == 3 && height == randHeight) {
                        setLogState(world, pos.up(height), BAMBOO_LARGE_LEAVES);
                    } else {
                        setLogState(world, pos.up(height), BAMBOO_BASE);
                    }
                }
            }
        }
    }

    private void placeDirtAt(ISeedReader world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() == Blocks.STONE || world.getBlockState(pos).getBlock() == Blocks.CAVE_AIR) {
            setLogState(world, pos, Blocks.DIRT.getDefaultState());
        }
    }

    private void placeGrassAt(ISeedReader world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() == Blocks.DIRT || world.getBlockState(pos).getBlock() == Blocks.STONE || world.getBlockState(pos).getBlock() == Blocks.CAVE_AIR || world.getBlockState(pos).getBlock() == HanamiBlocks.SAKURA_LEAF_CARPET.get()) {
            setLogState(world, pos, Blocks.GRASS_BLOCK.getDefaultState());
        }
    }

    private void placeLilyPadAt(ISeedReader world, BlockPos pos, Random rand) {
        if (rand.nextInt(3) == 0 && world.getBlockState(pos).getBlock() != HanamiBlocks.SAKURA_LOG.get()) {
            this.setLogState(world, pos, Blocks.LILY_PAD.getDefaultState());
        }
    }

    private void placeAirAt(ISeedReader world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() != HanamiBlocks.SAKURA_LEAVES.get() && world.getBlockState(pos).getBlock() != HanamiBlocks.SAKURA_LOG.get()) {
            this.setLogState(world, pos, Blocks.AIR.getDefaultState());
        }
    }

    private void placeWaterRandomlyAt(ISeedReader world, BlockPos pos, Random rand) {
        if (rand.nextBoolean()) {
            this.placeWaterAt(world, pos);
        }
    }

    private void placeWaterAt(ISeedReader world, BlockPos pos) {
        if (world.getBlockState(pos.up()).getBlock() == HanamiBlocks.SAKURA_LOG.get()) {
            this.setLogState(world, pos, HanamiBlocks.SAKURA_LOG.get().getDefaultState());
        } else {
            this.setLogState(world, pos, Blocks.WATER.getDefaultState());
        }
        this.placeDirtAt(world, pos.down());
    }

    public static boolean isRightGround(IWorldGenerationBaseReader world, BlockPos pos)
    {
        return world.hasBlockState(pos, state -> state.getBlock() == Blocks.GRASS_BLOCK || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.STONE);
    }

    protected final void setLogState(IWorldWriter world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state, 18);
    }
}
