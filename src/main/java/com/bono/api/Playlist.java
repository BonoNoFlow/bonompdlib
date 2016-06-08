package com.bono.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 19/04/16.
 */
public class Playlist {

    public static final String ADD                     = "add";

    public static final String ADDID                   = "addid";

    public static final String CLEAR                   = "clear";

    public static final String DELETE                  = "delete";

    public static final String DELETE_ID               = "deleteid";

    public static final String LOAD                    = "load";

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

    // listens when playlist is written.
    protected List<ChangeListener> listeners = new ArrayList<>();

    // listens when new song is added.
    protected List<ChangeListener> songListeners = new ArrayList<>();

    // the songs in this playlist.
    protected List<Song> songs = new ArrayList<>();


    public Playlist() {

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
                case Song.DATE:
                    song.setDate(line[1]);
                    break;
                case Song.GENRE:
                    song.setGenre(line[1]);
                    break;
                case Song.DISC:
                    song.setDisc(line[1]);
                    break;
                case Song.COMPOSER:
                    song.setId(line[1]);
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

    public void clear() {
        songs.clear();
    }

    public Iterator iterator() {
        return songs.iterator();
    }

    protected void fireListeners() {
        if (listeners.size() > 0) {
            for (ChangeListener listener : listeners) {
                listener.stateChanged(new ChangeEvent(this));
            }
        }
    }

    protected void fireSongListeners(Song song) {
        if (listeners.size() > 0) {
            for (ChangeListener listener : songListeners) {
                listener.stateChanged(new ChangeEvent(song));
            }
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

    public Song[] playlist() {
        Song[] songA = songs.toArray(new Song[songs.size()]);
        return songA;
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

}
