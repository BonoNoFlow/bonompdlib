import com.bono.api.*;

import java.util.List;

/**
 * Created by hendriknieuwenhuis on 22/04/16.
 */
public class EndpointTest {

    Endpoint endpoint = new Endpoint("192.168.2.4", 6600);

    public EndpointTest() {
        DefaultCommand defaultCommand = new DefaultCommand(Status.STATUS);
        try {
            //endpoint.command(defaultCommand);
            String version = endpoint.getVersion(4000);
            System.out.println(version);
            List<String> status = endpoint.command(defaultCommand, 4000);
            for (String s : status) {
                System.out.println(s);
            }
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
