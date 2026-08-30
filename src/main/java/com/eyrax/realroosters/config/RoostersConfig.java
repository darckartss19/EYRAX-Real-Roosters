package com.eyrax.realroosters.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class RoostersConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue NATURAL_SPAWNING = BUILDER
            .comment("Allow heritage chickens to spawn naturally in suitable overworld biomes.")
            .define("naturalSpawning", true);

    public static final ModConfigSpec.BooleanValue ROOSTER_COMBAT = BUILDER
            .comment("Allow adult male heritage chickens to defend themselves and their owner.")
            .define("roosterCombat", true);

    public static final ModConfigSpec.IntValue MIN_EGG_TICKS = BUILDER
            .comment("Minimum interval between eggs laid by an adult hen.")
            .defineInRange("minimumEggTicks", 6000, 1200, 48000);

    public static final ModConfigSpec.IntValue MAX_EGG_TICKS = BUILDER
            .comment("Maximum interval between eggs laid by an adult hen.")
            .defineInRange("maximumEggTicks", 12000, 1200, 72000);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private RoostersConfig() {}
}
