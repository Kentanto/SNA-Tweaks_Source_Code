package com.kentanto.alwayssna;

import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod("solarneutronactivatortweak")
public class SolarNeutronActivatorTweak {
    public static final String MODID = "solarneutronactivatortweak";

    public SolarNeutronActivatorTweak() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ForgeConfig.SPEC);
        CommonSetup.setConfig(new ForgeConfig());
        System.out.println("[SNA Tweak] Forge 1.20.1 loaded — Solar Neutron Activator will always run.");
        System.out.println("[SNA Tweak] Config loaded");

        ModLoadingContext.get().registerExtensionPoint(
            ConfigScreenHandler.ConfigScreenFactory.class,
            () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> new SNAConfigScreen(screen))
        );
    }
}
