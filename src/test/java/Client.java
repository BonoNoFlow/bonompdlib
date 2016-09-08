import com.bono.api.MPDClient;

import java.io.IOException;

/**
 * Created by hendriknieuwenhuis on 08/09/16.
 */
public class Client {

    private static final String HELP = "-h";
    private static final String STATUS = "--status";

    private MPDClient mpdClient;

    private String[] args;

    public Client(String[] args) {
        this.args = args;
        // obtain config host and port information.

        // init the client.
        mpdClient = new MPDClient("192.168.2.4", 6600);

        // check arg count.
        // no args give version and exit.
        if (args.length == 0) {
            // give version and exit.
            String version = "";
            try {
                version = mpdClient.getVersion();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            System.out.println("version: " + version);
            System.out.println("This is a mpd client.\nFor help with usage give '-h' as argument.");
            System.exit(0);
        }

        // read args.
        readArgs();
    }

    private void readArgs() {
        int argLength = args.length;

        //for (int i = 0; i < argLength; i++) {
        if (argLength == 1) {
            switch (args[0]) {
                case HELP:

                    // print all features.
                    System.out.println("features:\n\n"+STATUS+"    prints the status of");

                    // exit program.
                    System.exit(0);
                    break;
                case STATUS:
                    try {
                        mpdClient.initMonitor();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.print(mpdClient.getStatus().toString()+"\n");
                    System.exit(0);
                default:
                    break;
            }
        }
    }




    public static void main(String[] args) {
        new Client(args);
    }
}
