import com.bono.api.MPDStatus;

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
        String reply = "";

        while (true) {

            try {
                reply = status.idle(null);
            } catch (Exception e) {
                break;
            }
            System.out.println(reply);
            reply = "";
        }
    }
}
