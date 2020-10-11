package com.teamaurora.hanami.common.block;

import com.teamabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public class AzaleaBushBlock extends AbnormalsFlowerBlock {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    private final Supplier<Effect> stewEffect;

    public AzaleaBushBlock(Supplier<Effect> effect, int duration, Properties properties) {
        super(Effects.BLINDNESS, duration, properties);
        this.stewEffect = effect;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Vector3d vector3d = state.getOffset(worldIn, pos);
        return SHAPE.withOffset(vector3d.x, vector3d.y, vector3d.z);
    }

    public Effect getStewEffect() {
        return this.stewEffect.get();
    }
}
