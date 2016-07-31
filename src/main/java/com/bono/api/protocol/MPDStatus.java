package com.bono.api.protocol;

/**
 * Created by bono on 7/31/16.
 */
public class MPDStatus {

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
}
