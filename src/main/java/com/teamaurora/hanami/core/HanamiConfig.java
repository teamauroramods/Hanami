package com.teamaurora.hanami.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class HanamiConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Integer> sakuraForestWeight;
        public final ForgeConfigSpec.ConfigValue<Integer> sakuraRollingHillsWeight;
        public final ForgeConfigSpec.ConfigValue<Integer> sakuraValleyWeight;

        Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Common configurations for Atmospheric")
            .push("common");

            builder.comment("Values for biome frequencies; lower = more rare. (Requires restart)")
            .push("biome_weights");

            sakuraForestWeight = builder.define("Sakura Forest height", 3);
            sakuraRollingHillsWeight = builder.define("Sakura Rolling Hills weight", 1);
            sakuraValleyWeight = builder.define("Sakura Valley weight", 1);

            builder.pop();
            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
