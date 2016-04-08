import com.bono.api.Config;
import com.bono.api.DBExecutor;

/**
 * Created by hendriknieuwenhuis on 08/04/16.
 */
abstract class Test {

    protected DBExecutor dbExecutor;

    public Test(Config config) {
        dbExecutor = new DBExecutor(config);
    }

    abstract void test();
}
