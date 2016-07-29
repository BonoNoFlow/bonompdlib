import com.bono.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bono on 7/29/16.
 */
public class TestClientExecutor {


    public TestClientExecutor() {
        ClientExecutor executor = new ClientExecutor("192.168.2.4", 6600, 4000);

        DefaultCommand defaultCommand = new DefaultCommand(Status.STATUS);
        List<Command> commands = new ArrayList<>();
        commands.add(new DefaultCommand(DefaultCommand.COMMAND_LIST_BEGIN));
        commands.add(new DefaultCommand(Playback.REPEAT, "1"));
        commands.add(new DefaultCommand(Playback.RANDOM, "0"));
        commands.add(new DefaultCommand("setvol", "0"));
        commands.add(new DefaultCommand(DefaultCommand.COMMAND_LIST_END));


        try {


            List<String> response = executor.executeList(commands);
            for (String s : response) {
                System.out.println(s);
            }

            List<String> status = executor.execute(defaultCommand);
            for (String s : status) {
                System.out.println(s);
            }
            //} catch (ACKException ack) {
            //    ack.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }
        executor.shutdownExecutor();
    }

    public static void main(String[] args) {
        new TestClientExecutor();
    }
}
