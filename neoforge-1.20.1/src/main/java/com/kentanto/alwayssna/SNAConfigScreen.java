package com.kentanto.alwayssna;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

public class SNAConfigScreen extends Screen {
    private final Screen parent;

    private Button toggleAlwaysOnButton;
    private Button toggleCustomRateButton;
    private EditBox customRateInput;

    public SNAConfigScreen(Screen parent) {
        super(Component.literal("Solar Neutron Activator Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        boolean isHost = Minecraft.getInstance().getSingleplayerServer() != null
                || Minecraft.getInstance().getConnection() == null;

        ISNAConfig cfg = CommonSetup.config();

        toggleAlwaysOnButton = addRenderableWidget(Button.builder(getAlwaysOnText(cfg), button -> {
            boolean newValue = !cfg.alwaysOn();
            cfg.alwaysOn(newValue);
            cfg.alwaysOnSave();
            button.setMessage(getAlwaysOnText(cfg));
            System.out.println("[SNA Config] Always On toggled: " + newValue);
        }).bounds(width / 2 - 100, height / 2 - 40, 200, 20).build());
        toggleAlwaysOnButton.active = isHost;

        toggleCustomRateButton = addRenderableWidget(Button.builder(getCustomRateText(cfg), button -> {
            boolean newValue = !cfg.useCustomRate();
            cfg.useCustomRate(newValue);
            cfg.useCustomRateSave();
            button.setMessage(getCustomRateText(cfg));
            customRateInput.setEditable(newValue && isHost);
            System.out.println("[SNA Config] Use Custom Rate toggled: " + newValue);
        }).bounds(width / 2 - 100, height / 2, 200, 20).build());
        toggleCustomRateButton.active = isHost;

        customRateInput = new EditBox(font, width / 2 - 100, height / 2 + 30, 200, 20,
                Component.literal("Production Rate Multiplier"));
        customRateInput.setValue(Double.toString(cfg.customRate()));
        customRateInput.setEditable(cfg.useCustomRate() && isHost);
        addRenderableWidget(customRateInput);

        addRenderableWidget(Button.builder(Component.literal("Done"), button -> onClose())
                .bounds(width / 2 - 100, height / 2 + 60, 200, 20)
                .build());

        System.out.println("[SNA Config] Initial config loaded: Always On=" + cfg.alwaysOn()
                + ", Use Custom Rate=" + cfg.useCustomRate()
                + ", Custom Rate=" + cfg.customRate());
    }

    private Component getAlwaysOnText(ISNAConfig cfg) {
        return Component.literal("Always On: " + (cfg.alwaysOn() ? "Enabled" : "Disabled"));
    }

    private Component getCustomRateText(ISNAConfig cfg) {
        return Component.literal("Use Custom Multiplier: " + (cfg.useCustomRate() ? "Enabled" : "Disabled"));
    }

    @Override
    public void tick() {
        super.tick();

        ISNAConfig cfg = CommonSetup.config();
        if (toggleCustomRateButton != null && toggleCustomRateButton.active && cfg.useCustomRate()) {
            try {
                double val = Double.parseDouble(customRateInput.getValue());
                if (val != cfg.customRate()) {
                    cfg.customRate(val);
                    cfg.customRateSave();
                    System.out.println("[SNA Config] Custom Rate changed: " + val);
                }
            } catch (NumberFormatException ignored) {
            }
        }
    }

    @Override
    public void onClose() {
        System.out.println("[SNA Config] Config GUI closed.");
        Minecraft.getInstance().setScreen(parent);
    }
}