package com.bono.api;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.bono.api.Status.RANDOM;

/**
 * Created by hendriknieuwenhuis on 02/03/16.
 */
public class MPDStatus extends Exec {

    // Clears the current error message in status,
    // this is also accomplished by any command
    // that starts playback.
    public static final String CLEARERROR = "clearerror";

    public String clearerror() throws Exception {
        return execCommand(CLEARERROR);
    }

    // Display the song info of the current song.
    public static final String CURRENTSONG = "currentsong";

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
    public static final String IDLE = "idle";

    public String idle(String subsystem) throws Exception {
        return execSingleArgCommand(IDLE, subsystem);
    }

    // Reports the current status of the player
    public static final String STATUS = "status";

    public String status() throws Exception {
        return execCommand(STATUS);
    }

    // Displays the statistics
    public static final String STATS = "stats";

    public String stats() throws Exception {
        return execCommand(STATS);
    }

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

    private List<ChangeListener> listeners = new ArrayList<>();

    private Status status;


    public MPDStatus(DBExecutor dbExecutor) {
        super(dbExecutor);
        status = new Status();
    }

    public void populateStatus(String entry) {
        status.clear();

        Reply reply = new Reply(entry);
        Iterator<String> i = reply.iterator();
        while (i.hasNext()) {
            String[] state = i.next().split(Reply.SPLIT_LINE);

            switch (state[0]) {
                case Status.VOLUME:
                    status.setVolume(state[1]);
                    break;
                case Status.REPEAT:
                    status.setRepeat(state[1]);
                    break;
                case Status.RANDOM:
                    status.setRandom(state[1]);
                    break;
                case Status.SINGLE:
                    status.setSingle(state[1]);
                    break;
                case Status.CONSUME:
                    status.setConsume(state[1]);
                    break;
                case Status.PLAYLIST:
                    status.setPlaylist(state[1]);
                    break;
                case Status.PLAYLISTLENGTH:
                    status.setPlaylistlength(state[1]);
                    break;
                case Status.MIXRAMPDB:
                    status.setMixrampdb(state[1]);
                    break;
                case Status.STATE:
                    status.setState(state[1]);
                    break;
                case Status.SONG:
                    status.setSong(state[1]);
                    break;
                case Status.SONGID:
                    status.setSongid(state[1]);
                    break;
                case Status.TIME:
                    status.setTime(state[1]);
                    break;
                case Status.ELAPSED:
                    status.setElapsed(state[1]);
                    break;
                case Status.BITRATE:
                    status.setBitrate(state[1]);
                    break;
                case Status.AUDIO:
                    status.setAudio(state[1]);
                    break;
                case Status.NEXTSONG:
                    status.setNextsong(state[1]);
                    break;
                case Status.NEXTSONGID:
                    status.setNextsongid(state[1]);
                    break;
                default:
                    //System.out.println("Not a status property: " + state[0]);
                    break;
            }

        }
        fireListeners();
    }



    private void fireListeners() {
        Iterator i = listeners.iterator();
        while (i.hasNext()) {
            ((ChangeListener) i.next()).stateChanged(new ChangeEvent(this));
        }
    }

    public void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /*
    Listens to the idle object. stateChanged is triggered every
    time the status is written.
     */
    /*
    @Override
    public void stateChanged(ChangeEvent e) {
        String reply = "";

        try {
            reply = dbExecutor.execute(new MPDCommand("status"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setStatus(reply);
    }*/
}
