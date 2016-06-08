package com.bono.api;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 29/07/15.
 *
 * moet aangepast worden om in api package te kunnen.
 *
 * changelistener implementere!
 */
@Deprecated
public class PlaylistControl extends Exec {

    public static final String ADD                     = "add";

    public static final String ADDID                   = "addid";

    // Clears the current playlist.
    public static final String CLEAR                   = "clear";

    public static final String DELETE                  = "delete";

    public static final String DELETE_ID               = "deleteid";

    public static final String MOVE                    = "move";
    public static final String MOVE_ID                 = "moveid";

    public static final String PLAYLISTFIND            = "playlistfind";

    public static final String PLAYLISTID              = "playlistid";

    public static final String PLAYLISTINFO            = "playlistinfo";

    public final String PLAYLISTSEARCH                 = "playlistsearch";

    public static final String PLCHANGES               = "plchanges";

    public static final String PLCHANGESPOSID          = "plchangesposid";

    public static final String PRIO                    = "prio";

    public static final String SHUFFLE                 = "shuffle";

    public static final String PRIOID                  = "prioid";

    public static final String RANGEID                 = "rangeid";

    public static final String SWAP                    = "swap";

    public static final String SWAPID                  = "swapid";

    public static final String ADDTAGID                = "addtagid";

    public static final String CLEARTAGID              = "cleartagid";



    public PlaylistControl(DBExecutor dbExecutor) {
        super(dbExecutor);
    }


    /*
    Adds the file URI to the playlist (directories add recursively).
    URI can also be a single file.
    @param String uri, for file path to add, cannot be null.
     */
    public String add(String uri) throws Exception {
        if (uri == null) {
            throw new NullPointerException("String uri cannot be null!");
        }
        return execCommand(ADD, uri);
    }

    /*
    Adds a song to the playlist (non-recursive) and returns the song id.
    URI is always a single file or URL. For example:
    addid "foo.mp3"
    Id: 999
    OK
    @param String uri, the file to be added, cannot be null.
    @param String pos, the position to add the file to, can be null.
     */
    public String addid(String uri, String pos) throws Exception {
        if (uri == null) {
            throw new NullPointerException("String uri cannot be null!");
        }
        if (pos == null) {
            return execCommand(ADDID, uri);
        }
        return execCommand(ADDID, uri, pos);
    }

    /*
    Clear the current playlist.
     */
    public String clear() throws Exception {
        return  execCommand(CLEAR);
    }

    /*
    Deletes a song from the playlist.
            delete [{POS} | {START:END}]
    @param String pos, the song position or range of positions to delete, cannot be null.
     */
    public String delete(String pos) throws Exception {
        if (pos == null) {
            throw new NullPointerException("String pos cannot be null");
        }
        return execCommand(DELETE, pos);
    }

    /*
    Deletes the song SONGID from the playlist
            deleteid {SONGID}
    @param String songid, the song to be deleted, cannot be null.
    */
    public String deleteId(String songid) throws Exception {
        if (songid == null) {
            throw new NullPointerException("String songid cannot be null!");
        }
        return execCommand(DELETE_ID, songid);
    }

    /*
    Moves the song at FROM or range of songs at START:END to TO in the playlist.
    [6]
            move [{FROM} | {START:END}] {TO}
    @param String from, the position or range from which songs to move, cannot be null!
    @param String to, the position where to move the songs to, cannot be null!
    */
    public String move(String from, String to) throws Exception {
        if (from == null) {
            throw new NullPointerException("String from cannot be null!");
        }
        if (to == null) {
            throw new NullPointerException("String to cannot be null!");
        }
        return execCommand(MOVE, from, to);

    }

    /*
    Moves the song with FROM (songid) to TO (playlist index) in the playlist.
    If TO is negative, it is relative to the current song in the playlist
    (if there is one).
            moveid {SONGID} {TO}
    @param String songid, the song, by id, to move, cannot be null!
    @param String to, the position where to move the song to, cannot be null!
    */
    public String moveId(String songid, String to) throws Exception {
        if (songid == null) {
            throw new NullPointerException("String songid cannot be null!");
        }
        if (to == null) {
            throw new NullPointerException("String to cannot be null!");
        }
        return execCommand(MOVE_ID, songid, to);
    }

    /*
    Finds songs in the current playlist with strict matching.
            playlistfind {TAG} {NEEDLE}
    */
    public String playlistfind(String... args) throws Exception {
        return execCommand(PLAYLISTFIND, args);

    }

    /*
    Displays a list of songs in the playlist. SONGID is optional
    and specifies a single song to display info for.
            playlistid {SONGID}
    */
    public String playlistid(String songid) throws Exception {
        return execCommand(PLAYLISTID, songid);

    }

    /*
    Displays a list of all songs in the playlist, or if the optional
    argument is given, displays information only for the song
    SONGPOS or the range of songs START:END [6]
            playlistinfo [[SONGPOS] | [START:END]]
    @param String arg for songpos or start:end, can be null.*/
    public String playlistinfo(String arg) throws Exception {
        if (arg == null) {
            return execCommand(PLAYLISTINFO);
        }
        return execCommand(PLAYLISTINFO, arg);
    }

