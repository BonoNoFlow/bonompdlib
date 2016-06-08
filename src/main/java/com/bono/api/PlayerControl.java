package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 03/04/16.
 */
@Deprecated
public class PlayerControl extends Exec {

    public static final String NEXT = "next";
    public static final String PAUSE = "pause";
    public static final String PLAY = "play";
    public static final String PLAYID = "playid";
    public static final String PREVIOUS = "previous";
    public static final String SEEK = "seek";
    public static final String SEEKID = "seekid";
    public static final String SEEKCUR = "seekcur";
    public static final String STOP = "stop";

    public PlayerControl(DBExecutor dbExecutor) {
        super(dbExecutor);
    }

    public String next() throws Exception {
        return execCommand(NEXT);
     }

    /*
    pause [{0} | {1}]

    pause 0: continue.
    pause 1: pause.

    @param String value, cannot be null!
     */
    public String pause(String value) throws Exception {
        if (value == null) {
            throw new NullPointerException("String value cannot be null!");
        }
        return execCommand(PAUSE, value);
    }

    public String play() throws Exception {
        return execCommand(PLAY);
    }

    /*
    playid {songid}

    @param String songid, cannot be null!
     */
    public String playid(String songid) throws Exception {
        if (songid == null) {
            throw new NullPointerException("String songid cannot be null!");
        }
        return execCommand(PLAYID, songid);
    }

    public String previous() throws Exception {
        return execCommand(PREVIOUS);
    }

    public String seek(String value) throws Exception {
        if (value == null) {
            throw new NullPointerException("String value, cannot be null!");
        }
        return execCommand(SEEK, value);
    }

    public String seekid(String songid) throws Exception {
        if (songid == null) {
            throw new NullPointerException("String songid, cannot be null!");
        }
        return execCommand(SEEKID, songid);
    }

    public String seekcur(String value) throws Exception {
        if (value == null) {
            throw new NullPointerException("String value, cannot be null!");
        }
        return execCommand(SEEKCUR, value);
    }

    public String stop() throws Exception {
        return execCommand(STOP);
    }
}
