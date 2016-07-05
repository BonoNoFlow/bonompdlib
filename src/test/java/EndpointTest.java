import com.bono.api.*;

/**
 * Created by hendriknieuwenhuis on 22/04/16.
 */
public class EndpointTest {

    Endpoint endpoint = new Endpoint("192.168.2.5", 6600);

    public EndpointTest() {
        //DefaultCommand defaultCommand = new DefaultCommand(Playlist.ADDID);
        try {
            //endpoint.command(defaultCommand);
            String version = endpoint.getVersion(4000);
            System.out.println(version);
        //} catch (ACKException ack) {
        //    ack.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EndpointTest();
    }
}
