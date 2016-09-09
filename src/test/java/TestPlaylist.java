import com.bono.api.*;
import com.bono.api.protocol.Tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bono on 9/9/16.
 */
public class TestPlaylist {

    MPDClient client = new MPDClient("192.168.2.4", 6600);

    public TestPlaylist() {
        ClientExecutor clientExecutor = client.getClientExecutor();
        Playlist playlist = client.getPlaylist();

        //List<String> feedback = null;
        SongList songs = null;
        try {
            songs = playlist.playlistSearch(Tags.ANY,"mysteries");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (Song s : songs.getAsArray()) {
            System.out.println(s.toString());
        }
        clientExecutor.shutdownExecutor();
    }

    public static void main(String[] args) {
        new TestPlaylist();
    }
}
