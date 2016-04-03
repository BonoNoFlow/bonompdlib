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
public class Playlist {

    /*
    Adds the file URI to the playlist (directories add recursively).
    URI can also be a single file.
    */
    public static final String ADD                     = "add";

    /*
    Adds a song to the playlist (non-recursive) and returns the song id.
    URI is always a single file or URL. For example:
    addid "foo.mp3"
    Id: 999
    OK
    */
    public static final String ADDID                     = "addid";

    // Clears the current playlist.
    public static final String CLEAR                   = "clear";

    /*
    Deletes a song from the playlist.
            delete [{POS} | {START:END}]
    */
    public static final String DELETE                  = "delete";

    /*
    Deletes the song SONGID from the playlist
            deleteid {SONGID}
    */
    public static final String DELETE_ID               = "deleteid";

    /*
    Moves the song at FROM or range of songs at START:END to TO in the playlist.
    [6]
            move [{FROM} | {START:END}] {TO}
    */
    public static final String MOVE                    = "move";

    /*
    Moves the song with FROM (songid) to TO (playlist index) in the playlist.
    If TO is negative, it is relative to the current song in the playlist
    (if there is one).
            moveid {FROM} {TO}
    */
    public static final String MOVE_ID                 = "moveid";

    /*
    Finds songs in the current playlist with strict matching.
            playlistfind {TAG} {NEEDLE}
    */
    public static final String PLAYLISTFIND            = "playlistfind";

    /*
    Displays a list of songs in the playlist. SONGID is optional
    and specifies a single song to display info for.
            playlistid {SONGID}
    */
    public static final String PLAYLISTID              = "playlistid";

    /*
    Displays a list of all songs in the playlist, or if the optional
    argument is given, displays information only for the song
    SONGPOS or the range of songs START:END [6]
            playlistinfo [[SONGPOS] | [START:END]]
    */
    public static final String PLAYLISTINFO            = "playlistinfo";

    /*
    Searches case-insensitively for partial matches in the current playlist.
            playlistsearch {TAG} {NEEDLE}
    */
    public final String PLAYLISTSEARCH                 = "playlistsearch";

    /*
    Displays changed songs currently in the playlist since VERSION.
            plchanges {VERSION}
    To detect songs that were deleted at the end of the playlist,
    use playlistlength returned by status command.
    */
    public static final String CHANGES                 = "plchanges";

    /*
    Displays changed songs currently in the playlist since VERSION.
    This function only returns the position and the id of the changed
    song, not the complete metadata. This is more bandwidth efficient.
            plchangesposid {VERSION}
    To detect songs that were deleted at the end of the playlist,
    use playlistlength returned by status command.
    */
    public static final String PLCHANGESPOSID         = "plchangesposid";

    /*
    Set the priority of the specified songs. A higher priority means
    that it will be played first when "random" mode is enabled.
    A priority is an integer between 0 and 255. The default priority
    of new songs is 0.
            prio {PRIORITY} {START:END...}
    */
    public static final String PRIO                   = "prio";

    /*
    Same as prio, but address the songs with their id.
            prioid {PRIORITY} {ID...}
    */
    public static final String PRIOID                 = "prioid";

    /*
    [7] Specifies the portion of the song that shall be played.
    START and END are offsets in seconds (fractional seconds allowed);
    both are optional. Omitting both (i.e. sending just ":") means
    "remove the range, play everything". A song that is currently
    playing cannot be manipulated this way.
            rangeid {ID} {START:END}
    */
    public static final String RANGEID               = "rangeid";

    /*
    Shuffles the current playlist. START:END is optional and specifies
    a range of songs.
            shuffle [START:END]
    */
    public static final String SHUFFLE               = "shuffle";

    /*
    Swaps the positions of SONG1 and SONG2.
            swap {SONG1} {SONG2}
    */
    public static final String SWAP                  = "swap";

    /*
    Swaps the positions of SONG1 and SONG2 (both song ids).
        swapid {SONG1} {SONG2}
    */
    public static final String SWAPID                = "swapid";

    /*
    Adds a tag to the specified song. Editing song tags is only possible
    for remote songs. This change is volatile: it may be overwritten by
    tags received from the server, and the data is gone when the song gets
    removed from the queue.
            addtagid {SONGID} {TAG} {VALUE}
    */
    public static final String ADDTAGID              = "addtagid";

