import com.bono.api.Config;
import com.bono.api.DBExecutor;
import com.bono.api.Playlist;
import com.bono.api.PlaylistControl;

/**
 * Created by hendriknieuwenhuis on 18/05/16.
 */
public class TestPlaylist {

    private Playlist playlist;
    private PlaylistControl playlistControl;
    private DBExecutor dbExecutor;

    public TestPlaylist() {
        dbExecutor = new DBExecutor(new Config("192.168.2.4", 6600));
        playlistControl = new PlaylistControl(dbExecutor);
        playlist = new Playlist();

        String output = null;
        try {
            output = playlistControl.playlistinfo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        playlist.populate(output);

        System.exit(0);
    }

    public static void main (String[] args) {
        new TestPlaylist();
    }
}
