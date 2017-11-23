package com.bono.api;

import java.util.*;

/**
 * Created by hendriknieuwenhuis on 29/07/15.
 */
public class Song implements Comparable<Song> {

    public static Comparator<Song> songComparator = new Comparator<Song>() {
        @Override
        public int compare(Song o1, Song o2) {
            return o1.compareTo(o2);
        }
    };

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
                    String pos = "";

                    // check if track is written as x/x.
                    int trackPos = line[1].lastIndexOf('/');
                    if (trackPos == -1) {
                        pos = line[1];
                    } else {
                        pos = line[1].substring(0, trackPos);
                    }

                    // catch if track number begins with character
                    // as in 'A1' or 'B3' for example.
                    try {
                        pTrack = Integer.parseInt(pos);
                    } catch (NumberFormatException nfe) {
                        System.out.println(nfe.getMessage());
                        // TODO a better solution? String instead of int? or stripping the chars?
                    }
                    break;
                    //int trackPos = line[1].lastIndexOf('/');
                    //if (trackPos == -1) {
                    //    pTrack = Integer.parseInt(line[1]);
                    //} else {
                    //    pTrack = Integer.parseInt(line[1].substring(0, trackPos));
                    //
                    //break;
                default:
                    System.out.println("Not a property: " + line[0]);
                    break;
            }
        }
        return new Song(pAlbum, pAlbumArtist, pArtist, pComposer, pDate, pDisc, pFilePath,
                pGenre, pId, pLastModified, pName, pPos, pTime, pTitle, pTrack);
    }

    /**
     * A song is compared to by its songid,
     * name and as last resort full path.
     *
     * @param o Song object to compare to.
     * @return
     */
    @Override
    public int compareTo(Song o) {
        int comp = 0;

        comp = getId() > o.getId() ? +1 : getId() < o.getId() ? -1 : 0;

        if (comp == 0) {
            comp = getName().compareTo(o.getName());
        }

        if (comp == 0) {
            comp = getFilePath().compareTo(o.getFilePath());
        }

        return comp;
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

    /**
     * Get the time as hh:mm:ss
     * @param totalSeconds seconds total as long
     * @return String, time formatted is hh:mm:ss
     */
    public static String getFormattedTime(long totalSeconds) {
        long secondsInHour = 3600L;
        long secondsInMinute = 60L;

        if (totalSeconds == -1L) {
            totalSeconds = 0L;
        }

        long hour = totalSeconds / secondsInHour;
        long remainingMin = totalSeconds%secondsInHour;
        long minutes =  remainingMin / secondsInMinute;
        long seconds = remainingMin%secondsInMinute;

        String result = null;
        if (hour == 0) {
            result = String.format("%02d:%02d", minutes, seconds);
        } else {
            result = String.format("%02d:%02d:%02d", hour, minutes, seconds);
        }
        return result;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (disc != song.disc) return false;
        if (id != song.id) return false;
        if (pos != song.pos) return false;
        if (time != song.time) return false;
        if (track != song.track) return false;
        if (album != null ? !album.equals(song.album) : song.album != null) return false;
        if (albumArtist != null ? !albumArtist.equals(song.albumArtist) : song.albumArtist != null) return false;
        if (artist != null ? !artist.equals(song.artist) : song.artist != null) return false;
        if (composer != null ? !composer.equals(song.composer) : song.composer != null) return false;
        if (date != null ? !date.equals(song.date) : song.date != null) return false;
        if (filePath != null ? !filePath.equals(song.filePath) : song.filePath != null) return false;
        if (genre != null ? !genre.equals(song.genre) : song.genre != null) return false;
        if (lastModified != null ? !lastModified.equals(song.lastModified) : song.lastModified != null) return false;
        if (name != null ? !name.equals(song.name) : song.name != null) return false;
        return title != null ? title.equals(song.title) : song.title == null;

    }

    @Override
    public int hashCode() {
        int result = album != null ? album.hashCode() : 0;
        result = 31 * result + (albumArtist != null ? albumArtist.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (composer != null ? composer.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + disc;
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + pos;
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + track;
        return result;
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