    /*
    Removes tags from the specified song. If TAG is not specified, then
    all tag values will be removed. Editing song tags is only possible
    for remote songs.
            cleartagid {SONGID} [TAG]
    */
    public static final String CLEARTAGID            = "cleartagid";

    // listens when playlist is written.
    protected List<ChangeListener> listeners = new ArrayList<>();

    // listens when new song is added.
    protected List<ChangeListener> songListeners = new ArrayList<>();

    // the songs in this playlist.
    protected List<Song> songs = new ArrayList<>();

    protected DBExecutor dbExecutor;


    public Playlist() {}

    public Playlist(DBExecutor dbExecutor) {
        this.dbExecutor = dbExecutor;
    }

    public Playlist(String entry) {
        populate(entry);
    }

    public Song getSong(int index) {
        return songs.get(index);
    }

    public void populate(String entry) {
        songs.clear();

        Song song = null;
        Reply reply = new Reply(entry);
        Iterator i = reply.iterator();

        while (i.hasNext()) {
            String[] line = ((String) i.next()).split(Reply.SPLIT_LINE);
            switch (line[0]) {
                case Song.FILE:
                    song = new Song();
                    song.setFile(line[1]);
                    break;
                case Song.LAST_MODIFIED:
                    song.setLastModified(line[1]);
                    break;
                case Song.TITLE:
                    song.setTitle(line[1]);
                    break;
                case Song.ALBUM:
                    song.setAlbum(line[1]);
                    break;
                case Song.ARTIST:
                    song.setArtist(line[1]);
                    break;
                case Song.GENRE:
                    song.setGenre(line[1]);
                    break;
                case Song.DATE:
                    song.setDate(line[1]);
                    break;
                case Song.TRACK:
                    song.setTrack(line[1]);
                    break;
                case Song.ALBUM_ARTIST:
                    song.setAlbumArtist(line[1]);
                    break;
                case Song.NAME:
                    song.setName(line[1]);
                    break;
                case Song.TIME:
                    song.setTime(line[1]);
                    break;
                case Song.POS:
                    song.setPos(line[1]);
                    break;
                case Song.ID:
                    song.setId(line[1]);
                    songs.add(song);
                    fireSongListeners(song);  // add boolean.
                    song = null;
                    break;
                default:
                    System.out.println("Not a property: " + line[0]);
                    break;
            }

        }

        fireListeners();
    }

    public String add(String uri) throws Exception {
        return dbExecutor.execute(new DefaultCommand(ADD, uri));
    }

    public String addid(String uri, String pos) throws Exception {
        DefaultCommand command = new DefaultCommand(uri);
        if (!pos.equals(null) || pos != null) {
            command.addArgs(pos);
        }
        return dbExecutor.execute(command);
    }


    public Iterator iterator() {
        return songs.iterator();
    }

    public void clear() {
        songs.clear();
    }

    protected void fireListeners() {
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    protected void fireSongListeners(Song song) {
        for (ChangeListener listener : songListeners) {
            listener.stateChanged(new ChangeEvent(song));
        }
    }

    /*
    * Listener is triggered every time the playlist is populated.
    * */
    public void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    /*
    * Song listener is triggered every time a song is added to the playlist.
    *
    *
    * */
    public void addSongListener(ChangeListener listener) {
        songListeners.add(listener);
    }

    public void printPlaylist() {
        Iterator i = songs.iterator();

        while (i.hasNext()) {
            System.out.println(((Song) i.next()).toString());
        }
    }

    public int getSize() {
        return songs.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Playlist playlist = (Playlist) o;

        if (listeners != null ? !listeners.equals(playlist.listeners) : playlist.listeners != null) return false;
        if (songListeners != null ? !songListeners.equals(playlist.songListeners) : playlist.songListeners != null)
            return false;
        return songs != null ? songs.equals(playlist.songs) : playlist.songs == null;

    }

    @Override
    public int hashCode() {
        int result = listeners != null ? listeners.hashCode() : 0;
        result = 31 * result + (songListeners != null ? songListeners.hashCode() : 0);
        result = 31 * result + (songs != null ? songs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "listeners=" + listeners +
                ", songListeners=" + songListeners +
                ", songs=" + songs +
                '}';
    }
}
