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
    private String time;
    private String pos;
    private String id;
    private String Name;
    private String composer;

    protected List<ChangeListener> listeners = new ArrayList<>();

    public Song() {}

    public Song(List<String> entry) {
        populate(entry);
    }

    public void populate(List<String> entry) {
        //Reply reply = new Reply(entry);
        Iterator<String> i = entry.iterator();
        while (i.hasNext()) {
            String[] line = i.next().split(": ");
            switch (line[0]) {
                case Song.FILE:
                    setFile(line[1]);
                    break;
                case Song.LAST_MODIFIED:
                    setLastModified(line[1]);
                    break;
                case Song.TITLE:
                    setTitle(line[1]);
                    break;
                case Song.ALBUM:
                    setAlbum(line[1]);
                    break;
                case Song.ARTIST:
                    setArtist(line[1]);
                    break;
                case Song.DATE:
                    setDate(line[1]);
                    break;
                case Song.GENRE:
                    setGenre(line[1]);
                    break;
                case Song.DISC:
                    setDisc(line[1]);
                    break;
                case Song.COMPOSER:
                    setId(line[1]);
                    break;
                case Song.TRACK:
                    setTrack(line[1]);
                    break;
                case Song.ALBUM_ARTIST:
                    setAlbumArtist(line[1]);
                    break;
                case Song.NAME:
                    setName(line[1]);
                    break;
                case Song.TIME:
                    setTime(line[1]);
                    break;
                case Song.POS:
                    setPos(line[1]);
                    break;
                case Song.ID:
                    setId(line[1]);
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    @Override
    public String toString() {
        return "Song{" +
                "file='" + file + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", date='" + date + '\'' +
                ", track='" + track + '\'' +
                ", albumArtist='" + albumArtist + '\'' +
                ", time='" + time + '\'' +
                ", pos='" + pos + '\'' +
                ", id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", composer='" + composer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (file != null ? !file.equals(song.file) : song.file != null) return false;
        if (lastModified != null ? !lastModified.equals(song.lastModified) : song.lastModified != null) return false;
        if (title != null ? !title.equals(song.title) : song.title != null) return false;
        if (album != null ? !album.equals(song.album) : song.album != null) return false;
        if (artist != null ? !artist.equals(song.artist) : song.artist != null) return false;
        if (genre != null ? !genre.equals(song.genre) : song.genre != null) return false;
        if (date != null ? !date.equals(song.date) : song.date != null) return false;
        if (track != null ? !track.equals(song.track) : song.track != null) return false;
        if (albumArtist != null ? !albumArtist.equals(song.albumArtist) : song.albumArtist != null) return false;
        if (time != null ? !time.equals(song.time) : song.time != null) return false;
        if (pos != null ? !pos.equals(song.pos) : song.pos != null) return false;
        if (id != null ? !id.equals(song.id) : song.id != null) return false;
        if (Name != null ? !Name.equals(song.Name) : song.Name != null) return false;
        return composer != null ? composer.equals(song.composer) : song.composer == null;

    }

    @Override
    public int hashCode() {
        int result = file != null ? file.hashCode() : 0;
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (track != null ? track.hashCode() : 0);
        result = 31 * result + (albumArtist != null ? albumArtist.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (pos != null ? pos.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (Name != null ? Name.hashCode() : 0);
        result = 31 * result + (composer != null ? composer.hashCode() : 0);
        return result;
    }

}
