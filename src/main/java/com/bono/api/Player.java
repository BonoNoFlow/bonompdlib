package com.bono.api;

import java.io.IOException;


/**
 * Created by Hendrik Nieuwenhuis.
 */
public class Player extends AbstractController {

    public static final String CONSUME        = "consume";

    public static final String CROSSFADE      = "crossfade";

    /*
    mixrampdb {deciBels}
    Sets the threshold at which songs will be overlapped. Like crossfading but doesn't fade the track volume, just overlaps. The songs need to have MixRamp tags added by an external tool. 0dB is the normalized maximum volume so use negative values, I prefer -17dB. In the absence of mixramp tags crossfading will be used. See http://sourceforge.net/projects/mixramp
    */
    //public static final String MIXRAMPDB      = "mixrampdb";

    /*
    mixrampdelay {SECONDS}
    Additional time subtracted from the overlap calculated by mixrampdb. A value of "nan" disables MixRamp overlapping and falls back to crossfading.
    */
    //public static final String MIXRAMPDELAY   = "mixrampdelay";

    public static final String NEXT           = "next";

    public static final String PAUSE          = "pause";

    public static final String PLAY           = "play";

    public static final String PLAYID         = "playid";

    public static final String PREVIOUS       = "previous";

    public static final String RANDOM         = "random";

    public static final String REPEAT         = "repeat";

    public static final String SEEK           = "seek";

    public static final String SEEKID         = "seekid";

    public static final String SEEKCUR        = "seekcur";

    public static final String SETVOL         = "setvol";

    public static final String SINGLE         = "single";

    public static final String STOP           = "stop";


    private ClientExecutor clientExecutor;

    private Song playingSong;

    public Player(ClientExecutor clientExecutor) {
        super(clientExecutor);
    }

    /**
     * Consume, when turned on '1', removes the song
     * just played from the playlist.
     * @param value boolean true or false.
     * @throws IOException
     */
    public void consume(boolean value) throws IOException {

        clientExecutor.execute(CONSUME, booleanToString(value));
    }

    /**
     * Sets crossfading between songs
     * @param seconds integer, amount of seconds of songs to be crossfaded.
     * @throws IOException
     */
    public void crossfade(int seconds) throws IOException {
        clientExecutor.execute(CROSSFADE, Integer.toString(seconds));
    }

    /**
     * Play the next song.
     * @throws IOException
     */
    public void next() throws IOException {
        clientExecutor.execute(NEXT);
    }

    /**
     * Set the server to pause or play when already paused.
     * Argument int has to be given either 0 or 1.
     *
     * @param value boolean true for pause false for play (stop pausing).
     * @throws IOException
     */
    public void pause(boolean value) throws IOException {
        clientExecutor.execute(PAUSE, booleanToString(value));
    }

    /**
     * Set the server to play.
     * @throws IOException
     */
    public void play() throws IOException {
        clientExecutor.execute(PLAY);
    }

    /**
     * Set the server to play the song at the
     * given position on the playlist.
     *
     * @param position int song position.
     * @throws IOException
     */
    public void playPos(int position) throws IOException {
        clientExecutor.execute(PLAY, Integer.toString(position));
    }

    /**
     * Set the server to play. An integer has to be given as argument
     * representing the song id in the playlist.
     *
     * @param songId int song id.
     * @throws IOException
     */
    public void playId(int songId) throws IOException {
        clientExecutor.execute(PLAYID, Integer.toString(songId));
    }

    /**
     * Play the previous song.
     * @throws IOException
     */
    public void previous() throws IOException {
        clientExecutor.execute(PREVIOUS);
    }

    /**
     * Set the playback to random mode.
     * @param value boolean true or false.
     * @throws IOException
     */
    public void random(boolean value) throws IOException {
        clientExecutor.execute(RANDOM, booleanToString(value));
    }

    /**
     * Repeat the playback of the playlist.
     * @param value boolean true or false.
     * @throws IOException
     */
    public void repeat(boolean value) throws IOException {
        clientExecutor.execute(REPEAT, booleanToString(value));
    }

    // seek

    // seekid

    /**
     * Seek / go to the given time inside the currently
     * playing song.
     * @param time seconds to go to inside the song.
     * @throws IOException
     */
    public void seekCur(int time) throws IOException {
        clientExecutor.execute(SEEKCUR, Integer.toString(time));
    }

    /**
     * Sets the playback volume.
     * @param volume integer in the range of 0 - 100.
     * @throws IOException
     */
    public void setVol(int volume) throws IOException {
        if (volume >= 0 && volume <= 100) {
            clientExecutor.execute(SETVOL, Integer.toString(volume));
        }
    }

    /**
     * Only play the song selected. Unless repeat is also selected
     * the server stops playing after the song is done.
     * While repeat, the server keeps playing the selected song
     * over and over.
     * @param value boolean true or false.
     * @throws IOException
     */
    public void single(boolean value) throws IOException {
        clientExecutor.execute(SINGLE, booleanToString(value));
    }

    /**
     * Set the server to stop playing.
     * @throws IOException
     */
    public void stop() throws IOException {
        clientExecutor.execute(STOP);
    }

    /**
     * Changes a boolean value to a String value "0" or "1".

     * @param value boolean
     * @return String "0" for false or "1" for true
     */
    public static String booleanToString(boolean value) {
        if (value) {
            return "1";
        }
        return "0";
    }

    public ClientExecutor getClientExecutor() {
        return clientExecutor;
    }

    public Song getPlayingSong() {
        return playingSong;
    }

    public void setPlayingSong(Song playingSong) {
        this.playingSong = playingSong;
    }
}
