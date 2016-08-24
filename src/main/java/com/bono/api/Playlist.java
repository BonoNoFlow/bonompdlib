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
    protected List<Song> songs = new ArrayList<>();


    public Playlist() {

    }

    public Song getSong(int index) {
        return songs.get(index);
    }

    public void populate(Collection<String> entry) {
        songs.clear();
        List<String> songCache = new ArrayList<>();
        songCache.clear();
        int size = entry.size();

        for (String s : entry) {
            if (s.startsWith("file")) {
                if (!songCache.isEmpty()) {
                    songs.add(Song.createSong(songCache));
                    songCache.clear();
                }
            }
            songCache.add(s);
        }

        if (!songCache.isEmpty()) {
            songs.add(Song.createSong(songCache));
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

    @Override
    public String toString() {
        StringBuilder playlist = new StringBuilder();
        for (Song song : songs) {
            playlist.append(song.toString() + "\n");
        }
        return playlist.toString();
    }
}
