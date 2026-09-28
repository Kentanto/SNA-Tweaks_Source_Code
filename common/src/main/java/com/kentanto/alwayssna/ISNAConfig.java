package com.kentanto.alwayssna;

public interface ISNAConfig {
    boolean alwaysOn();
    void alwaysOn(boolean value);
    void alwaysOnSave();

    boolean useCustomRate();
    void useCustomRate(boolean value);
    void useCustomRateSave();

    double customRate();
    void customRate(double value);
    void customRateSave();
}
