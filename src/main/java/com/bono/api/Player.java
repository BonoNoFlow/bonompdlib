package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 03/04/16.
 */
public abstract class Player extends Exec {

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
        return dbExecutor.execute(new DefaultCommand(NEXT));
     }

    public String pause(String value) throws Exception {
        return dbExecutor.execute(new DefaultCommand(PAUSE, value));
    }

    public String play() throws Exception {
        return dbExecutor.execute(new DefaultCommand(PLAY));
    }

    public String playid(String value) throws Exception {
        return dbExecutor.execute(new DefaultCommand(PLAYID, value));
    }

    public String previous() throws Exception {
        return dbExecutor.execute(new DefaultCommand(PREVIOUS));
    }

    public String seek(String value) throws Exception {
        return dbExecutor.execute(new DefaultCommand(SEEK, value));
    }

    public String seekid(String value) throws Exception {
        return dbExecutor.execute((new DefaultCommand(SEEKID, value)));
    }

    public String seekcur(String value) throws Exception {
        return dbExecutor.execute(new DefaultCommand(SEEKCUR, value));
    }

    public String stop() throws Exception {
        return dbExecutor.execute(new DefaultCommand(STOP));
    }
}
