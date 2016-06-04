import com.bono.api.Config;
import com.bono.api.Connection;

/**
 * Created by hendriknieuwenhuis on 01/06/16.
 */
public class ConfigTest  {

    private static final String HOST = "host";
    private static final String PORT = "port";

    private Config config;

    public ConfigTest() {

        Connection connection = new Connection("192.168.2.4", 6600);
        config = new Config();
        config.setProperty(HOST, connection.getHost());
        config.setProperty(PORT, Integer.toString(connection.getPort()));
        try {
            config.saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

        config.clearProperties();

        try {
            config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Host: " + config.getProperty(HOST) + "\nPort: " + config.getProperty(PORT));
    }

    public static void main(String[] args) {
        new ConfigTest();
    }
}
