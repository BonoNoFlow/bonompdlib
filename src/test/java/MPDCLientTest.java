import com.bono.api.ChangeListener;
import com.bono.api.MPDClient;
import com.bono.api.Player;
import com.bono.api.Server;

import java.io.IOException;
import java.util.EventObject;


public class MPDCLientTest {

    MPDClient mpdClient;

    static Object lock = new Object();

    public MPDCLientTest() {
        mpdClient = new MPDClient();
        mpdClient.setHost("192.168.2.4");
        mpdClient.setPort(6600);
        mpdClient.getServerMonitor().addMonitorListener(new Listener());
        synchronized (lock) {
            try {
                mpdClient.initMonitor();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            mpdClient.runMonitor();
        }
    }

    MPDClient getClient() {
        return mpdClient;
    }

    void print(Server server) {
        System.out.println(server.getHost() + " " + server.getPort());
    }

    class Listener implements ChangeListener {

        @Override
        public void stateChanged(EventObject e) {
            System.out.println(e.getSource().toString());
            System.out.println(mpdClient.getStatus().toString());
        }
    }

    public static void main(String[] args) {
        MPDCLientTest test = new MPDCLientTest();
        test.print(test.getClient());

        if (test.mpdClient.getClientExecutor() == null) {
            System.out.println("null");
        }

        synchronized (lock) {
            try {
                //test.mpdClient.getClientExecutor().execute(Player.STOP);
                test.mpdClient.getPlayer().play();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
