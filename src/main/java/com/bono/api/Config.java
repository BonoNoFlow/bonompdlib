package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 22/03/16.
 */
public class Config {

    private String host;
    private int port;

    public Config(String host, int port) {
        this.host = host;
        this.port = port;
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
