import com.bono.api.MPDStatus;
import com.bono.api.Reply;

import java.util.Iterator;

/**
 * Created by hendriknieuwenhuis on 11/04/16.
 */
public class Idle extends Thread {

    private MPDStatus status;

    public Idle(MPDStatus status) {
        super();
        this.status = status;
    }

    @Override
    public void run() {
        Reply reply = null;

        while (true) {
            System.out.println("new idle.");
            try {
                reply = new Reply(status.idle(null));
            } catch (Exception e) {
                break;
            }

            if (reply != null) {
                Iterator<String> i = reply.iterator();
                int c = 0;
                while (i.hasNext()) {
                    System.out.println(c + " " + i.next());
                }
            }


        }
    }
}
