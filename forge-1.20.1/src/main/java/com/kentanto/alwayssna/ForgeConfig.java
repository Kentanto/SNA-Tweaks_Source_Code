package com.kentanto.alwayssna;

import net.minecraftforge.common.ForgeConfigSpec;

public class ForgeConfig implements ISNAConfig {
    private static ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ALWAYS_ON = BUILDER
            .comment("If true, Solar Neutron Activator ignores sunlight requirement.")
            .define("alwaysOn", true);

    public static final ForgeConfigSpec.BooleanValue USE_CUSTOM_RATE = BUILDER
            .comment("If true, use a custom production rate instead of Mekanism default.")
            .define("useCustomRate", false);

    public static final ForgeConfigSpec.DoubleValue CUSTOM_RATE = BUILDER
            .comment("Custom Tritium production rate multiplier. 1.0 = normal Mekanism rate.")
            .defineInRange("customRateMultiplier", 1.0, 0.0, 100.0);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    @Override
    public boolean alwaysOn() { return ALWAYS_ON.get(); }
    @Override
    public void alwaysOn(boolean value) { ALWAYS_ON.set(value); }
    @Override
    public void alwaysOnSave() { ALWAYS_ON.save(); }

    @Override
    public boolean useCustomRate() { return USE_CUSTOM_RATE.get(); }
    @Override
    public void useCustomRate(boolean value) { USE_CUSTOM_RATE.set(value); }
    @Override
    public void useCustomRateSave() { USE_CUSTOM_RATE.save(); }

    @Override
    public double customRate() { return CUSTOM_RATE.get(); }
    @Override
    public void customRate(double value) { CUSTOM_RATE.set(value); }
    @Override
    public void customRateSave() { CUSTOM_RATE.save(); }
}
