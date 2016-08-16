import com.bono.api.ChangeListener;
import com.bono.api.ClientExecutor;
import com.bono.api.DefaultCommand;
import com.bono.api.Status;
import com.bono.api.protocol.MPDStatus;

import java.util.EventObject;

/**
 * Created by bono on 8/16/16.
 */
public class TestStatus {
    Status status = new Status();
    ClientExecutor clientExecutor = new ClientExecutor("192.168.2.4", 6600, 4000);
    FirstChangelistener f = new FirstChangelistener();
    SecondChangelistener s = new SecondChangelistener();

    public TestStatus() {
        status.addListener(f);
        status.addListener(s);
        populateStatus();
        status.removeListener(f);
        populateStatus();
    }

    private void populateStatus() {
        try {
            status.populate(clientExecutor.execute(new DefaultCommand(MPDStatus.STATUS)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class FirstChangelistener implements ChangeListener {
        @Override
        public void stateChanged(EventObject e) {
            System.out.println("first");
        }
    }

    private class SecondChangelistener implements ChangeListener {
        @Override
        public void stateChanged(EventObject e) {
            System.out.println("second");
        }
    }

    public static void main (String[] args) {
        new TestStatus();
    }
}
