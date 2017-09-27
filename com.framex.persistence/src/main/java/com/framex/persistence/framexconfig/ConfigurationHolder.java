package com.framex.persistence.framexconfig;

/**
 * @author lijie
 * @date 2017/9/27 15:37
 * @description
 */
public class ConfigurationHolder {
    private static Configuration configuration;

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(Configuration configuration) {
        ConfigurationHolder.configuration = configuration;
    }
}
