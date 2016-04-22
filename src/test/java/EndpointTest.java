import com.bono.api.*;

/**
 * Created by hendriknieuwenhuis on 22/04/16.
 */
public class EndpointTest {

    Endpoint endpoint = new Endpoint("192.168.2.4", 6600);

    public EndpointTest() {
        DefaultCommand defaultCommand = new DefaultCommand(PlaylistControl.ADDID);
        try {
            endpoint.command(defaultCommand);
        } catch (ACKException ack) {
            ack.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EndpointTest();
    }
}
