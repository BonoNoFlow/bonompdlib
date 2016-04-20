import com.bono.api.Command;
import com.bono.api.CommandList;
import com.bono.api.DefaultCommand;

/**
 * Created by hendriknieuwenhuis on 20/04/16.
 */
public class TestCommandsList {

    public static void main(String[] args) {

        CommandList commandList = new CommandList();

        DefaultCommand defaultCommand;

        defaultCommand = new DefaultCommand("hallo");
        commandList.addCommand(defaultCommand);
        defaultCommand = new DefaultCommand("wereld");
        commandList.addCommand(defaultCommand);
        defaultCommand = new DefaultCommand(".");
        commandList.addCommand(defaultCommand);


    }
}
