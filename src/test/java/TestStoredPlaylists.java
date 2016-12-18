import com.bono.api.MPDClient;
import com.bono.api.Song;
import com.bono.api.SongList;
import com.bono.api.StoredPlaylists;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bono on 12/18/16.
 */
public class TestStoredPlaylists {

    public static void main(String[] args) {
        MPDClient mpdClient = new MPDClient("192.168.2.4", 6600);
        StoredPlaylists storedPlaylists = mpdClient.getStoredPlaylists();

        try {
            SongList list = storedPlaylists.listplaylist("earthless");
            Iterator<Song> i = list.iterator();
            while (i.hasNext()) {
                System.out.println((i.next()).getFileName());
            }
        } catch (IOException ioe) {

        }
    }
}
