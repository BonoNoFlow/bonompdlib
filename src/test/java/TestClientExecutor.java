import com.bono.api.*;
import com.bono.api.protocol.MPDPlayback;
import com.bono.api.protocol.MPDStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bono on 7/29/16.
 */
public class TestClientExecutor {


    public TestClientExecutor() {
        ClientExecutor executor = new ClientExecutor("192.168.2.4", 6600, 4000);

        DefaultCommand defaultCommand = new DefaultCommand(MPDStatus.STATUS);
        Collection<Command> commands = new ArrayList<>();
        commands.add(new DefaultCommand(DefaultCommand.COMMAND_LIST_BEGIN));
        commands.add(new DefaultCommand(MPDPlayback.REPEAT, "1"));
        commands.add(new DefaultCommand(MPDPlayback.RANDOM, "0"));
        commands.add(new DefaultCommand("setvol", "0"));
        commands.add(new DefaultCommand(DefaultCommand.COMMAND_LIST_END));


        try {


            Collection<String> response = executor.executeList(commands);
            for (String s : response) {
                System.out.println(s);
            }

            Collection<String> status = executor.execute(defaultCommand);
            for (String s : status) {
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        executor.shutdownExecutor();
    }

    public static void main(String[] args) {
        new TestClientExecutor();
    }
}
