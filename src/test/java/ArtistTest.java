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

    static final String ARTIST = "Artist: ";

    Endpoint endpoint;


    TreeSet<Artist> artists;

    public ArtistTest() {
        endpoint = new Endpoint("192.168.2.4", 6600);
        try {
            artists = artists(endpoint.command(new DefaultCommand(MPDDatabase.LIST, "artist")));
        } catch (ACKException ack) {
            ack.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        print(artists);

        endpoint = new Endpoint("192.168.2.4", 6600);
        Collection<String> reply = new ArrayList<>();
        try {
            reply = endpoint.command(new DefaultCommand(MPDDatabase.LIST, "album", ""));
        } catch (ACKException ack) {
            ack.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (String s : reply) {
            System.out.println(s);
        }

    }

    private void print(TreeSet<Artist> tree) {
        for (Artist a : tree) {
            System.out.println(a.getName());
        }
    }

    private TreeSet<Artist> artists(Collection<String> collection) {

        TreeSet<Artist> tree = new TreeSet<>();
        int pos = ARTIST.length();
        for (String s : collection) {
            if (s.startsWith(ARTIST)) {


                tree.add(new Artist(s.substring(pos)));
            }
        }
        return tree;
    }

    public static void main(String[] args) {
        new ArtistTest();
    }
}
