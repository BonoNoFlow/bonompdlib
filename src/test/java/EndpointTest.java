import com.bono.api.*;
import com.bono.api.protocol.MPDPlayback;
import com.bono.api.protocol.MPDStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 22/04/16.
 */
public class EndpointTest {

    Endpoint endpoint = new Endpoint("192.168.2.4", 6600);

    public EndpointTest() {
        DefaultCommand defaultCommand = new DefaultCommand(MPDStatus.STATUS);
        List<Command> commands = new ArrayList<>();
        commands.add(new DefaultCommand(DefaultCommand.COMMAND_LIST_BEGIN));
        commands.add(new DefaultCommand(MPDPlayback.REPEAT, "1"));
        commands.add(new DefaultCommand(MPDPlayback.RANDOM, "0"));
        commands.add(new DefaultCommand("setvol", "9"));
        commands.add(new DefaultCommand(DefaultCommand.COMMAND_LIST_END));
        try {
            //endpoint.command(defaultCommand);
            String version = endpoint.getVersion();
            System.out.println(version);

            Collection<String> response = endpoint.commands(commands);
            for (String s : response) {
                System.out.println(s);
            }

            Collection<String> status = endpoint.command(defaultCommand);
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
