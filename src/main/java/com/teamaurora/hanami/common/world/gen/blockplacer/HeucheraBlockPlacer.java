package com.teamaurora.hanami.common.world.gen.blockplacer;

import com.mojang.serialization.Codec;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;

import java.util.Random;

// IT JUST WORKS
public class HeucheraBlockPlacer extends BlockPlacer {
    public static final Codec<com.teamaurora.hanami.common.world.gen.blockplacer.HeucheraBlockPlacer> field_236443_b_;
    public static final com.teamaurora.hanami.common.world.gen.blockplacer.HeucheraBlockPlacer field_236444_c_ = new com.teamaurora.hanami.common.world.gen.blockplacer.HeucheraBlockPlacer();

    protected BlockPlacerType<?> func_230368_a_() {
        return BlockPlacerType.DOUBLE_PLANT;
    }

    public void func_225567_a_(IWorld p_225567_1_, BlockPos p_225567_2_, BlockState p_225567_3_, Random p_225567_4_) {
        if (p_225567_3_.getBlock() == HanamiBlocks.TALL_HEUCHERA.get()) {
            ((DoublePlantBlock) p_225567_3_.getBlock()).placeAt(p_225567_1_, p_225567_2_, 2);
        } else {
            p_225567_1_.setBlockState(p_225567_2_, p_225567_3_, 2);
        }
    }

    static {
        field_236443_b_ = Codec.unit(() -> {
            return field_236444_c_;
        });
    }
}