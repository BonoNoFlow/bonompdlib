package com.bono.api;


/**
 * Created by hendriknieuwenhuis on 02/03/16.
 */
@Deprecated
public class StatusControl extends Exec {

    public static final String CLEARERROR = "clearerror";

    public static final String CURRENTSONG = "currentsong";

    public static final String IDLE = "idle";

    public static final String STATUS = "status";

    public static final String STATS = "stats";

    /*
     The subsystems of the idle command.
     These can be given as argument so
     'idle' only listens to one of these
     changes.
    */
    // The song database
    public static final String IDLE_DATABASE = "database";

    // A Database update
    public static final String IDLE_UPDATE = "update";

    // A stored play list modification
    public static final String IDLE_STORED_PLAYLIST = "stored_playlist";

    // Current playlist modification
    public static final String IDLE_PLAYLIST = "playlist";

    // Player started, stopped or seeked.
    public static final String IDLE_PLAYER = "player";

    // Volume change
    public static final String IDLE_VOLUME = "volume";

    // Audio output enabled / disabled change
    public static final String IDLE_OUTPUT = "output";

    // Options change
    public static final String IDLE_OPTIONS = "options";

    // Modification in sticker database
    public static final String IDLE_STICKER = "sticker";

    // client subscribed or unsubscribe changes
    public static final String IDLE_SUBSCRIPTION = "subscription";

    // A message is recieved this client is
    // subscribed to.
    public static final String IDLE_MESSAGE = "message";


    public StatusControl(DBExecutor dbExecutor) {
        super(dbExecutor);

    }

    // Clears the current error message in status,
    // this is also accomplished by any command
    // that starts playback.
    public String clearerror() throws Exception {
        return execCommand(CLEARERROR);
    }

    // Display the song info of the current song.
    public String currentsong() throws Exception {
        return execCommand(CURRENTSONG);
    }

    // Wait, keep connection bind, until a subsystem is changed.
    // Response is the subsystem that is changed. After response the
    // connection is closed.
    // A subsystem can also be given as argument, then only a response
    // will follow after that subsystem is changed.
    // Example:
    // idle [idleSubsystem],
    //
    // idle
    //
    public String idle(String subsystem) throws Exception {
        if (subsystem == null) {
            return execCommand(IDLE);
        }
        return execCommand(IDLE, subsystem);
    }

    // Reports the current status of the player
    public String status() throws Exception {
        return execCommand(STATUS);
    }

    // Displays the statistics
    public String stats() throws Exception {
        return execCommand(STATS);
    }

}
