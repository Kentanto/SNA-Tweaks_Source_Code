package com.kentanto.alwayssna.mixin;

import com.kentanto.alwayssna.CommonSetup;
import com.kentanto.alwayssna.ISNAConfig;
import mekanism.common.config.MekanismConfig;
import com.jerry.meklm.common.tile.machine.TileEntityLargeSolarNeutronActivator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityLargeSolarNeutronActivator.class)
public abstract class LargeSNAMixin {

    @Inject(method = "canFunction", at = @At("HEAD"), cancellable = true)
    private void alwaysCanFunction(CallbackInfoReturnable<Boolean> cir) {
        ISNAConfig cfg = CommonSetup.config();
        if (cfg != null && cfg.alwaysOn()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getPeakProductionRate", at = @At("RETURN"), cancellable = true)
    private void overridePeakRate(CallbackInfoReturnable<Float> cir) {
        ISNAConfig cfg = CommonSetup.config();
        if (cfg != null && cfg.alwaysOn()) {
            float rate = (float) MekanismConfig.general.maxSolarNeutronActivatorRate.get();
            if (cfg.useCustomRate()) {
                rate *= (float) cfg.customRate();
            }
            cir.setReturnValue(rate);
        }
    }

    @Inject(method = "recalculateProductionRate", at = @At("RETURN"), cancellable = true)
    private void modifyProductionRate(CallbackInfoReturnable<Float> cir) {
        ISNAConfig cfg = CommonSetup.config();
        if (cfg != null && cfg.alwaysOn()) {
            float rate = (float) MekanismConfig.general.maxSolarNeutronActivatorRate.get();
            if (cfg.useCustomRate()) {
                rate *= (float) cfg.customRate();
            }
            cir.setReturnValue(rate);
        } else if (cfg != null && cfg.useCustomRate()) {
            cir.setReturnValue(cir.getReturnValue() * (float) cfg.customRate());
        }
    }
}
