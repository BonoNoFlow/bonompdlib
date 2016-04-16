import com.bono.api.*;

/**
 * Created by hendriknieuwenhuis on 03/04/16.
 */
public class TestDefaultCommand {

    DefaultCommand command;

    public TestDefaultCommand() {
        command = new DefaultCommand(MPDStatus.STATUS);
        //command.addArg("wereld!");
        //c/ommand.addArgs(new String[]{"Krijg", "de", "kolere!"});
        //command.addArg("Krijg");
        //command.addArg("de");
        //command.addArg("kolere!");
        //System.out.print(new String(command.getCommandBytes()) + "werled!");
        DBExecutor dbExecutor = new DBExecutor(new Config("192.168.2.4", 6600));

        try {
            System.out.println(dbExecutor.execute(command));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TestDefaultCommand();
    }
}
