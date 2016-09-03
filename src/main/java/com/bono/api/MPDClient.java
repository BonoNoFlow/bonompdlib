package com.bono.api;

/**
 * Created by Hendrik Nieuwenhuis on 9/1/16.
 */
public class MPDClient implements Server {

    private String host = "localhost"; //
    private int port = 6600; // standard port configuration.

    private ClientExecutor clientExecutor;

    private Player player;
    private Playlist playlist;
    private Status status;

    public MPDClient() {
        this.clientExecutor = new ClientExecutor(this);
        this.player = new Player(clientExecutor);
        this.playlist = new Playlist();
        this.status = new Status();
    }

    public MPDClient(String host, int port) {
        this();
        this.host = host;
        this.port = port;
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