    /*
    Searches case-insensitively for partial matches in the current playlist.
            playlistsearch {TAG} {NEEDLE}
    @param  String tag, the tag to search for, cannot be null.
    @param String needla, TODO test!
    */
    public String playlistsearch(String tag, String needle) throws Exception {
        if (tag == null) {
            throw new NullPointerException("String tag cannot be null!");
        }
        return execCommand(PLAYLISTSEARCH, tag, needle);

    }

    /*
    Displays changed songs currently in the playlist since VERSION.
            plchanges {VERSION}
    To detect songs that were deleted at the end of the playlist,
    use playlistlength returned by status command.
    */
    public String plchanges(String version) throws Exception {
        return execCommand(PLCHANGES, version);

    }

    /*
    Displays changed songs currently in the playlist since VERSION.
    This function only returns the position and the id of the changed
    song, not the complete metadata. This is more bandwidth efficient.
            plchangesposid {VERSION}
    To detect songs that were deleted at the end of the playlist,
    use playlistlength returned by status command.
    */
    public String plchangesposid(String version) throws Exception {
        return execCommand(PLCHANGESPOSID, version);

    }

    /*
    Set the priority of the specified songs. A higher priority means
    that it will be played first when "random" mode is enabled.
    A priority is an integer between 0 and 255. The default priority
    of new songs is 0.
            prio {PRIORITY} {START:END...}
    */
    public String prio(String priority, String range) throws Exception {
        return execCommand(PRIO, priority, range);
    }

    /*
    Same as prio, but address the songs with their id.
            prioid {PRIORITY} {ID...}
    */
    public String prioid(String priority, String id) throws Exception {
        return execCommand(PRIOID, priority, id);
    }

    /*
    [7] Specifies the portion of the song that shall be played.
    START and END are offsets in seconds (fractional seconds allowed);
    both are optional. Omitting both (i.e. sending just ":") means
    "remove the range, play everything". A song that is currently
    playing cannot be manipulated this way.
            rangeid {ID} {START:END}
    */
    public String rangeid(String id, String range) throws Exception {
        return execCommand(RANGEID, id, range);
    }

    /*
    Shuffles the current playlist. START:END is optional and specifies
    a range of songs.
            shuffle [START:END]
    @param String range, optional range of songs to shuffle, can be null!
    */
    public String shuffle(String range) throws Exception {
        if (range == null) {
            return execCommand(SHUFFLE);
        }
        return execCommand(SHUFFLE, range);
    }

    /*
    Swaps the positions of SONG1 and SONG2.
            swap {SONG1} {SONG2}
    @param String song1 an song2, cannot be null!
    */
    public String swap(String song1, String song2) throws Exception {
        if (song1 == null) {
            throw new NullPointerException("String song1 cannot be null");
        }
        if (song2 == null) {
            throw new NullPointerException("String song2 cannot be null");
        }
        return execCommand(SWAP, song1, song2);
    }

    /*
    Swaps the positions of SONG1 and SONG2 (both song ids).
        swapid {SONGID1} {SONGID2}
    @param String song1 and song2, songs specofied by id to swap, cannot be null!
    */
    public String swapid(String songid1, String songid2) throws Exception {
        if (songid1 == null) {
            throw new NullPointerException("String songid1 cannot be null");
        }
        if (songid2 == null) {
            throw new NullPointerException("String songid2 cannot be null");
        }
        return execCommand(SWAPID, songid1, songid2);
    }

    /*
    Adds a tag to the specified song. Editing song tags is only possible
    for remote songs. This change is volatile: it may be overwritten by
    tags received from the server, and the data is gone when the song gets
    removed from the queue.
            addtagid {SONGID} {TAG} {VALUE}
    @param String songid, the specified song, cannot be null!
    @param String tag, the tag to add, cannot be null!
    @param String value, TODO NULL or NOT!
    */
    public String addtagid(String songid, String tag, String value) throws Exception {
        if (songid == null) {
            throw new NullPointerException("String songid cannot be null!");
        }
        if (tag == null) {
            throw new NullPointerException("String tag cannot be null!");
        }
        return execCommand(ADDTAGID, songid, tag, value);
    }

    /*
    Removes tags from the specified song. If TAG is not specified, then
    all tag values will be removed. Editing song tags is only possible
    for remote songs.
            cleartagid {SONGID} [TAG]
    @param String songid, the song to be cleared from tags, cannot be null!
    @param String tag, the tag to be removed, can be null!
    */
    public String cleartagid(String songid, String tag) throws Exception {
        if (songid == null) {
            throw new NullPointerException("String songid cannot be null!");
        }
        if (tag == null) {
            return execCommand(CLEARTAGID, songid);
        }
        return execCommand(CLEARTAGID, songid, tag);
    }

}
