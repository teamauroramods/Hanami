package com.teamaurora.hanami.common.item;

import com.teamaurora.hanami.core.registry.HanamiEffects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class CherryKombuchaItem extends Item {
    public CherryKombuchaItem(Item.Properties properties) {
        super(properties);
    }

    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity livingEntity) {
        super.onItemUseFinish(stack, world, livingEntity);
        if (livingEntity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)livingEntity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
            serverplayerentity.addStat(Stats.ITEM_USED.get(this));
        }

        if (livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;

            if (player.isPotionActive(HanamiEffects.INSTABILITY.get())) {
                int modifier = player.getActivePotionEffect(HanamiEffects.INSTABILITY.get()).getAmplifier();
                player.addPotionEffect(new EffectInstance(HanamiEffects.INSTABILITY.get(), 1200, Math.min(modifier + 1, 2), false, false, true));
            } else {
                player.addPotionEffect(new EffectInstance(HanamiEffects.INSTABILITY.get(), 1200, 0, false, false, true));
            }
        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (livingEntity instanceof PlayerEntity && !((PlayerEntity)livingEntity).abilities.isCreativeMode) {
                ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
                PlayerEntity playerentity = (PlayerEntity)livingEntity;
                if (!playerentity.inventory.addItemStackToInventory(itemstack)) {
                    playerentity.dropItem(itemstack, false);
                }
            }

            return stack;
        }
    }

    public UseAction getUseAction(ItemStack itemStack) {
        return UseAction.DRINK;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }
}
