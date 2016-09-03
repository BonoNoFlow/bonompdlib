package com.bono.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 19/04/16.
 */
public class Playlist {

    // listens when playlist is written.
    protected List<ChangeListener> listeners = new ArrayList<>();

    // listens when new song is added.
    protected List<ChangeListener> songListeners = new ArrayList<>();

    // the songs in this playlist.
    //protected List<Song> songs = new ArrayList<>();

    protected SongList songList;


    public Playlist() {
        songList = new SongList();
    }

    public Song getSong(int index) {
        return songList.getSongByIndex(index);
    }

    public void populate(Collection<String> entry) {
        songList.populate(entry);

        fireListeners();
    }



    public void clear() {
        songList.clear();
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

    public Song[] playlist() {
        Song[] songA = null;//songs.toArray(new Song[songs.size()]);
        return songA;
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
