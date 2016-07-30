import com.bono.api.*;

import java.util.EventObject;
import java.util.List;

/**
 * Created by bono on 7/30/16.
 */
public class TestIdle {

    ClientExecutor clientExecutor = new ClientExecutor("192.168.2.4", 6600, 4000);
    Status status = new Status();

    public TestIdle() {
        IdleRunner idle = new IdleRunner(clientExecutor);

        idle.addListener(new ChangeListener() {
            @Override
            public void stateChanged(EventObject e) {
                System.out.println((String)e.getSource());
            }
        });

        /*
        Thread thread = new Thread(() -> {
            try {
                idle.runIdle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();*/

        idle.start();

        while (true) {

            //List<String> statusQuery = null;
            try {
                status.populate(clientExecutor.execute(new DefaultCommand(Status.STATUS)));
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(status.getState());

            if (status.getState().equals("play")) {
                try {
                    clientExecutor.execute(new DefaultCommand(Playback.PAUSE, "1"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    clientExecutor.execute(new DefaultCommand(Playback.PAUSE, "0"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        new TestIdle();
    }
}
