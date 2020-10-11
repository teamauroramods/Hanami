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

    protected ItemStack dispenseStack(IBlockSource source, ItemStack itemStack) {
        this.func_239796_a_(false);
        Item item = itemStack.getItem();

        if (item instanceof SakuraFlowerItem) {
            Direction direction = source.getBlockState().get(DispenserBlock.FACING);
            World world = source.getWorld().getWorld();
            BlockPos pos = source.getBlockPos().offset(direction);

            if (!world.isRemote) {
                ThrownSakuraBlossomEntity blossom = new ThrownSakuraBlossomEntity(world, pos.getX(), pos.getY(), pos.getZ());
                blossom.setItem(itemStack);
                blossom.shoot(direction.getXOffset(), direction.getYOffset() + 0.1F, direction.getZOffset(), 1.1F, 6.0F);

                world.addEntity(blossom);
            }
            itemStack.shrink(1);
            this.func_239796_a_(true);
        }
        return itemStack;
    }
}
