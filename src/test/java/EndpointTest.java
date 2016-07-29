import com.bono.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 22/04/16.
 */
public class EndpointTest {

    Endpoint endpoint = new Endpoint("192.168.2.4", 6600);

    public EndpointTest() {
        DefaultCommand defaultCommand = new DefaultCommand(Status.STATUS);
        List<Command> commands = new ArrayList<>();
        commands.add(new DefaultCommand(DefaultCommand.COMMAND_LIST_BEGIN));
        commands.add(new DefaultCommand(Playback.REPEAT, "0"));
        commands.add(new DefaultCommand(Playback.RANDOM, "1"));
        commands.add(new DefaultCommand("setvol", "90"));
        commands.add(new DefaultCommand(DefaultCommand.COMMAND_LIST_END));
        try {
            //endpoint.command(defaultCommand);
            String version = endpoint.getVersion(4000);
            System.out.println(version);

            List<String> response = endpoint.commands(commands, 4000);
            for (String s : response) {
                System.out.println(s);
            }

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
