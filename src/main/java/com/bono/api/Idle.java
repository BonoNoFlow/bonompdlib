package com.bono.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 09/05/16.
 */
public class Idle {

    private ArrayList<ChangeListener> changeListeners = new ArrayList<>();

    private DBExecutor dbExecutor;

    private ClientExecutor clientExecutor;



    public Idle(ClientExecutor clientExecutor) {
        this.clientExecutor = clientExecutor;

    }

    public Idle(DBExecutor dbExecutor) {
        this.dbExecutor = dbExecutor;
    }

    public void addListener(ChangeListener listener) {
        changeListeners.add(listener);
    }

    public void removeListeners() {
        changeListeners.clear();
    }

    public void runIdle() throws Exception {
        //String feedback = null;
        List<String> feedback = new ArrayList<>();

        while (true) {
            //feedback = status.idle(null);

            feedback = clientExecutor.execute(new DefaultCommand(Status.IDLE));


            Iterator<String> i = feedback.iterator();
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
