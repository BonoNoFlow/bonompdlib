package com.bono.api;

/**
 * Created by Hendrik Nieuwenhuis.
 *
 * Interface for the server values methods.
 *
 * The basic values the server has:
 *  host
 *  port
 *
 */
public interface Server {

    void setHost(String host);

    String getHost();

    void setPort(int port);

    int getPort();


}
