import com.bono.api.Config;

/**
 * Created by hendriknieuwenhuis on 01/06/16.
 */
public class ConfigTest {

    public ConfigTest() {
        Config config = new Config("192.168.2.4", 6600);
        try {
            config.saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

        config = null;
        Config newConfig = new Config();
        try {
            newConfig.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Host: " + newConfig.getHost() + "\nPort: " + newConfig.getPort());
    }

    public static void main(String[] args) {
        new ConfigTest();
    }
}
