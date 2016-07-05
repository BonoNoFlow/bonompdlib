import com.bono.api.*;

import java.util.Iterator;

/**
 * Created by hendriknieuwenhuis on 20/04/16.
 */
public class TestCommandsList {

    public static void main(String[] args) {

        CommandList commandList = CommandList.commandList(CommandList.COMMAND_LIST_BEGIN);

        DefaultCommand defaultCommand;

        defaultCommand = new DefaultCommand(Playback.NEXT);
        commandList.addCommand(defaultCommand);
        defaultCommand = new DefaultCommand(Playlist.ADDID, "123", "next");
        commandList.addCommand(defaultCommand);
        defaultCommand = new DefaultCommand(".");
        commandList.addCommand(defaultCommand);
        commandList.commandlistEnd();

        //System.out.println(new String(commandList.getCommandListBytes()));
        Iterator<Command> i = commandList.iterator();
        while (i.hasNext()) {
            Command command = i.next();
            System.out.print(new String(command.getCommandBytes()));
        }
    }
}
