package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 03/04/16.
 */
public class Player extends Exec {

    public static final String NEXT = "next";
    public static final String PAUSE = "pause";
    public static final String PLAY = "play";
    public static final String PLAYID = "playid";
    public static final String PREVIOUS = "previous";
    public static final String SEEK = "seek";
    public static final String SEEKID = "seekid";
    public static final String SEEKCUR = "seekcur";
    public static final String STOP = "stop";

    public Player(DBExecutor dbExecutor) {
        super(dbExecutor);
    }

    public String next() throws Exception {
        return execCommand(NEXT);
     }

    public String pause(String value) throws Exception {
        return execCommand(PAUSE, value);
    }

    public String play() throws Exception {
        return execCommand(PLAY);
    }

    public String playid(String value) throws Exception {
        return execCommand(PLAYID, value);
    }

    public String previous() throws Exception {
        return execCommand(PREVIOUS);
    }

    public String seek(String value) throws Exception {
        return execCommand(SEEK, value);
    }

    public String seekid(String value) throws Exception {
        return execCommand(SEEKID, value);
    }

    public String seekcur(String value) throws Exception {
        return execCommand(SEEKCUR, value);
    }

    public String stop() throws Exception {
        return execCommand(STOP);
    }
}
