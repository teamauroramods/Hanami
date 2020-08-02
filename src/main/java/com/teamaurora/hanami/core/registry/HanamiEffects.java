package com.teamaurora.hanami.core.registry;

import com.teamaurora.hanami.common.potion.InstabilityEffect;
import com.teamaurora.hanami.common.potion.NourishingEffect;
import com.teamaurora.hanami.common.potion.UnsalutaryEffect;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Hanami.MODID);

    public static RegistryObject<Effect> INSTABILITY = EFFECTS.register("instability", InstabilityEffect::new);
    public static RegistryObject<Effect> NOURISHING = EFFECTS.register("nourishing", NourishingEffect::new);
    public static RegistryObject<Effect> UNSALUTARY = EFFECTS.register("unsalutary", UnsalutaryEffect::new);
}
