package com.bono.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 29/07/15.
 */
public class Song {

    public static final String FILE = "file";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String TITLE = "Title";
    public static final String ALBUM = "Album";
    public static final String ARTIST = "Artist";
    public static final String DATE = "Date";
    public static final String GENRE = "Genre";
    public static final String DISC = "Disc";
    public static final String TRACK = "Track";
    public static final String ALBUM_ARTIST = "AlbumArtist";
    public static final String TIME = "Time";
    public static final String POS = "Pos";
    public static final String ID = "Id";
    public static final String NAME = "Name";
    public static final String COMPOSER = "Composer";

    private String file;
    private String lastModified;
    private String title;
    private String album;
    private String artist;
    private String date;
    private String genre;
    private String disc;
    private String track;
    private String albumArtist;
    private long time;
    private int pos;
    private int id;
    private String Name;
    private String composer;

    protected List<ChangeListener> listeners = new ArrayList<>();

    public Song() {}

    public Song(List<String> entry) {
        populate(entry);
    }

    public void populate(List<String> entry) {

        Iterator<String> i = entry.iterator();
        while (i.hasNext()) {
            String[] line = i.next().split(": ");
            switch (line[0]) {
                case Song.FILE:
                    file = line[1];
                    break;
                case Song.LAST_MODIFIED:
                    lastModified = line[1];
                    break;
                case Song.TITLE:
                    title = line[1];
                    break;
                case Song.ALBUM:
                    album = line[1];
                    break;
                case Song.ARTIST:
                    artist = line[1];;
                    break;
                case Song.DATE:
                    date = line[1];
                    break;
                case Song.GENRE:
                    genre = line[1];
                    break;
                case Song.DISC:
                    disc = line[1];
                    break;
                case Song.COMPOSER:
                    composer = line[1];
                    break;
                case Song.TRACK:
                    track = line[1];
                    break;
                case Song.ALBUM_ARTIST:
                    albumArtist = line[1];
                    break;
                case Song.TIME:
                    time = Integer.parseInt(line[1]);
                    break;
                case Song.POS:
                    pos = Integer.parseInt(line[1]);
                    break;
                case Song.ID:
                    id = Integer.parseInt(line[1]);
                    break;
                default:
                    System.out.println("Not a property: " + line[0]);
                    break;
            }
        }
        fireListeners();
    }



    protected void fireListeners() {
        if (listeners.size() > 0) {
            for (ChangeListener listener : listeners) {
                listener.stateChanged(new ChangeEvent(this));
            }
        }
    }

    public void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public void setTime(String time) {
        this.time = Long.parseLong(time);
    }

    public void setPos(String pos) {
        this.pos = Integer.parseInt(pos);
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public void setName(String name) {
        Name = name;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getFile() {
        return file;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getDate() {
        return date;
    }

    public String getGenre() {
        return genre;
    }

    public String getDisc() {
        return disc;
    }

    public String getTrack() {
        return track;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public long getTime() {
        return time;
    }

    public int getPos() {
        return pos;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getComposer() {
        return composer;
    }

    @Override
    public String toString() {
        return "Song{" +
                "file='" + file + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", date='" + date + '\'' +
                ", genre='" + genre + '\'' +
                ", disc='" + disc + '\'' +
                ", track='" + track + '\'' +
                ", albumArtist='" + albumArtist + '\'' +
                ", time=" + time +
                ", pos=" + pos +
                ", id=" + id +
                ", Name='" + Name + '\'' +
                ", composer='" + composer + '\'' +
                '}';
    }
}
