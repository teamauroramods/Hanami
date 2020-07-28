package com.teamaurora.hanami.common.world.gen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.abnormals_core.common.blocks.wood.AbnormalsLogBlock;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class SakuraTreeWithFallenLeavesFeature extends Feature<BaseTreeFeatureConfig> {
    public SakuraTreeWithFallenLeavesFeature(Codec<BaseTreeFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos position, BaseTreeFeatureConfig config) {
        int logHeight = rand.nextInt(3) + 4;
        int branchEast1 = -1;
        int branchEast2 = -1;
        int branchWest1 = -1;
        int branchWest2 = -1;
        int branchNorth1 = -1;
        int branchNorth2 = -1;
        int branchSouth1 = -1;
        int branchSouth2 = -1;
        int randChooser;

        BlockState LEAF_CARPET = HanamiBlocks.SAKURA_LEAF_CARPET.get().getDefaultState();

        int branchChance = 2; // one in n

        if (logHeight == 4) {
            if (rand.nextInt(branchChance) == 0) {
                // east branch exists
                branchEast1 = 3;
            }
            if (rand.nextInt(branchChance) == 0) {
                // west branch exists
                branchWest1 = 3;
            }
            if (rand.nextInt(branchChance) == 0) {
                // north branch exists
                branchNorth1 = 3;
            }
            if (rand.nextInt(branchChance) == 0) {
                // south branch exists
                branchSouth1 = 3;
            }
        } else if (logHeight == 5) {
            if (rand.nextInt(branchChance) == 0) {
                // east branch exists
                branchEast1 = 3 + rand.nextInt(2);
            }
            if (rand.nextInt(branchChance) == 0) {
                // west branch exists
                branchWest1 = 3 + rand.nextInt(2);
            }
            if (rand.nextInt(branchChance) == 0) {
                // north branch exists
                branchNorth1 = 3 + rand.nextInt(2);
            }
            if (rand.nextInt(branchChance) == 0) {
                // south branch exists
                branchSouth1 = 3 + rand.nextInt(2);
            }
        } else if (logHeight == 6) {
            if (rand.nextInt(branchChance) == 0) {
                // east branch exists
                randChooser = rand.nextInt(4);
                if (randChooser == 3) {
                    branchEast1 = 3;
                    branchEast2 = 5;
                } else {
                    branchEast1 = 3 + randChooser;
                }
            }
            if (rand.nextInt(branchChance) == 0) {
                // west branch exists
                randChooser = rand.nextInt(4);
                if (randChooser == 3) {
                    branchWest1 = 3;
                    branchWest2 = 5;
                } else {
                    branchWest1 = 3 + randChooser;
                }
            }
            if (rand.nextInt(branchChance) == 0) {
                // north branch exists
                randChooser = rand.nextInt(4);
                if (randChooser == 3) {
                    branchNorth1 = 3;
                    branchNorth2 = 5;
                } else {
                    branchNorth1 = 3 + randChooser;
                }
            }
            if (rand.nextInt(branchChance) == 0) {
                // south branch exists
                randChooser = rand.nextInt(4);
                if (randChooser == 3) {
                    branchSouth1 = 3;
                    branchSouth2 = 5;
                } else {
                    branchSouth1 = 3 + randChooser;
                }
            }
        }
        Vector3i[] leafOffsets = new Vector3i[4];
        leafOffsets[0] = new Vector3i(0, 0, rand.nextInt(3) - 1);
        leafOffsets[1] = new Vector3i(0, 0, rand.nextInt(3) - 1);
        leafOffsets[2] = new Vector3i(rand.nextInt(3) - 1, 0, 0);
        leafOffsets[3] = new Vector3i(rand.nextInt(3) - 1, 0, 0);

        boolean flag = true;
        if (position.getY() >= 1 && position.getY() + logHeight + 2 <= worldIn.getHeight()) {
            for (int i = position.getY(); i <= position.getY() + logHeight + 2; ++i) {
                int j = 2;
                if (i <= position.getY() + logHeight - 2) {
                    j = 0;
                }
                if (j == position.getY() + logHeight + 2) {
                    j = 1;
                }

                BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

                BlockPos posOffset = position.add(leafOffsets[0]);
                if (i - position.getY() == branchEast1 || i - position.getY() == branchEast2) {
                    if (
                            !isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(position.getX() + 1, i, position.getZ())) ||
                            !isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(position.getX() + 2, i, position.getZ())) ||
                            !checkBranchLeaves(position.getX() + 2, i, posOffset.getZ(), worldIn, blockpos$mutableblockpos)
                    ) {
                        flag = false;
                    }
                }
                posOffset = position.add(leafOffsets[1]);
                if (i - position.getY() == branchWest1 || i - position.getY() == branchWest2) {
                    if (
                            !isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(position.getX() - 1, i, position.getZ())) ||
                                    !isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(position.getX() - 2, i, position.getZ())) ||
                                    !checkBranchLeaves(position.getX() - 2, i, posOffset.getZ(), worldIn, blockpos$mutableblockpos)
                    ) {
                        flag = false;
                    }
                }
                posOffset = position.add(leafOffsets[2]);
                if (i - position.getY() == branchSouth1 || i - position.getY() == branchSouth2) {
                    if (
                            !isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(position.getX(), i, position.getZ() + 1)) ||
                                    !isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(position.getX(), i, position.getZ() + 2)) ||
                                    !checkBranchLeaves(posOffset.getX(), i, position.getZ() + 2, worldIn, blockpos$mutableblockpos)
                    ) {
                        flag = false;
                    }
                }
                posOffset = position.add(leafOffsets[3]);
                if (i - position.getY() == branchNorth1 || i - position.getY() == branchNorth2) {
                    if (
                            !isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(position.getX(), i, position.getZ() - 1)) ||
                                    !isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(position.getX(), i, position.getZ() - 2)) ||
                                    !checkBranchLeaves(posOffset.getX(), i, position.getZ() - 2, worldIn, blockpos$mutableblockpos)
                    ) {
                        flag = false;
                    }
                }

                for (int k = position.getX() - j; k <= position.getX() + j && flag; ++k) {
                    for (int l = position.getZ() - j; l <= position.getZ() + j && flag; ++l) {
                        if (i >= 0 && i < worldIn.getHeight()) {
                            if (!isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(k, i, l))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }
            if (!flag) {
                return false;
            } else if (isValidGround(worldIn, position.down()) && position.getY() < worldIn.getHeight() - logHeight - 2) {
                setDirtAt(worldIn, position.down());

                for (int i2 = 0; i2 <= logHeight; ++i2) {
                    BlockPos blockpos = position.up(i2);
                    if (isAirOrLeaves(worldIn, blockpos)) {
                        this.placeLogAt(worldIn, blockpos, rand, config);
                    }
                    if (i2 == branchEast1 || i2 == branchEast2) {
                        if (isAirOrLeaves(worldIn, blockpos.east())) {
                            this.placeLogXAt(worldIn, blockpos.east(), rand, config);
                        }
                        if (rand.nextInt(4) > 0 || i2 == branchEast2) {
                            if (isAirOrLeaves(worldIn, blockpos.east(2))) {
                                this.placeLogXAt(worldIn, blockpos.east(2), rand, config);
                            }
                                if (rand.nextInt(4) == 0) {
                                    this.placeLeavesAt(worldIn, blockpos.east(2).add(leafOffsets[0]), rand, config);
                                } else {
                                    this.placeThinLeavesAt(worldIn, blockpos.east(2).add(leafOffsets[0]), rand, config);
                                }
                        }
                    }
                    if (i2 == branchWest1 || i2 == branchWest2) {
                        if (isAirOrLeaves(worldIn, blockpos.west())) {
                            this.placeLogXAt(worldIn, blockpos.west(), rand, config);
                        }
                        if (rand.nextInt(4) > 0 || i2 == branchWest2) {
                            if (isAirOrLeaves(worldIn, blockpos.west(2))) {
                                this.placeLogXAt(worldIn, blockpos.west(2), rand, config);
                            }
                                if (rand.nextInt(4) == 0) {
                                    this.placeLeavesAt(worldIn, blockpos.west(2).add(leafOffsets[1]), rand, config);
                                } else {
                                    this.placeThinLeavesAt(worldIn, blockpos.west(2).add(leafOffsets[1]), rand, config);
                                }
                        }
                    }
                    if (i2 == branchSouth1 || i2 == branchSouth2) {
                        if (isAirOrLeaves(worldIn, blockpos.south())) {
                            this.placeLogZAt(worldIn, blockpos.south(), rand, config);
                        }
                        if (rand.nextInt(4) > 0 || i2 == branchSouth2) {
                            if (isAirOrLeaves(worldIn, blockpos.south(2))) {
                                this.placeLogZAt(worldIn, blockpos.south(2), rand, config);
                            }
                                if (rand.nextInt(4) == 0) {
                                    this.placeLeavesAt(worldIn, blockpos.south(2).add(leafOffsets[2]), rand, config);
                                } else {
                                    this.placeThinLeavesAt(worldIn, blockpos.south(2).add(leafOffsets[2]), rand, config);
                                }
                        }
                    }
                    if (i2 == branchNorth1 || i2 == branchNorth2) {
                        if (isAirOrLeaves(worldIn, blockpos.north())) {
                            this.placeLogZAt(worldIn, blockpos.north(), rand, config);
                        }
                        if (rand.nextInt(4) > 0 || i2 == branchNorth2) {
                            if (isAirOrLeaves(worldIn, blockpos.north(2))) {
                                this.placeLogZAt(worldIn, blockpos.north(2), rand, config);
                            }
                                if (rand.nextInt(4) == 0) {
                                    this.placeLeavesAt(worldIn, blockpos.north(2).add(leafOffsets[3]), rand, config);
                                } else {
                                    this.placeThinLeavesAt(worldIn, blockpos.north(2).add(leafOffsets[3]), rand, config);
                                }
                        }
                    }
                    if (i2 == logHeight) {
                        this.placeLeavesAt(worldIn, blockpos, rand, config);
                    }
                }

                if (rand.nextInt(4) == 0) {
                    for (int x = -3; x <= 3; ++x) {
                        for (int z = -3; z <= 3; ++z) {
                            if ((((Math.abs(x) <= 1 && Math.abs(z) <= 1) || x == 0 || z == 0) && rand.nextInt(20) < 19) || ((Math.abs(x) <= 2 || Math.abs(z) <= 2) && rand.nextInt(20) < 7)) {
                                for (int y = -3; y <= 3; ++y) {
                                    BlockPos placePos = position.add(x, y, z);
                                    if (worldIn.isAirBlock(placePos) && placePos.getY() < worldIn.getHeight() && worldIn.getBlockState(placePos.down()).getBlock() == Blocks.GRASS_BLOCK) {
                                        worldIn.setBlockState(placePos, LEAF_CARPET, 2);
                                        flag = true;
                                    }
                                }
                            }
                        }
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

    private boolean checkBranchLeaves(int x, int y, int z, ISeedReader worldIn, BlockPos.Mutable blockpos$mutableblockpos) {
        boolean flag = true;
        for (int i = y - 1; i <= y + 2; ++i) {
            int j = 2;
            if (i == y + 2) {
                j = 1;
            }

            for (int k = x - j; k <= x + j && flag; ++k) {
                for (int l = z - j; l <= z + j && flag; ++l) {
                    if (i >= 0 && i < worldIn.getHeight()) {
                        if (!isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(k, i, l))) {
                            flag = false;
                        }
                    } else {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    private void placeLogAt(IWorldWriter worldIn, BlockPos pos, Random rand, BaseTreeFeatureConfig config)
    {
        this.setLogState(worldIn, pos, config.trunkProvider.getBlockState(rand, pos));
    }

    private void placeLogXAt(IWorldWriter worldIn, BlockPos pos, Random rand, BaseTreeFeatureConfig config)
    {
        this.setLogState(worldIn, pos, config.trunkProvider.getBlockState(rand, pos).with(AbnormalsLogBlock.AXIS, Direction.Axis.X));
    }

    private void placeLogZAt(IWorldWriter worldIn, BlockPos pos, Random rand, BaseTreeFeatureConfig config)
    {
        this.setLogState(worldIn, pos, config.trunkProvider.getBlockState(rand, pos).with(AbnormalsLogBlock.AXIS, Direction.Axis.Z));
    }

    private void placeLeavesAt(IWorldGenerationReader world, BlockPos source, Random rand, BaseTreeFeatureConfig config) {
        this.placeSafeLeafAt(world, source.add(0, -2, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(0, -1, 0), rand, config);

        for (BlockPos blockpos : BlockPos.getAllInBoxMutable(source.add(-1,0,-1), source.add(1,1,1))) {
            this.placeSafeLeafAt(world, blockpos, rand, config);
        }
        this.placeSafeLeafAt(world, source.add(-1,-1,-1), rand, config);
        this.placeSafeLeafAt(world, source.add(-1,-1,1), rand, config);
        this.placeSafeLeafAt(world, source.add(1,-1,-1), rand, config);
        this.placeSafeLeafAt(world, source.add(1,-1,1), rand, config);

        for (BlockPos blockpos1 : BlockPos.getAllInBoxMutable(source.add(-2,0,-1), source.add(-2,1,1))) {
            this.placeSafeLeafAt(world, blockpos1, rand, config);
        }
        for (BlockPos blockpos2 : BlockPos.getAllInBoxMutable(source.add(-1,0,-2), source.add(1,1,-2))) {
            this.placeSafeLeafAt(world, blockpos2, rand, config);
        }
        for (BlockPos blockpos3 : BlockPos.getAllInBoxMutable(source.add(2,0,-1), source.add(2,1,1))) {
            this.placeSafeLeafAt(world, blockpos3, rand, config);
        }
        for (BlockPos blockpos4 : BlockPos.getAllInBoxMutable(source.add(-1,0,2), source.add(1,1,2))) {
            this.placeSafeLeafAt(world, blockpos4, rand, config);
        }

        this.placeSafeLeafAt(world, source.add(-2, -1, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(2, -1, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(0, -1, -2), rand, config);
        this.placeSafeLeafAt(world, source.add(0, -1, 2), rand, config);

        this.placeSafeLeafAt(world, source.add(0, 2, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(-1, 2, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(1, 2, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(0, 2, -1), rand, config);
        this.placeSafeLeafAt(world, source.add(0, 2, 1), rand, config);

        this.placeRandomLeafAt(world, source.add(-2, -2, 0), rand, config);
        this.placeRandomLeafAt(world, source.add(2, -2, 0), rand, config);
        this.placeRandomLeafAt(world, source.add(0, -2, -2), rand, config);
        this.placeRandomLeafAt(world, source.add(0, -2, 2), rand, config);

        this.placeRandomLeafAt(world, source.add(-2, -1, -1), rand, config);
        this.placeRandomLeafAt(world, source.add(-2, -1, 1), rand, config);
        this.placeRandomLeafAt(world, source.add(2, -1, -1), rand, config);
        this.placeRandomLeafAt(world, source.add(2, -1, 1), rand, config);
        this.placeRandomLeafAt(world, source.add(-1, -1, -2), rand, config);
        this.placeRandomLeafAt(world, source.add(1, -1, -2), rand, config);
        this.placeRandomLeafAt(world, source.add(-1, -1, 2), rand, config);
        this.placeRandomLeafAt(world, source.add(1, -1, 2), rand, config);

        this.placeRandomLeafAt(world, source.add(-1, 2, -1), rand, config);
        this.placeRandomLeafAt(world, source.add(-1, 2, 1), rand, config);
        this.placeRandomLeafAt(world, source.add(1, 2, -1), rand, config);
        this.placeRandomLeafAt(world, source.add(1, 2, 1), rand, config);
    }

    private void placeThinLeavesAt(IWorldGenerationReader world, BlockPos source, Random rand, BaseTreeFeatureConfig config) {
        this.placeSafeLeafAt(world, source.add(0, -1, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(0, 0, 0), rand, config);

        for (BlockPos blockpos : BlockPos.getAllInBoxMutable(source.add(-1,1,-1), source.add(1,1,1))) {
            this.placeSafeLeafAt(world, blockpos, rand, config);
        }
        this.placeSafeLeafAt(world, source.add(-1,0,0), rand, config);
        this.placeSafeLeafAt(world, source.add(1,0,0), rand, config);
        this.placeSafeLeafAt(world, source.add(0,0,-1), rand, config);
        this.placeSafeLeafAt(world, source.add(0,0,1), rand, config);

        for (BlockPos blockpos1 : BlockPos.getAllInBoxMutable(source.add(-2,1,-1), source.add(-2,1,1))) {
            this.placeSafeLeafAt(world, blockpos1, rand, config);
        }
        for (BlockPos blockpos2 : BlockPos.getAllInBoxMutable(source.add(-1,1,-2), source.add(1,1,-2))) {
            this.placeSafeLeafAt(world, blockpos2, rand, config);
        }
        for (BlockPos blockpos3 : BlockPos.getAllInBoxMutable(source.add(2,1,-1), source.add(2,1,1))) {
            this.placeSafeLeafAt(world, blockpos3, rand, config);
        }
        for (BlockPos blockpos4 : BlockPos.getAllInBoxMutable(source.add(-1,1,2), source.add(1,1,2))) {
            this.placeSafeLeafAt(world, blockpos4, rand, config);
        }

        this.placeSafeLeafAt(world, source.add(-2, 0, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(2, 0, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(0, 0, -2), rand, config);
        this.placeSafeLeafAt(world, source.add(0, 0, 2), rand, config);

        this.placeSafeLeafAt(world, source.add(0, 2, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(-1, 2, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(1, 2, 0), rand, config);
        this.placeSafeLeafAt(world, source.add(0, 2, -1), rand, config);
        this.placeSafeLeafAt(world, source.add(0, 2, 1), rand, config);

        this.placeRandomLeafAt(world, source.add(-2, -1, 0), rand, config);
        this.placeRandomLeafAt(world, source.add(2, -1, 0), rand, config);
        this.placeRandomLeafAt(world, source.add(0, -1, -2), rand, config);
        this.placeRandomLeafAt(world, source.add(0, -1, 2), rand, config);

        this.placeRandomLeafAt(world, source.add(-2, 0, -1), rand, config);
        this.placeRandomLeafAt(world, source.add(-2, 0, 1), rand, config);
        this.placeRandomLeafAt(world, source.add(2, 0, -1), rand, config);
        this.placeRandomLeafAt(world, source.add(2, 0, 1), rand, config);
        this.placeRandomLeafAt(world, source.add(-1, 0, -2), rand, config);
        this.placeRandomLeafAt(world, source.add(1, 0, -2), rand, config);
        this.placeRandomLeafAt(world, source.add(-1, 0, 2), rand, config);
        this.placeRandomLeafAt(world, source.add(1, 0, 2), rand, config);

        this.placeRandomLeafAt(world, source.add(-1, 2, -1), rand, config);
        this.placeRandomLeafAt(world, source.add(-1, 2, 1), rand, config);
        this.placeRandomLeafAt(world, source.add(1, 2, -1), rand, config);
        this.placeRandomLeafAt(world, source.add(1, 2, 1), rand, config);
    }

    private void placeRandomLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, BaseTreeFeatureConfig config) {
        if (rand.nextInt(20) < 13) {
            placeSafeLeafAt(world, pos, rand, config);
        }
    }

    private void placeSafeLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, BaseTreeFeatureConfig config) {
        if (isAirOrLeaves(world, pos)) {
            placeLeafAt(world, pos, rand, config);
        }
    }

    private void placeLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, BaseTreeFeatureConfig config)
    {
        if (isAirOrLeaves(world, pos))
        {
            this.setLogState(world, pos, config.leavesProvider.getBlockState(rand, pos).with(LeavesBlock.DISTANCE, 1));
        }
    }

    protected final void setLogState(IWorldWriter worldIn, BlockPos pos, BlockState state) {
        worldIn.setBlockState(pos, state, 18);
    }

    public static boolean isAir(IWorldGenerationBaseReader worldIn, BlockPos pos)
    {
        if (!(worldIn instanceof net.minecraft.world.IBlockReader))
        {
            return worldIn.hasBlockState(pos, BlockState::isAir);
        }
        else
        {
            return worldIn.hasBlockState(pos, state -> state.isAir((net.minecraft.world.IBlockReader) worldIn, pos));
        }
    }

    public static boolean isAirOrLeaves(IWorldGenerationBaseReader worldIn, BlockPos pos)
    {
        if (worldIn instanceof net.minecraft.world.IWorldReader)
        {
            return worldIn.hasBlockState(pos, state -> state.canBeReplacedByLeaves((net.minecraft.world.IWorldReader) worldIn, pos));
        }
        return worldIn.hasBlockState(pos, (state) -> {
            return state.isAir() || state.func_235714_a_(BlockTags.LEAVES);
        });
    }

    public static void setDirtAt(IWorld worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos).getBlock();
        if (block == Blocks.GRASS_BLOCK || block == Blocks.FARMLAND)
        {
            worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState(), 18);
        }
    }

    public static boolean isValidGround(IWorld world, BlockPos pos)
    {
        return world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (IPlantable) HanamiBlocks.SAKURA_SAPLING.get());
    }
}
