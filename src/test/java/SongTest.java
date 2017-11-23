import com.bono.api.Song;

import java.util.ArrayList;

public class SongTest {

    public static void main(String[] args) {
        ArrayList<String> track = new ArrayList<>();
        track.add("Track: C3");
        Song song = Song.createSong(track);
        System.out.println(song.getTrack());
    }
}
