import com.bono.api.ACKException;
import com.bono.api.Artist;
import com.bono.api.DefaultCommand;
import com.bono.api.Endpoint;
import com.bono.api.protocol.MPDDatabase;

import java.io.IOException;
import java.util.*;

/**
 * Created by hendriknieuwenhuis on 30/08/16.
 */
public class ArtistTest {

    static final String ARTIST = "artist";

    Endpoint endpoint = new Endpoint("192.168.2.4", 6600);


    TreeSet<Artist> artists;

    public ArtistTest() {

        try {
            artists = artists(endpoint.command(new DefaultCommand(MPDDatabase.LIST, "artist"), 4000));
        } catch (ACKException ack) {
            ack.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        print(artists);

    }

    private void print(TreeSet<Artist> tree) {
        for (Artist a : tree) {
            System.out.println(a.getName());
        }
    }

    private TreeSet<Artist> artists(Collection<String> collection) {

        TreeSet<Artist> tree = new TreeSet<>();
        int pos = 0;
        for (String s : collection) {
            pos = s.lastIndexOf(' ');
            if (pos > -1) {

                tree.add(new Artist(s.substring(pos + 1)));
            }
        }
        return tree;
    }

    public static void main(String[] args) {
        new ArtistTest();
    }
}
