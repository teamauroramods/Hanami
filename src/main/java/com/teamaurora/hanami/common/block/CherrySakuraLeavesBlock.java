package com.teamaurora.hanami.common.block;

import com.teamabnormals.abnormals_core.common.blocks.wood.AbnormalsLeavesBlock;
import com.teamaurora.hanami.client.particle.HanamiParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;
import java.util.function.Supplier;

public class CherrySakuraLeavesBlock extends AbnormalsLeavesBlock {
    private final LeavesBlock leavesBlock;
    private final Supplier<Item> fruitItem;

    public CherrySakuraLeavesBlock(Properties properties, LeavesBlock leaves, Supplier<Item> fruit) {
        super(properties);
        leavesBlock = leaves;
        fruitItem = fruit;
    }

    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        spawnAsEntity(world, pos, new ItemStack(fruitItem.get(), 1));

        world.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + world.rand.nextFloat() * 0.4F);
        world.setBlockState(pos, leavesBlock.getDefaultState().with(LeavesBlock.PERSISTENT, state.get(LeavesBlock.PERSISTENT)).with(LeavesBlock.DISTANCE, state.get(LeavesBlock.DISTANCE)), 2);

        return ActionResultType.func_233537_a_(world.isRemote);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState blockState, World world, BlockPos pos, Random rand) {
        super.animateTick(blockState, world, pos, rand);
        int particleChance = 60;

        if (world.isRaining() || world.isThundering()) particleChance -= 15;
        if (world.getCurrentMoonPhaseFactor() == 1.0) particleChance -= 30;

        if (rand.nextInt(particleChance) == 0) {
            BlockPos blockpos = pos.down();

            if (world.isAirBlock(blockpos)) {
                double x = (float)pos.getX() + rand.nextFloat();
                double y = (float)pos.getY() - 0.05D;
                double z = (float)pos.getZ() + rand.nextFloat();
                double wind = 0;

                if (world.isRaining() || world.isThundering()) wind += 0.05F;
                if (world.getCurrentMoonPhaseFactor() == 1.0) wind += 0.1F;

                world.addParticle(HanamiParticles.BLOSSOM.get(), x, y, z, wind, 0.0F, 0.0F);
            }
        }
    }
}
