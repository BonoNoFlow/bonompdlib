import com.bono.api.*;
import com.bono.api.protocol.MPDPlaylist;
import com.bono.api.protocol.MPDStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by bono on 8/25/16.
 */
public class Timetest {



    public Timetest() {
        Collection<String> response;

        response = new ArrayList<>();

        try {
            response = new Endpoint("192.168.2.4", 6600).command(new DefaultCommand(MPDPlaylist.PLAYLISTINFO));
        } catch (ACKException ack) {
            ack.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Playlist playlist = new Playlist();
        playlist.populate(response);
        for (int i = 0; i < playlist.getSize(); i++) {
            Song song = playlist.getSong(i);
            System.out.println(song.getFormattedTime(song.getTime()));
        }
    }

    public static void main(String[] args) {
        new Timetest();
    }
}
