import com.bono.api.*;

import java.util.EventObject;


/**
 * Created by hendriknieuwenhuis on 10/05/16.
 */
public class IdleTester {

    private Status status;

    public void test() {
        status = new Status(new DBExecutor(new Config("192.168.2.4", 6600)));
        IdleRunnable idleRunnable = new IdleRunnable(status);
        Thread thread = new Thread(idleRunnable);
        thread.start();
    }

    private class IdleRunnable implements Runnable {

        private Idle idle;

        public IdleRunnable(Status status) {
            idle = new Idle(status);
            idle.addListener(new IdleListener());
        }
        @Override
        public void run() {
            try {
                idle.runIdle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class IdleListener implements ChangeListener {


        @Override
        public void stateChanged(EventObject e) {

        }
    }


}
