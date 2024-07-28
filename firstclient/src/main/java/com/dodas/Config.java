package com.dodas;

import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Config {
    static Config conf;
    static Properties configProp;

    public static Config getConfigManager() {
        if (conf == null) {
            conf = new Config();
        }
        return conf;
    }

    public void loadConfig() {
         try {
            if (configProp == null) {
                configProp = new Properties();
                InputStream configInputStream = this.getClass().getResourceAsStream("/app.properties");
                configProp.load(configInputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("IP do servidor: ");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String port = scanner.nextLine();
        setConfigProp("server.ip", port);
    }

    public String getConfigProp(String key) {return configProp.getProperty(key);}

    public void setConfigProp(String key, String value) {configProp.setProperty(key, value);}
}
