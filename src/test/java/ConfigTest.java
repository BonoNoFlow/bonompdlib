import com.bono.api.Config;
import com.bono.api.Connection;

/**
 * Created by hendriknieuwenhuis on 01/06/16.
 */
public class ConfigTest  {

    private Config config;

    public ConfigTest() {

        Connection connection = new Connection("192.168.2.4", 6600);
        config = new Config();
        config.setProperty(Connection.HOST, connection.getHost());
        config.setProperty(Connection.PORT, Integer.toString(connection.getPort()));
        try {
            config.saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

        config.clearProperties();
        //Config newConfig = new Config();
        try {
            config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Host: " + config.getProperty(Connection.HOST) + "\nPort: " + config.getProperty(Connection.PORT));
    }

    public static void main(String[] args) {
        new ConfigTest();
    }
}
