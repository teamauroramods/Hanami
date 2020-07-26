package com.teamaurora.hanami.core.registry;

import com.teamaurora.hanami.core.Hanami;
import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("deprecation")
public class HanamiFoods {
    public static final Food CHERRIES = new Food.Builder().hunger(4).saturation(0.6F).build();

    public static final Food CHERRY_JUICE = new Food.Builder().hunger(2).saturation(0.1F).effect(() -> new EffectInstance(HanamiEffects.NOURISHING.get(), 800, 0, false, false, true), 1.0F).setAlwaysEdible().build();
    public static final Food CHERRY_KOMBUCHA = new Food.Builder().hunger(2).saturation(0.1F).effect(() -> new EffectInstance(HanamiEffects.INSTABILITY.get(), 800, 0, false, false, true), 1.0F).setAlwaysEdible().build();
}
