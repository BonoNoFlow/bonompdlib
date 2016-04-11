import com.bono.api.Config;
import com.bono.api.DBExecutor;
import com.bono.api.MPDStatus;

/**
 * Created by hendriknieuwenhuis on 11/04/16.
 */
public class TestIdle {

    public static void main(String[] args) {
        DBExecutor dbExecutor = new DBExecutor(new Config("192.168.2.4", 6600));
        MPDStatus status = new MPDStatus(dbExecutor);
        try {
            status.populateStatus(status.status());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Idle(status).start();
    }
}
