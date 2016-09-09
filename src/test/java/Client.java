import com.bono.api.MPDClient;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by hendriknieuwenhuis.
 *
 * process argument.
 *
 * when needed read config file for host and port.
 *  - no file try localhost and standard port.
 *    if fails give message to config client, end app.
 *
 *  - yes config file test configuration.
 *    if fails give message to config client, end app.
 *    on succes continue.
 *
 * Execute argument.
 *
 * Give feedback.
 */
public class Client {

    private final String configFile = System.getProperty("user.home") + "/.zeroclient/zero.conf";

    private static final String HELP = "-h";
    private static final String STATUS = "--status";

    private MPDClient mpdClient;

    private String host;
    private String port;
    private String version;

    private String[] args;

    public Client(String[] args) {
        this.args = args;

        // obtain config host and port information.
        if (!readConfig()) {
            // tell user to config correctly.
            System.exit(0);
        } else {
            if (!testConfig()) {
                // tell user to config correctly.
                System.exit(0);
            }
        }

        // by now mpdclient is created.

        // check arg count.
        // no args give version and exit.
        if (args.length == 0) {
            // give version and exit.
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
            if (args[0].contains("=")) {
                String[] arg = args[0].split("=");
                switch (arg[0]) {
                    case "--config":
                        writeConfig(arg[1]);
                        break;
                    default:
                        System.out.println(arg[0] + " " + arg[1]);
                        break;
                }
            } else {
                switch (args[0]) {
                    case HELP:

                        // print all features.
                        System.out.println("features:\n\n" + STATUS + "    prints the status of");

                        // exit program.
                        System.exit(0);
                        break;
                    case STATUS:
                        try {
                            mpdClient.initMonitor();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.print(mpdClient.getStatus().toString() + "\n");
                        System.exit(0);
                    default:
                        break;
                }
            }
        }
    }

    private boolean readConfig() {
        File f = new File(configFile);
        String conf = null;
        if (f.exists()) {
            Path file = Paths.get(configFile);
            try {
                conf = new String(Files.readAllBytes(file));
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        if (conf != null) {
            if (conf.contains(":")) {
                String[] confA = conf.split(":");
                host = confA[0];
                port = confA[1];
                return true;
            }
        }
        return false;
    }

    private boolean testConfig() {
        mpdClient = new MPDClient(host, Integer.parseInt(port));
        try {
            version = mpdClient.getVersion();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (version.startsWith("OK")) {
            return true;
        }
        return false;
    }

    private void writeConfig(String config) {
        File f = new File(configFile);
        try {
            if (!f.getParentFile().exists()) {
                if (!f.getParentFile().mkdirs()) {
                    System.out.println("Dirs could not be written");
                }
            }
            if (f.createNewFile()) {
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        Path file = Paths.get(configFile);
        if (Files.isWritable(file)) {
            try {
                Files.write(file, config.getBytes(Charset.forName("UTF-8")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Not writable");
        }
    }

    private boolean isFileExisting(String file) {
        File f = new File(file);
        return f.exists();
    }


    public static void main(String[] args) {
        new Client(args);
    }
}
