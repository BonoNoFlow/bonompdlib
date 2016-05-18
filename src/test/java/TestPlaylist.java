import com.bono.api.Playlist;
import com.bono.api.PlaylistControl;

/**
 * Created by hendriknieuwenhuis on 18/05/16.
 */
public class TestPlaylist extends Test {

    private Playlist playlist;
    private PlaylistControl playlistControl;

    public TestPlaylist() {
        super();
        playlistControl = new PlaylistControl(this.dbExecutor);
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
