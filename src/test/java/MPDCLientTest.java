import com.bono.api.MPDClient;
import com.bono.api.Server;


public class MPDCLientTest {

    MPDClient mpdClient;

    public MPDCLientTest() {
        mpdClient = new MPDClient();
        mpdClient.setHost("192.168.2.4");
        mpdClient.setPort(6600);
    }

    MPDClient getClient() {
        return mpdClient;
    }

    void print(Server server) {
        System.out.println(server.getHost() + " " + server.getPort());
    }

    public static void main(String[] args) {
        MPDCLientTest test = new MPDCLientTest();
        test.print(test.getClient());
    }
}
