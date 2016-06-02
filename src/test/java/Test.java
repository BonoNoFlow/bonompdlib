import com.bono.api.Connection;
import com.bono.api.Config;
import com.bono.api.DBExecutor;
import com.bono.api.Status;

/**
 * Created by hendriknieuwenhuis on 18/05/16.
 */
public class Test {

    protected DBExecutor dbExecutor;
    protected Status status;

    public Test() {
        dbExecutor = new DBExecutor(new Connection("192.168.2.4", 6600));
        status = new Status(dbExecutor);
        try {
            status.populate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
