package com.bono.api;

import java.io.*;
import java.util.Properties;

/**
 * Created by hendriknieuwenhuis on 17/02/16.
 */
public class Config {

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
            File file = new File("server.properties");
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            inputStream = null;
        }

        /* here's where the exception throw comes from. */
        if (inputStream == null) {
             // try loading from classpath.
            inputStream = getClass().getResourceAsStream("server.properties");
        }
        // try loading properties from the file (if found)
        properties.load(inputStream);

        host = properties.getProperty("HostAddress");
        port = new Integer(properties.getProperty("Port"));

    }

    public Properties loadProperties() throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = null;

        // First try loading from the current directory.
        try {
            File file = new File("server.properties");
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            inputStream = null;
        }

        /* here's where the exception throw comes from. */
        if (inputStream == null) {
            // try loading from classpath.
            inputStream = getClass().getResourceAsStream("server.properties");
        }
        // try loading properties from the file (if found)
        properties.load(inputStream);
        return properties;
    }

    public void saveParamChanges() throws Exception {
        try {
            Properties properties = new Properties();
            properties.setProperty("HostAddress", host);
            properties.setProperty("Port", Integer.toString(port));
            File file = new File("server.properties");
            OutputStream out = new FileOutputStream(file);
            properties.store(out, "properties");
        } catch (Exception e) {
            throw e;
        }
    }

    public void saveParamChanges(Properties properties) throws Exception {
        try {
            File file = new File("server.properties");
            OutputStream out = new FileOutputStream(file);
            properties.store(out, "properties");
        } catch (Exception e) {
            throw e;
        }
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
