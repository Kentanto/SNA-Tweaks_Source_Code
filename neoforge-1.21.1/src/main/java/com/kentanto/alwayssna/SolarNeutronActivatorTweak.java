package com.kentanto.alwayssna;

import net.minecraft.client.gui.screens.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod("solarneutronactivatortweak")
public class SolarNeutronActivatorTweak {
    public static final String MODID = "solarneutronactivatortweak";

    public SolarNeutronActivatorTweak(IEventBus modEventBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, NeoConfig.SPEC);
        CommonSetup.setConfig(new NeoConfig());
        System.out.println("[SNA Tweak] NeoForge 1.21.1 loaded — Solar Neutron Activator will always run.");
        System.out.println("[SNA Tweak] Config loaded");

        modEventBus.addListener(this::onClientSetup);
    }

    @OnlyIn(Dist.CLIENT)
    private void onClientSetup(final net.neoforged.fml.event.lifecycle.FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(
            IConfigScreenFactory.class,
            () -> new IConfigScreenFactory() {
                @Override
                public Screen createScreen(ModContainer mod, Screen parent) {
                    return new SNAConfigScreen(parent);
                }
            }
        );
    }
}
