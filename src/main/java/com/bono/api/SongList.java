package com.bono.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Hendrik Nieuwenhuis 2016.
 */
public class SongList implements Iterable<Song> {

    private List<Song> songList;

    public void populate(Collection<String> entry) {
        songList.clear();
        List<String> songCache = new ArrayList<>();
        songCache.clear();
        int size = entry.size();
        Song song = null;
        for (String s : entry) {
            if (s.startsWith("file")) {
                if (!songCache.isEmpty()) {
                    song = Song.createSong(songCache);
                    songList.add(song);
                    songCache.clear();
                }
            }
            songCache.add(s);
        }

        if (!songCache.isEmpty()) {
            song = Song.createSong(songCache);
            songList.add(song);
        }
    }

    public void add(Song song) {
        int pos = song.getPos();

        songList.set(pos, song);
    }

    @Override
    public Iterator<Song> iterator() {
        return null;
    }
}
