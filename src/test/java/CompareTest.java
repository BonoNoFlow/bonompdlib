import com.bono.api.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by bono on 8/29/16.
 */
public class CompareTest {

    public CompareTest() {
        List<Song> songs = new ArrayList<>();

        int[] ids = {12, 345, 23, 78,467, 378, 54, 9};

        songs = songList(ids);

        print(songs);

        Collections.sort(songs, Song.songComparator);



        print(songs);
    }

    private List<Song> songList(int[] ids) {
        List<Song> songList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            songList.add(newSong(ids[i]));
        }
        return songList;
    }

    private Song newSong(int id) {
        return new Song(null, null, null, null, null, -1, null, null, id, null,
                null, -1, -1L, null, -1);
    }

    private void print(List<Song> songList) {
        for (Song s : songList) {
            System.out.println(s.getId());
        }
    }

    public static void main(String[] args) {
        new CompareTest();
    }
}
