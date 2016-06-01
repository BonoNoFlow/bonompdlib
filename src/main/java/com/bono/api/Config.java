package com.bono.api;

import java.io.*;
import java.util.Properties;

/**
 * Created by hendriknieuwenhuis on 17/02/16.
 */
public class Config {

    public static final String CONFIG_FILE = "server.properties";

    private String host;
    private int port;

    /*
    TODO werken met Properties.

     */
    protected Properties properties;

    public Config() {
        properties = new Properties();
    }

    public Config(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void loadParams() throws Exception {
        Properties properties = new Properties();
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

        host = properties.getProperty("HostAddress");
        port = new Integer(properties.getProperty("Port"));

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

    public void saveParamChanges() throws Exception {
        try {
            Properties properties = new Properties();
            properties.setProperty("HostAddress", host);
            properties.setProperty("Port", Integer.toString(port));
            File file = new File(Config.CONFIG_FILE);
            OutputStream out = new FileOutputStream(file);
            properties.store(out, "properties");
        } catch (Exception e) {
            throw e;
        }
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
