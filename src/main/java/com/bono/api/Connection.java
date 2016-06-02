package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 02/06/16.
 */
public class Connection {

    public static final String CLASS_NAME = "connection";

    public static final String HOST = "host";
    public static final String PORT  = "port";

    private String host;
    private int port;

    public Connection(String host, int port) {
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
