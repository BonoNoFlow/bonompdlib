package com.bono.api;

import java.util.*;

/**
 * Created by Hendrik Nieuwenhuis 2016.
 */
public class SongList implements Iterable<Song> {

    // synchronized arraylist.
    private List<Song> songList;

    public SongList() {
        songList = Collections.synchronizedList(new ArrayList<Song>());
    }

    public SongList(Collection<String> playlistinfo) {
        this();
        populate(playlistinfo);
    }

    /**
     * Populate the SongList with songs from an
     * entry collection of strings optained by
     * querying the playlist in the server.
     * @param entry collection of strings.
     */
    public void populate(Collection<String> entry) {
        synchronized (songList) {
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
    }

    /**
     * Add a song to the list. The song is added at the
     * index equal to the position of the song.
     * @param song
     */
    public void add(Song song) {
        int pos = song.getPos();
        synchronized (songList) {
            songList.set(pos, song);
        }
    }

    public List<Song> getAsList() {
        return songList;
    }

    /**
     * Get the song list as an array of songs.
     * @return song list as array of S ong.
     */
    public Song[] getAsArray() {
        return songList.toArray(new Song[songList.size()]);
    }

    /**
     * Get a song by its position / index in the playlist.
     * @param index position of song in playlist.
     * @return Song at the given position.
     */
    public Song getSongByIndex(int index) {
        return songList.get(index);
    }

    /**
     * Clear the song list.
     */
    public void clear() {
        songList.clear();
    }

    /**
     * Get the size of the song list.
     * @return int size of list.
     */
    public int getSize() {
        return songList.size();
    }

    @Override
    public Iterator<Song> iterator() {
        return songList.iterator();
    }


}
