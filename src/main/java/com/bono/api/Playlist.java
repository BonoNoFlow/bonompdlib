package com.bono.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 19/04/16.
 */
public class Playlist extends AbstractController {

    public static final String ADD                     = "add";

    public static final String ADDID                   = "addid";

    public static final String CLEAR                   = "clear";

    public static final String LOAD                    = "load";

    public static final String PLAYLISTFIND            = "playlistfind";

    public static final String PLAYLISTINFO            = "playlistinfo";

    public static final String PLAYLISTSEARCH          = "playlistsearch";

    public static final String SHUFFLE                 = "shuffle";

    // listens when playlist is written.
    protected List<ChangeListener> listeners = new ArrayList<>();

    // listens when new song is added.
    protected List<ChangeListener> songListeners = new ArrayList<>();

    // the songs in this playlist.
    //protected List<Song> songs = new ArrayList<>();

    protected SongList songList;


    @Deprecated
    public Playlist() {
        super(new ClientExecutor());
        songList = new SongList();
    }

    public Playlist(ClientExecutor clientExecutor) {
        super(clientExecutor);
    }

    public Song getSong(int index) {
        return songList.getSongByIndex(index);
    }

    public void populate(Collection<String> entry) {
        songList.populate(entry);

        fireListeners();
    }

    /**
     * Add a song by the given uri to the current playlist.
     * @param uri String uri song.
     * @throws IOException
     */
    public void add(String uri) throws IOException {
        clientExecutor.execute(ADD, uri);
    }

    /**
     *
     * @param uri String song uri, cannot be null.
     * @param position int position to add the song to. -1 for no specified position.
     * @return
     * @throws IOException
     */
    public String addId(String uri, int position) throws IOException {
        return null;
    }

    /**
     * Clear the current playlist.
     * @throws IOException
     */
    public void clear() throws IOException {
        clientExecutor.execute(CLEAR);
    }

    /**
     * Load an uri to the playlist.
     * @param uri
     * @throws IOException
     */
    public void load(String uri) throws IOException {
        clientExecutor.execute(LOAD, uri);
    }

    /**
     * Query the server's current playlist.
     * @return SongList containing the songs in the playlist.
     * @throws IOException
     */
    public SongList playlistInfo() throws IOException {
        return new SongList(clientExecutor.execute(PLAYLISTINFO));
    }

    /**
     * Search case-insensitive for matches in the current playlist.
     * @param tag, a Tag to search for example 'album', 'any' or 'title'
     * @param needle the search input to search for
     * @return SongList results matching the search input.
     * @throws IOException
     */
    public SongList playlistSearch(String tag, String needle) throws IOException {
        return new SongList(clientExecutor.execute(PLAYLISTSEARCH, tag, needle));
    }

    /**
     * Shuffle the playlist.
     * @throws IOException
     */
    public void shuffle() throws IOException {
        clientExecutor.execute(SHUFFLE);
    }

    /**
     * Shuffle the playlist whitin the given range.
     * @param range of songs to shuffle. 'START:END'
     * @throws IOException
     */
    public void shuffle(String range) throws IOException {
        clientExecutor.execute(SHUFFLE, range);
    }

    /**
     * Query the servers current playlist and populate the
     * song list with it.
     */
    public void queryPlaylist() throws IOException {
        if (clientExecutor == null) {
            throw new NullPointerException("ClientExecutor is null");
        }
        this.songList = playlistInfo();
    }


    public Iterator iterator() {
        return songList.iterator();
    }

    protected void fireListeners() {
        if (listeners.size() > 0) {
            for (ChangeListener listener : listeners) {
                listener.stateChanged(new ChangeEvent(this));
            }
        }
    }

    protected void fireSongListeners(Song song) {
        if (songListeners.size() > 0) {
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

    public void setSong(Song song) {
        songList.add(song);
    }

    public SongList getSongList() {
        return songList;
    }


    public void printPlaylist() {
        Iterator i = songList.iterator();

        while (i.hasNext()) {
            System.out.println(((Song) i.next()).toString());
        }
    }

    public int getSize() {
        return songList.getSize();
    }

    @Override
    public String toString() {
        StringBuilder playlist = new StringBuilder();
        Iterator<Song> i = songList.iterator();
        while (i.hasNext()) {

            playlist.append(i.next().toString() + "\n");
        }
        return playlist.toString();
    }
}
