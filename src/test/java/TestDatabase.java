import com.bono.api.Config;
import com.bono.api.DBExecutor;
import com.bono.api.Database;

/**
 * Created by hendriknieuwenhuis on 14/04/16.
 */
public class TestDatabase {

    public static void main(String[] args) {

        DBExecutor dbExecutor = new DBExecutor(new Config("192.168.2.4", 6600));

        Database database = new Database(dbExecutor);

        try {
            System.out.println(database.listfiles(null));
        } catch(Exception e) {
            e.printStackTrace();
        }
        dbExecutor = null;
    }
}
