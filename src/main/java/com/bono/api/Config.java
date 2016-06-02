package com.bono.api;

import java.io.*;
import java.util.Properties;

/**
 * Created by hendriknieuwenhuis on 17/02/16.
 */
public class Config {

    public static final String CONFIG_FILE = "server.properties";


    protected Properties properties;

    public Config() {
        properties = new Properties();
    }

    public void loadConfig() throws Exception {
        //properties = new Properties();
        InputStream inputStream = null;

        // First try loading from the current directory.
        try {
            File file = new File(Config.CONFIG_FILE);
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            inputStream = null;
        }

        /* here's where the exception throw comes from. */
        if (inputStream == null) {
            // try loading from classpath.
            inputStream = getClass().getResourceAsStream(Config.CONFIG_FILE);
        }
        // try loading properties from the file (if found)
        properties.load(inputStream);
    }

    public void saveConfig() throws Exception {
        try {
            File file = new File(Config.CONFIG_FILE);
            OutputStream out = new FileOutputStream(file);
            properties.store(out, "properties");
        } catch (Exception e) {
            throw e;
        }
    }

    public void setProperty(String key, String property) {
        properties.setProperty(key, property);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void clearProperties() {
        properties.clear();
    }
}
