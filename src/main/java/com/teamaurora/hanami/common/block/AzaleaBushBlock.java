package com.teamaurora.hanami.common.block;

import com.teamabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

import java.util.function.Supplier;

public class AzaleaBushBlock extends AbnormalsFlowerBlock {
    private final Supplier<Effect> stewEffect;

    public AzaleaBushBlock(Supplier<Effect> effect, int stewEffectDuration, Properties properties) {
        super(Effects.BLINDNESS, stewEffectDuration, properties);
        this.stewEffect = effect;
    }

    public Effect getStewEffect() {
        return this.stewEffect.get();
    }
}
