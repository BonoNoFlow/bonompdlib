import com.bono.api.ClientExecutor;
import com.bono.api.Player;

import java.io.IOException;

/**
 * Created by bono on 9/4/16.
 */
public class TestPlayer {

    public static void main(String[] args) {
        ClientExecutor clientExecutor = new ClientExecutor("192.168.2.4", 6600, 10000);
        Player player = new Player(clientExecutor);

        try {
            player.sendCommandList().add(Player.CONSUME, Player.booleanToString(false))
                    .add(Player.RANDOM, Player.booleanToString(false)).send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientExecutor.shutdownExecutor();
    }
}
