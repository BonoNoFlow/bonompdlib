import com.bono.api.CommandList;
import com.bono.api.DefaultCommand;
import com.bono.api.PlayerControl;
import com.bono.api.PlaylistControl;

/**
 * Created by hendriknieuwenhuis on 20/04/16.
 */
public class TestCommandsList {

    public static void main(String[] args) {

        CommandList commandList = CommandList.commandList(CommandList.COMMAND_LIST_BEGIN);

        DefaultCommand defaultCommand;

        defaultCommand = new DefaultCommand(PlayerControl.NEXT);
        commandList.addCommand(defaultCommand);
        defaultCommand = new DefaultCommand(PlaylistControl.ADDID, "123", "next");
        commandList.addCommand(defaultCommand);
        defaultCommand = new DefaultCommand(".");
        commandList.addCommand(defaultCommand);
        commandList.commandlistEnd();

        System.out.println(new String(commandList.getCommandListBytes()));
    }
}
