import com.bono.api.Config;

/**
 * Created by hendriknieuwenhuis on 01/06/16.
 */
public class ConfigTest  {

    private static final String HOST = "host";
    private static final String PORT = "port";

    private Config config;

    public ConfigTest() {


        config = new Config();
        config.setProperty(HOST, "192.168.2.4");
        config.setProperty(PORT, Integer.toString(6600));
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
