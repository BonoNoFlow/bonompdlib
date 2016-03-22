import com.bono.api.MPDEndpoint;

/**
 * Created by bono on 3/22/16.
 */
public class LibTest {

    public static void main(String[] args) {
        MPDEndpoint endpoint = new MPDEndpoint("192.168.2.4", 6600);
        try {
            System.out.println(endpoint.getVersion());
           // endpoint.command(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
