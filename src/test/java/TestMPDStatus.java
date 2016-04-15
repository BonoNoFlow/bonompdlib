import com.bono.api.Config;
import com.bono.api.DBExecutor;
import com.bono.api.MPDStatus;

/**
 * Created by hendriknieuwenhuis on 15/04/16.
 */
public class TestMPDStatus {

    public TestMPDStatus() {
        //DBExecutor dbExecutor = new DBExecutor(new Config("192.168.2.4", 6600));
        MPDStatus status = new MPDStatus(new DBExecutor(new Config("192.168.2.4", 6600)));

        try {
            status.populateStatus(status.status());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(status.getStatus().toString());
    }

    public static void main(String[] args) {
        new TestMPDStatus();
    }
}
