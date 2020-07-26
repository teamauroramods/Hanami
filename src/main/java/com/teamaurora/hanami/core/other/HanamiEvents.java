package com.teamaurora.hanami.core.other;

import com.teamaurora.hanami.core.Hanami;
import com.teamaurora.hanami.core.registry.HanamiEffects;
import net.minecraft.entity.LivingEntity;
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
            event.setStrength(event.getStrength() * (1 + 0.5F * (amplifier + 1)));
        }
    }

    @SubscribeEvent
    public static void livingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        ItemStack item = event.getItem();

        LivingEntity entity = event.getEntityLiving();

        // NOURISHING //
        if (event.getEntityLiving().isPotionActive(HanamiEffects.NOURISHING.get())) {
            int amplifier =
        }
    }
}
