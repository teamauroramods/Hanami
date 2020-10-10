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

public class SakuraFlowerItem extends Item {
    public SakuraFlowerItem(Item.Properties builder) {
        super(builder);
    }

    // VVV - Not to worry, this code you "borrowed" is already borrowed from vanilla.

    // admittedly this code was basically copied from Atmospheric's code. I don't think there's really any better way to do this anyway though lol
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
        ItemStack itemStack = player.getHeldItem(handIn);
        world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {
            ThrownSakuraBlossomEntity blossom = new ThrownSakuraBlossomEntity(world, player);
            blossom.setItem(itemStack);
            blossom.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.addEntity(blossom);
        }
        player.addStat(Stats.ITEM_USED.get(this));

        if (!player.abilities.isCreativeMode) {
            itemStack.shrink(1);
        }
        return ActionResult.resultSuccess(itemStack);
    }
}
