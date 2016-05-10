package com.bono.api;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by hendriknieuwenhuis on 09/05/16.
 */
public class Idle {

    private final static String CHANGED_STATUS = "changed: status\n";

    private ArrayList<ChangeListener> changeListeners = new ArrayList<>();

    private Status status;

    public Idle(Status status) {
        this.status = status;
    }

    public void addListener(ChangeListener listener) {
        changeListeners.add(listener);
    }

    public void runIdle() throws Exception {
        String feedback = null;

        while (true) {
            feedback = status.idle(null);
            //System.out.println(feedback);

            Reply reply = new Reply(CHANGED_STATUS + feedback);
            Iterator<String> i = reply.iterator();
            while (i.hasNext()) {
                String line = i.next();
                String[] lines = line.split(" ");
                for (ChangeListener l : changeListeners) {
                    l.stateChanged(new ChangeEvent(lines[1]));
                }
            }
        }
    }
}