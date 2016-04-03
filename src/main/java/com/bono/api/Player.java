package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 03/04/16.
 */
public abstract class Player {

    public static final String NEXT = "next";
    public static final String PAUSE = "pause";
    public static final String PLAY = "play";
    public static final String PLAYID = "playid";
    public static final String PREVIOUS = "previous";
    public static final String SEEK = "seek";
    public static final String SEEKID = "seekid";
    public static final String SEEKCUR = "seekcur";
    public static final String STOP = "stop";

    private DBExecutor dbExecutor;

    public Player(DBExecutor dbExecutor) {
        this.dbExecutor = dbExecutor;
    }

    public String next() throws Exception {
        return dbExecutor.execute(new MPDCommand(NEXT));
     }

    public String pause(String value) throws Exception {
        return dbExecutor.execute(new MPDCommand(PAUSE, value));
    }

    public String play() throws Exception {
        return dbExecutor.execute(new MPDCommand(PLAY));
    }

    public String playid(String value) throws Exception {
        return dbExecutor.execute(new MPDCommand(PLAYID, value));
    }

    public String previous() throws Exception {
        return dbExecutor.execute(new MPDCommand(PREVIOUS));
    }

    public String seek(String value) throws Exception {
        return dbExecutor.execute(new MPDCommand(SEEK, value));
    }

    public String seekid(String value) throws Exception {
        return dbExecutor.execute((new MPDCommand(SEEKID, value)));
    }

    public String seekcur(String value) throws Exception {
        return dbExecutor.execute(new MPDCommand(SEEKCUR, value));
    }

    public String stop() throws Exception {
        return dbExecutor.execute(new MPDCommand(STOP));
    }
}
