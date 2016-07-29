package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 08/06/16.
 */
public abstract class Playback {

    public static final String NEXT           = "next";

    public static final String PAUSE          = "pause";

    public static final String PLAY           = "play";

    public static final String PLAYID         = "playid";

    public static final String PREVIOUS       = "previous";

    public static final String SEEK           = "seek";

    public static final String SEEKID         = "seekid";

    public static final String SEEKCUR        = "seekcur";

    public static final String STOP           = "stop";

    /*
    consume {STATE}
    [2] Sets consume state to STATE, STATE should be 0 or 1. When consume is activated, each song played is removed from playlist.
     */
    public static final String CONSUME        = "consume";

    /*
    crossfade {SECONDS}
    Sets crossfading between songs.
    */
    public static final String CROSSFADE      = "crossfade";

    /*
    mixrampdb {deciBels}
    Sets the threshold at which songs will be overlapped. Like crossfading but doesn't fade the track volume, just overlaps. The songs need to have MixRamp tags added by an external tool. 0dB is the normalized maximum volume so use negative values, I prefer -17dB. In the absence of mixramp tags crossfading will be used. See http://sourceforge.net/projects/mixramp
    */
    public static final String MIXRAMPDB      = "mixrampdb";

    /*
    mixrampdelay {SECONDS}
    Additional time subtracted from the overlap calculated by mixrampdb. A value of "nan" disables MixRamp overlapping and falls back to crossfading.
    */
    public static final String MIXRAMPDELAY   = "mixrampdelay";

    /*
    random {STATE}
    Sets random state to STATE, STATE should be 0 or 1.
    */
    public static final String RANDOM         = "random";

    /*
    repeat {STATE}
    Sets repeat state to STATE, STATE should be 0 or 1.
    */
    public static final String REPEAT         = "repeat";

    /*
    setvol {VOL}
    Sets volume to VOL, the range of volume is 0-100.
    */
    public static final String SETVOL         = "setvol";

    /*
    single {STATE}
    [2] Sets single state to STATE, STATE should be 0 or 1. When single is activated, playback is stopped after current song, or song is repeated if the 'repeat' mode is enabled.

            replay_gain_mode {MODE}

    Sets the replay gain mode. One of off, track, album, auto[5].

    Changing the mode during playback may take several seconds, because the new settings does not affect the buffered data.

    This command triggers the options idle event.

            replay_gain_status

    Prints replay gain options. Currently, only the variable replay_gain_mode is returned.
    */
    public static final String SINGLE         = "single";


}
