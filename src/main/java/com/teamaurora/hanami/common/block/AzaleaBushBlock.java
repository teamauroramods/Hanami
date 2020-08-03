package com.teamaurora.hanami.common.block;

import com.teamabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public class AzaleaBushBlock extends AbnormalsFlowerBlock {
    private final Supplier<Effect> stewEffect;

    public AzaleaBushBlock(Supplier<Effect> effect, int stewEffectDuration, Properties properties) {
        super(Effects.BLINDNESS, stewEffectDuration, properties);
        this.stewEffect = effect;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() == HanamiBlocks.COMPACT_AZALEA.get() || super.isValidGround(state, worldIn, pos);
    }

    public Effect getStewEffect() {
        return this.stewEffect.get();
    }
}
