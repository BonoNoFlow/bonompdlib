import com.bono.api.ClientExecutor;
import com.bono.api.DefaultCommand;
import com.bono.api.protocol.MPDDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bono on 7/31/16.
 */
public class TestDatabase {

    public TestDatabase() {
        ClientExecutor clientExecutor = new ClientExecutor("192.168.2.4", 6600, 4000);

        List<String> results = new ArrayList<>();

        DefaultCommand defaultCommand = new DefaultCommand(MPDDatabase.FIND, "album", "\"Avalanche\"");

        System.out.println(new String(defaultCommand.getCommandBytes()));
        try {
            results = clientExecutor.execute(defaultCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String s : results) {
            System.out.println(s);
        }
        clientExecutor.shutdownExecutor();
    }

    public static void main(String[] args) {
        new TestDatabase();
    }
}
