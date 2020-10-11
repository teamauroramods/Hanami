package com.teamaurora.hanami.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class DrinkItem extends Item {
    public DrinkItem(Item.Properties properties) {
        super(properties);
    }

    public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity livingEntity) {
        super.onItemUseFinish(itemStack, world, livingEntity);
        if (livingEntity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)livingEntity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, itemStack);
            serverplayerentity.addStat(Stats.ITEM_USED.get(this));
        }

        if (itemStack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (livingEntity instanceof PlayerEntity && !((PlayerEntity)livingEntity).abilities.isCreativeMode) {
                ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
                PlayerEntity playerentity = (PlayerEntity)livingEntity;

                if (!playerentity.inventory.addItemStackToInventory(bottle)) {
                    playerentity.dropItem(bottle, false);
                }
            }
            return itemStack;
        }
    }

    public UseAction getUseAction(ItemStack itemStack) {
        return UseAction.DRINK;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }
}
