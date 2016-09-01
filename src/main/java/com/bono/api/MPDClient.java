package com.bono.api;

/**
 * Created by bono on 9/1/16.
 */
public class MPDClient implements Server {

    private String host = "localhost"; //
    private int port = 6600; // standard port configuration.

    private Player player;
    private Playlist playlist;
    private Status status;

    public MPDClient() {
        this.player = new Player();
        this.playlist = new Playlist();
        this.status = new Status();
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public int getPort() {
        return port;
    }
}
