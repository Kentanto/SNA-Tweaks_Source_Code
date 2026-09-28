package com.kentanto.alwayssna;

public class CommonSetup {
    private static ISNAConfig config;

    public static void setConfig(ISNAConfig cfg) {
        config = cfg;
    }

    public static ISNAConfig config() {
        return config;
    }
}
