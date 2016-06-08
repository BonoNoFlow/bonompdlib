package com.bono.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 01/03/16.
 *
 * Class Status holds the various stats of the staus
 * of the server.
 *
 * Status varies in type of stats. So listeners
 * should always check whether a stat is null or not!
 */
public class Status {

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

    private static final String VOLUME          = "volume";
    private static final String REPEAT          = "repeat";
    private static final String RANDOM          = "random";
    private static final String SINGLE          = "single";
    private static final String CONSUME         = "consume";
    private static final String PLAYLIST        = "playlist";
    private static final String PLAYLISTLENGTH  = "playlistlength";
    private static final String MIXRAMPDB       = "mixrampdb";
    private static final String STATE           = "state";
    private static final String SONG            = "song";
    private static final String SONGID          = "songid";
    private static final String TIME            = "time";
    private static final String ELAPSED         = "elapsed";
    private static final String BITRATE         = "bitrate";
    private static final String AUDIO           = "audio";
    private static final String NEXTSONG        = "nextsong";
    private static final String NEXTSONGID      = "nextsongid";

    private String volume;
    private String repeat;
    private String random;
    private String single;
    private String consume;
    private String playlist;
    private String playlistlength;
    private String mixrampdb;
    private String state;
    private String song;
    private String songid;
    private String time;
    private String elapsed;
    private String bitrate;
    private String audio;
    private String nextsong;
    private String nextsongid;

    private List<ChangeListener> listeners = new ArrayList<>();

    public void populateStatus(String entry) {
        clear();

        Reply reply = new Reply(entry);
        Iterator<String> i = reply.iterator();
        while (i.hasNext()) {
            String[] state = i.next().split(Reply.SPLIT_LINE);

            switch (state[0]) {
                case Status.VOLUME:
                    setVolume(state[1]);
                    break;
                case Status.REPEAT:
                    setRepeat(state[1]);
                    break;
                case Status.RANDOM:
                    setRandom(state[1]);
                    break;
                case Status.SINGLE:
                    setSingle(state[1]);
                    break;
                case Status.CONSUME:
                    setConsume(state[1]);
                    break;
                case Status.PLAYLIST:
                    setPlaylist(state[1]);
                    break;
                case Status.PLAYLISTLENGTH:
                    setPlaylistlength(state[1]);
                    break;
                case Status.MIXRAMPDB:
                    setMixrampdb(state[1]);
                    break;
                case Status.STATE:
                    setState(state[1]);
                    break;
                case Status.SONG:
                    setSong(state[1]);
                    break;
                case Status.SONGID:
                    setSongid(state[1]);
                    break;
                case Status.TIME:
                    setTime(state[1]);
                    break;
                case Status.ELAPSED:
                    setElapsed(state[1]);
                    break;
                case Status.BITRATE:
                    setBitrate(state[1]);
                    break;
                case Status.AUDIO:
                    setAudio(state[1]);
                    break;
                case Status.NEXTSONG:
                    setNextsong(state[1]);
                    break;
                case Status.NEXTSONGID:
                    setNextsongid(state[1]);
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

    protected void clear() {
        setVolume(null);
        setRepeat(null);
        setRandom(null);
        setSingle(null);
        setConsume(null);
        setPlaylist(null);
        setPlaylistlength(null);
        setMixrampdb(null);
        setState(null);
        setSong(null);
        setSongid(null);
        setTime(null);
        setElapsed(null);
        setBitrate(null);
        setAudio(null);
        setNextsong(null);
        setNextsongid(null);
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public String getPlaylistlength() {
        return playlistlength;
    }

    public void setPlaylistlength(String playlistlength) {
        this.playlistlength = playlistlength;
    }

    public String getMixrampdb() {
        return mixrampdb;
    }

    public void setMixrampdb(String mixrampdb) {
        this.mixrampdb = mixrampdb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getElapsed() {
        return elapsed;
    }

    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getNextsong() {
        return nextsong;
    }

    public void setNextsong(String nextsong) {
        this.nextsong = nextsong;
    }

    public String getNextsongid() {
        return nextsongid;
    }

    public void setNextsongid(String nextsongid) {
        this.nextsongid = nextsongid;
    }

    @Override
    public String toString() {
        return "Status{" +
                "volume='" + volume + '\'' +
                ", repeat='" + repeat + '\'' +
                ", random='" + random + '\'' +
                ", single='" + single + '\'' +
                ", consume='" + consume + '\'' +
                ", playlist='" + playlist + '\'' +
                ", playlistlength='" + playlistlength + '\'' +
                ", mixrampdb='" + mixrampdb + '\'' +
                ", state='" + state + '\'' +
                ", song='" + song + '\'' +
                ", songid='" + songid + '\'' +
                ", time='" + time + '\'' +
                ", elapsed='" + elapsed + '\'' +
                ", bitrate='" + bitrate + '\'' +
                ", audio='" + audio + '\'' +
                ", nextsong='" + nextsong + '\'' +
                ", nextsongid='" + nextsongid + '\'' +
                '}';
    }
}
