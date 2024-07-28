package com.dodas.firstapi.util;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    static Properties configProp;
    static Config config;

    public static Config getConfigManager() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public void loadConfig() {
        try {
            if (configProp == null) {
                configProp = new Properties();
                InputStream configInputStream = this.getClass().getResourceAsStream("/application.properties");
                configProp.load(configInputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getConfigProp(String key) {return configProp.getProperty(key);}

    public void setConfigProp(String key, String value) {configProp.setProperty(key, value);}
}
