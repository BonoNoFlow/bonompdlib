package com.bono.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 01/03/16.
 *
 * Class Status holds the various stats of the status
 * of the server.
 *
 * Status varies in type of stats. So listeners
 * should always check whether a stat is null or not!
 */
public class Status {

    public static final int PAUSE_STATE = 0;

    public static final int PLAY_STATE = 1;

    public static final int STOP_STATE = 2;

    public static final int UNKNOWN_STATE = 3;

    private long bitrate;
    private int bitsPerSample;
    private int channels;
    private boolean consume;
    private long elapsedTime;
    private float elapsedTimeHighRes;
    private String error;
    private float mixrampdb;
    private float mixRampDelay;
    private int nextsong;
    private int nextsongid;
    private int playlist;
    private int playlistlength;
    private boolean random;
    private boolean repeat;
    private int sampleRate;
    private boolean single;
    private int song;
    private int songid;
    private int state;

    private long totalTime;
    private boolean updating;
    private int volume;
    private int xFade;

    // Listeners for status class. They are fired
    // when status is populated.
    private List<ChangeListener> listeners = new ArrayList<>();

    public Status() {
        reset();
    }

    public void populate(final Collection<String> entry) {
        reset();
        for (String s : entry) {

            String[] stateEntry = s.split(": ");
            switch (stateEntry[0]) {
                case "audio":
                    String[] audio = stateEntry[1].split(":");
                    try {
                        sampleRate = Integer.parseInt(audio[0]);
                        bitsPerSample = Integer.parseInt(audio[1]);
                        channels = Integer.parseInt(audio[2]);
                    } catch (NumberFormatException e) {
                        // when a value is 'unknown' or '?' it
                        // can't be written as integer.
                    }
                    break;
                case "bitrate":
                    bitrate = Long.parseLong(stateEntry[1]);
                    break;
                case "consume":
                    consume = "1".equals(stateEntry[1]);
                    break;
                case "elapsed":
                    elapsedTimeHighRes = Float.parseFloat(stateEntry[1]);
                    break;
                case "error":
                    error = stateEntry[1];
                    break;
                case "mixrampdb":
                    try {
                        mixrampdb = Float.parseFloat(stateEntry[1]);
                    } catch (NumberFormatException e) {
                        // sometimes server sends 'nan' as
                        // mixrampdb.
                    }
                    break;
                case "mixrampdelay":
                    try {
                        mixRampDelay = Float.parseFloat(stateEntry[1]);
                    } catch (NumberFormatException e) {
                        // sometimes server sends 'nan' as
                        // mixrampdb.
                    }
                    break;
                case "nextsong":
                    nextsong = Integer.parseInt(stateEntry[1]);
                    break;
                case "nextsongid":
                    nextsongid = Integer.parseInt(stateEntry[1]);
                    break;
                case "playlist":
                    playlist = Integer.parseInt(stateEntry[1]);
                    break;
                case "playlistlength":
                    playlistlength = Integer.parseInt(stateEntry[1]);
                    break;
                case "random":
                    random = "1".equals(stateEntry[1]);
                    break;
                case "repeat":
                    repeat = "1".equals(stateEntry[1]);
                    break;
                case "single":
                    single = "1".equals(stateEntry[1]);
                    break;
                case "song":
                    song = Integer.parseInt(stateEntry[1]);
                    break;
                case "songid":
                    songid = Integer.parseInt(stateEntry[1]);
                    break;
                case "state":
                    switch (stateEntry[1]) {
                        case "pause":
                            state = PAUSE_STATE;
                            break;
                        case "play":
                            state = PLAY_STATE;
                            break;
                        case "stop":
                            state = STOP_STATE;
                            break;
                        default:
                            state = UNKNOWN_STATE;
                    }
                    break;
                case "time":
                    String[] timeEntry = stateEntry[1].split(":");
                    elapsedTime = Long.parseLong(timeEntry[0]);
                    totalTime = Long.parseLong(timeEntry[1]);
                    break;
                case "volume":
                    volume = Integer.parseInt(stateEntry[1]);
                    break;
                case "xfade":
                    xFade = Integer.parseInt(stateEntry[1]);
                    break;
                case "updating_db":
                    updating = true;
                    break;
                default:
                    System.out.println("Unknown status value: " + stateEntry[0] + ": " + stateEntry[1]);
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

    public boolean removeListener(ChangeListener listener) {
        Iterator<ChangeListener> i = listeners.iterator();
        while (i.hasNext()) {
            ChangeListener c = i.next();
            if (c.equals(listener)) {
                return listeners.remove(c);
            }
        }
        return false;
    }


    private void reset() {
        bitrate = 0L;
        bitsPerSample = 0;
        channels = 0;
        consume = false;  // always in status
        elapsedTime = 0L;
        elapsedTimeHighRes = 0L;
        error = null;
        mixrampdb = 0.0f;  // always in status
        mixRampDelay = 0.0f;
        nextsong = -1;
        nextsongid = 0;
        playlist = 0;  // always in status
        playlistlength = 0;  // always in status
        random = false;  // always in status
        repeat = false;  // always in status
        sampleRate = 0;
        single = false;  // always in status
        song = 0;
        songid = 0;
        state = UNKNOWN_STATE;  // always in status
        totalTime = 0l;
        updating = false;
        volume = 0;  // always in status
        xFade = 0;

    }

    public long getBitrate() {
        return bitrate;
    }

    public int getBitsPerSample() {
        return bitsPerSample;
    }

    public int getChannels() {
        return channels;
    }

    public boolean isConsume() {
        return consume;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public float getElapsedTimeHighRes() {
        return elapsedTimeHighRes;
    }

    public String getError() {
        return error;
    }

    public float getMixrampdb() {
        return mixrampdb;
    }

    public float getMixRampDelay() {
        return mixRampDelay;
    }

    public int getNextsong() {
        return nextsong;
    }

    public int getNextsongid() {
        return nextsongid;
    }

    public int getPlaylist() {
        return playlist;
    }

    public int getPlaylistlength() {
        return playlistlength;
    }

    public boolean isRandom() {
        return random;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public boolean isSingle() {
        return single;
    }

    public int getSong() {
        return song;
    }

    public int getSongid() {
        return songid;
    }

    public int getState() {
        return state;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public boolean isUpdating() {
        return updating;
    }

    public int getVolume() {
        return volume;
    }

    public int getCrossFade() {
        return xFade;
    }

    @Override
    public String toString() {
        String stateS = (state == PLAY_STATE) ? "play" : (state == PAUSE_STATE) ? "pause" : (state == STOP_STATE) ? "stop" : "unknown";
        String reply = "Status: \n" +
                "bitrate=" + bitrate +
                "\nbitsPerSample=" + bitsPerSample +
                "\nchannels=" + channels +
                "\nconsume=" + consume +
                "\nelapsedTime=" + elapsedTime +
                "\nelapsedTimeHighRes=" + elapsedTimeHighRes +
                "\nerror='" + error + '\'' +
                "\nmixrampdb=" + mixrampdb +
                "\nmixRampDelay=" + mixRampDelay +
                "\nnextsong=" + nextsong +
                "\nnextsongid=" + nextsongid +
                "\nplaylist=" + playlist +
                "\nplaylistlength=" + playlistlength +
                "\nrandom=" + random +
                "\nrepeat=" + repeat +
                "\nsampleRate=" + sampleRate +
                "\nsingle=" + single +
                "\nsong=" + song +
                "\nsongid=" + songid +
                "\nstate=" + stateS +
                "\ntotalTime=" + totalTime +
                "\nupdating=" + updating +
                "\nvolume=" + volume +
                "\nxFade=" + xFade + "\n"
                ;
        return reply;
    }
}
