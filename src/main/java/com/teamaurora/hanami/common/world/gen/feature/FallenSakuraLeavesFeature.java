package com.teamaurora.hanami.common.world.gen.feature;

import com.mojang.serialization.Codec;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class FallenSakuraLeavesFeature extends Feature<NoFeatureConfig> {
    public FallenSakuraLeavesFeature(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockState LEAF_CARPET = HanamiBlocks.SAKURA_LEAF_CARPET.get().getDefaultState();
        boolean flag = false;
        for (int x = -3; x <= 3; ++x) {
            for (int z = -3; z <= 3; ++z) {
                if ((((Math.abs(x) <= 1 && Math.abs(z) <= 1) || x == 0 || z == 0)  && rand.nextInt(20) < 19) || ((Math.abs(x) <= 2 || Math.abs(z) <= 2) && rand.nextInt(20) < 7)) {
                    for (int y = -3; y <= 3; ++y) {
                        BlockPos placePos = pos.add(x,y,z);
                        if (world.isAirBlock(placePos) && placePos.getY() < world.getHeight() && world.getBlockState(placePos.down()).getBlock() == Blocks.GRASS_BLOCK) {
                            world.setBlockState(placePos, LEAF_CARPET, 2);
                            flag = true;
                        }
                    }
                }
            }
        }
        return flag;
    }
}
