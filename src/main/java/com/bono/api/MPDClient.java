package com.bono.api;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Hendrik Nieuwenhuis on 9/1/16.
 */
public class MPDClient implements Server {

    private String host = ""; //
    private int port = 6600; // standard port configuration.

    private ClientExecutor clientExecutor;

    private Player player;
    private Playlist playlist;
    private Status status;

    private ServerMonitor serverMonitor;

    public MPDClient() {
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.clientExecutor = new ClientExecutor(this);
        this.player = new Player(clientExecutor);
        this.playlist = new Playlist(clientExecutor);
        this.status = new Status();
        serverMonitor = new ServerMonitor(this);
    }

    public MPDClient(String host, int port) {
        this();
        this.host = host;
        this.port = port;
    }

    public void initMonitor() throws IOException {
        serverMonitor.initMonitor();
    }

    public void runMonitor() {
        serverMonitor.start();
    }

    public ClientExecutor getClientExecutor() {
        return clientExecutor;
    }

    public String getVersion() throws IOException {
        return clientExecutor.testConnection();
    }

    public ServerMonitor getServerMonitor() {
        return serverMonitor;
    }

    public Player getPlayer() {
        return player;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public Status getStatus() {
        return status;
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
