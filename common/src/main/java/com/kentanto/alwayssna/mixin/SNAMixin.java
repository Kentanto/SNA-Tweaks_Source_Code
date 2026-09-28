package com.kentanto.alwayssna.mixin;

import com.kentanto.alwayssna.CommonSetup;
import com.kentanto.alwayssna.ISNAConfig;
import mekanism.common.tile.machine.TileEntitySolarNeutronActivator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntitySolarNeutronActivator.class)
public abstract class SNAMixin {

    @Inject(method = "canFunction", at = @At("HEAD"), cancellable = true)
    private void alwaysCanFunction(CallbackInfoReturnable<Boolean> cir) {
        ISNAConfig cfg = CommonSetup.config();
        if (cfg != null && cfg.alwaysOn()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "recalculateProductionRate", at = @At("RETURN"), cancellable = true)
    private void modifyProductionRate(CallbackInfoReturnable<Float> cir) {
        ISNAConfig cfg = CommonSetup.config();
        if (cfg != null && cfg.useCustomRate()) {
            cir.setReturnValue(cir.getReturnValue() * (float) cfg.customRate());
        }
    }
}
