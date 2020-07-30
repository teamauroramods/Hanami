package com.teamaurora.hanami.common.item;

import com.teamaurora.hanami.common.entity.ThrownSakuraBlossomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class SakuraBlossomItem extends Item {
    public SakuraBlossomItem(Item.Properties builder) {
        super(builder);
    }

    // admittedly this code was basically copied from Atmospheric's code. I don't think there's really any better way to do this anyway though lol
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote) {
            ThrownSakuraBlossomEntity blossom = new ThrownSakuraBlossomEntity(worldIn, playerIn);
            blossom.setItem(itemStack);
            blossom.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.addEntity(blossom);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemStack.shrink(1);
        }

        return ActionResult.resultSuccess(itemStack);
    }
}
