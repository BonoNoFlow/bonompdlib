import com.bono.api.*;
import com.bono.api.protocol.MPDPlaylist;
import com.bono.api.protocol.MPDStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.List;

/**
 * Created by bono on 8/16/16.
 */
public class TestStatus {
    //Status status = new Status();
    //ClientExecutor clientExecutor = new ClientExecutor("192.168.2.4", 6600, 4000);
    //FirstChangelistener f = new FirstChangelistener();
    //SecondChangelistener s = new SecondChangelistener();

    public TestStatus() {
        //status.addListener(f);
        //status.addListener(s);
        populateStatus();
        //status.removeListener(f);
        //populateStatus();
    }

    private void populateStatus() {
        Collection<String> response = new ArrayList<>();
        Status status = new Status();

        try {
            response = new Endpoint("192.168.2.4", 6600).command(new DefaultCommand(MPDStatus.STATUS), 4000);
        } catch (ACKException ack) {
            ack.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        status.populate(response);

        response = new ArrayList<>();

        try {
            response = new Endpoint("192.168.2.4", 6600).command(new DefaultCommand(MPDPlaylist.PLAYLISTINFO), 4000);
        } catch (ACKException ack) {
            ack.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        for (String s : response) {
            System.out.println(s);
        }
        Playlist playlist = new Playlist();
        playlist.populate(response);
        System.out.println(playlist.toString());
    }

    private class FirstChangelistener implements ChangeListener {
        @Override
        public void stateChanged(EventObject e) {
            System.out.println("first");
        }
    }

    private class SecondChangelistener implements ChangeListener {
        @Override
        public void stateChanged(EventObject e) {
            System.out.println("second");
        }
    }

    public static void main (String[] args) {
        new TestStatus();
    }
}
