import com.bono.api.DefaultCommand;

/**
 * Created by hendriknieuwenhuis on 03/04/16.
 */
public class TestDefaultCommand {

    DefaultCommand command;

    public TestDefaultCommand() {
        command = new DefaultCommand("Hallo");
        //command.addArg("wereld!");
        //c/ommand.addArgs(new String[]{"Krijg", "de", "kolere!"});
        //command.addArg("Krijg");
        //command.addArg("de");
        //command.addArg("kolere!");
        System.out.print(new String(command.getCommandBytes()));
    }

    public static void main(String[] args) {
        new TestDefaultCommand();
    }
}
