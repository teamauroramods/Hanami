package com.teamaurora.hanami.core.other;

import com.teamaurora.hanami.core.Hanami;
import com.teamaurora.hanami.core.registry.HanamiEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hanami.MODID)
public class HanamiEvents {

    @SubscribeEvent
    public static void livingKnockBack(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntityLiving();

        // INSTABILITY //
        if (entity.isPotionActive(HanamiEffects.INSTABILITY.get())) {
            int amplifier = entity.getActivePotionEffect(HanamiEffects.INSTABILITY.get()).getAmplifier();
            event.setStrength(event.getStrength() * (2 + amplifier));
        }
    }

    @SubscribeEvent
    public static void livingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        ItemStack item = event.getItem();

        LivingEntity entity = event.getEntityLiving();

        // NOURISHING //
        if (entity.isPotionActive(HanamiEffects.NOURISHING.get())) {
            int amplifier = entity.getActivePotionEffect(HanamiEffects.NOURISHING.get()).getAmplifier();
            if (item.isFood()) {
                int foodToAdd = 2 * (amplifier + 1);
                if (entity instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) entity;
                    player.getFoodStats().addStats(foodToAdd, 0);
                }
            }
        }

        // UNSALUTARY //
        if (entity.isPotionActive(HanamiEffects.UNSALUTARY.get())) {
            int amplifier = entity.getActivePotionEffect(HanamiEffects.UNSALUTARY.get()).getAmplifier();
            if (item.isFood()) {
                Food food = item.getItem().getFood();
                int foodToRemove = Math.min(2 * (amplifier + 1), food.getHealing());
                if (entity instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) entity;
                    player.getFoodStats().addStats(foodToRemove * -1, 0);
                }
            }
        }
    }
}
