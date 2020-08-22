package com.teamaurora.hanami.common.block;

import com.teamabnormals.abnormals_core.common.blocks.wood.AbnormalsLeavesBlock;
import com.teamaurora.hanami.client.particle.HanamiParticles;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class SakuraLeavesBlock extends AbnormalsLeavesBlock {
    public SakuraLeavesBlock(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.animateTick(stateIn, worldIn, pos, rand);
        int particleChance = 60;
        if (worldIn.isRaining() || worldIn.isThundering()) particleChance -= 15;
        if (worldIn.getCurrentMoonPhaseFactor() == 1.0) particleChance -= 30;
        if (rand.nextInt(particleChance) == 0) {
            BlockPos blockpos = pos.down();
            if (worldIn.isAirBlock(blockpos)) {
                double px = (double)((float)pos.getX() + rand.nextFloat());
                double py = (double)pos.getY() - 0.05D;
                double pz = (double)((float)pos.getZ() + rand.nextFloat());
                double wind = 0;
                if (worldIn.isRaining() || worldIn.isThundering()) wind += 0.05F;
                if (worldIn.getCurrentMoonPhaseFactor() == 1.0) wind += 0.1F;
                worldIn.addParticle(HanamiParticles.BLOSSOM.get(), px, py, pz, wind, 0.0F, 0.0F);
            }
        }
    }
}
