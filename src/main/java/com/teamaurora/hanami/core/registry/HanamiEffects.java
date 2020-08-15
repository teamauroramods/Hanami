package com.teamaurora.hanami.core.registry;

import com.teamaurora.hanami.common.potion.InstabilityEffect;
import com.teamaurora.hanami.common.potion.NourishingEffect;
import com.teamaurora.hanami.common.potion.UnsalutaryEffect;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Hanami.MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Hanami.MODID);

    public static RegistryObject<Effect> INSTABILITY = EFFECTS.register("instability", InstabilityEffect::new);
    public static RegistryObject<Effect> NOURISHING = EFFECTS.register("nourishing", NourishingEffect::new);
    public static RegistryObject<Effect> UNSALUTARY = EFFECTS.register("unsalutary", UnsalutaryEffect::new);

    public static final RegistryObject<Potion> INSTABILITY_NORMAL = POTIONS.register("instability", ()->new Potion(new EffectInstance(INSTABILITY.get(), 1800)));
    public static final RegistryObject<Potion> INSTABILITY_STRONG = POTIONS.register("instability_strong", ()->new Potion(new EffectInstance(INSTABILITY.get(), 900, 1)));
    public static final RegistryObject<Potion> INSTABILITY_LONG = POTIONS.register("instability_long", ()->new Potion(new EffectInstance(INSTABILITY.get(), 4800)));

    public static void registerBrewingRecipes() {
        PotionBrewing.addMix(Potions.AWKWARD, HanamiBlocks.BLOMB.get().asItem(), INSTABILITY_NORMAL.get());
        PotionBrewing.addMix(INSTABILITY_NORMAL.get(), Items.GLOWSTONE_DUST, INSTABILITY_STRONG.get());
        PotionBrewing.addMix(INSTABILITY_NORMAL.get(), Items.REDSTONE, INSTABILITY_LONG.get());
    }
}
