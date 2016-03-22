import com.bono.api.Config;

/**
 * Created by hendriknieuwenhuis on 22/03/16.
 */
public class ConfigTest {

    public static void main(String[] args) {
        Config config = new Config();
        try {
            config.loadParams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
