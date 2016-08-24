package com.bono.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 29/07/15.
 */
public class Song {

    protected String album;
    protected String albumArtist;
    protected String artist;
    protected String composer;
    protected String date;    // maybe integer or long
    protected int disc;
    protected String filePath;
    protected String genre;
    protected int id;
    protected String lastModified;  // maybe removed, not useful?.
    protected String name;
    protected int pos;
    protected long time;
    protected String title;
    protected int track;

    @Deprecated
    protected List<ChangeListener> listeners = new ArrayList<>(); // remove listeners

    public Song(String album, String albumArtist, String artist, String composer,
                String date, int disc, String filePath, String genre, int id,
                String lastModified, String name, int pos, long time,
                String title, int track) {
        this.album = album;
        this.albumArtist = albumArtist;
        this.artist = artist;
        this.composer = composer;
        this.date = date;
        this.disc = disc;
        this.filePath = filePath;
        this.genre = genre;
        this.id = id;
        this.lastModified = lastModified;
        this.name = name;
        this.pos = pos;
        this.time = time;
        this.title = title;
        this.track = track;
    }





    public static Song createSong(final Collection<String> entry) {

        String pAlbum = null;
        String pAlbumArtist = null;
        String pArtist = null;
        String pComposer = null;
        String pDate = null;
        int pDisc = -1;
        String pFilePath = null;
        String pGenre = null;
        int pId = -1;
        String pLastModified = null;
        String pName = null;
        int pPos = -1;
        long pTime = -1L;
        String pTitle = null;
        int pTrack = -1;

        Iterator<String> i = entry.iterator();
        while (i.hasNext()) {
            String[] line = i.next().split(": ");
            switch (line[0]) {

                case "Album":
                    pAlbum = line[1];
                    break;
                case "AlbumArtist":
                    pAlbumArtist = line[1];
                case "Artist":
                    pArtist = line[1];;
                    break;
                case "Composer":
                    pComposer = line[1];
                    break;
                case "Date":
                    pDate = line[1];
                    break;
                case "Disc":
                    int discPos = line[1].lastIndexOf('/');
                    if (discPos == -1) {
                        pDisc = Integer.parseInt(line[1]);
                    } else {
                        pDisc = Integer.parseInt(line[1].substring(discPos + 1));
                    }
                    break;
                case "file":
                    pFilePath = line[1];
                    break;
                case "Genre":
                    pGenre = line[1];
                    break;
                case "Id":
                    pId = Integer.parseInt(line[1]);
                    break;
                case "Last-Modified":
                    pLastModified = line[1];
                    break;
                case "Name":
                    pName = line[1];
                    break;
                case "Pos":
                    pPos = Integer.parseInt(line[1]);
                    break;
                case "Time":
                    pTime = Integer.parseInt(line[1]);
                    break;
                case "Title":

                    pTitle = line[1];

                    break;
                case "Track":
                    int trackPos = line[1].lastIndexOf('/');
                    if (trackPos == -1) {
                        pTrack = Integer.parseInt(line[1]);
                    } else {
                        pTrack = Integer.parseInt(line[1].substring(trackPos + 1));
                    }
                    break;
                default:
                    System.out.println("Not a property: " + line[0]);
                    break;
            }
        }
        return new Song(pAlbum, pAlbumArtist, pArtist, pComposer, pDate, pDisc, pFilePath,
                pGenre, pId, pLastModified, pName, pPos, pTime, pTitle, pTrack);
    }



    @Deprecated
    protected void fireListeners() {
        if (listeners.size() > 0) {
            for (ChangeListener listener : listeners) {
                listener.stateChanged(new ChangeEvent(this));
            }
        }
    }

    @Deprecated
    public void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public String getAlbum() {
        return album;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public String getArtist() {
        return artist;
    }

    public String getComposer() {
        return composer;
    }

    public String getDate() {
        return date;
    }

    public int getDisc() {
        return disc;
    }

    public String getFileName() {
        int pos = filePath.lastIndexOf('/');
        if (pos == -1) {
            return filePath;
        }
        return filePath.substring(pos + 1);
    }

    public String getFilePath() {
        return filePath;
    }

    public String getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getName() {
        if (name == null || name.isEmpty()) {
            return getFileName();
        }
        return name;
    }

    public int getPos() {
        return pos;
    }

    public long getTime() {
        return time;
    }

    public String getTitle() {
        if (title == null || title.isEmpty()) {
            return filePath;
        }
        return title;
    }

    public int getTrack() {
        return track;
    }

    @Override
    public String toString() {
        return "Song{" +
                "album='" + album + '\'' +
                ", albumArtist='" + albumArtist + '\'' +
                ", artist='" + artist + '\'' +
                ", composer='" + composer + '\'' +
                ", date='" + date + '\'' +
                ", disc='" + disc + '\'' +
                ", filepath='" + filePath + '\'' +
                ", genre='" + genre + '\'' +
                ", id=" + id +
                ", lastModified='" + lastModified + '\'' +
                ", name='" + name + '\'' +
                ", pos=" + pos +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", track=" + track +
                '}';
    }
}
