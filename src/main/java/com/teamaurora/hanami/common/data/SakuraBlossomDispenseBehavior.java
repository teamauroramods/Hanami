package com.teamaurora.hanami.common.data;

import com.teamaurora.hanami.common.entity.ThrownSakuraBlossomEntity;
import com.teamaurora.hanami.common.item.SakuraFlowerItem;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SakuraBlossomDispenseBehavior extends OptionalDispenseBehavior {
    @SuppressWarnings("deprecation")
    protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
        this.func_239796_a_(false);
        Item item = stack.getItem();
        if (item instanceof SakuraFlowerItem) {
            Direction direction = source.getBlockState().get(DispenserBlock.FACING);
            World worldIn = source.getWorld().getWorld();
            BlockPos pos = source.getBlockPos().offset(direction);
            if (!worldIn.isRemote) {
                ThrownSakuraBlossomEntity blossom = new ThrownSakuraBlossomEntity(worldIn, pos.getX(), pos.getY(), pos.getZ());
                blossom.setItem(stack);
                blossom.shoot((double)direction.getXOffset(), (double)((float)direction.getYOffset() + 0.1F), (double)direction.getZOffset(), 1.1F, 6.0F);

                worldIn.addEntity(blossom);
            }
            stack.shrink(1);
            this.func_239796_a_(true);
        }
        return stack;
    }
}
