package com.teamaurora.hanami.common.world.gen.blockstateprovider;

import com.mojang.serialization.Codec;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;

import java.util.Random;

public class HeucheraBlockStateProvider extends BlockStateProvider {
    public static final Codec<com.teamaurora.hanami.common.world.gen.blockstateprovider.HeucheraBlockStateProvider> field_236804_b_;
    public static final com.teamaurora.hanami.common.world.gen.blockstateprovider.HeucheraBlockStateProvider field_236805_c_ = new com.teamaurora.hanami.common.world.gen.blockstateprovider.HeucheraBlockStateProvider();

    protected BlockStateProviderType<?> func_230377_a_() {
        return BlockStateProviderType.PLAIN_FLOWER_PROVIDER;
    }

    public BlockState getBlockState(Random randomIn, BlockPos blockPosIn) {
        return randomIn.nextInt(3) > 0 ? HanamiBlocks.HEUCHERA.get().getDefaultState() : HanamiBlocks.TALL_HEUCHERA.get().getDefaultState();
    }

    static {
        field_236804_b_ = Codec.unit(() -> {
            return field_236805_c_;
        });
    }
}
