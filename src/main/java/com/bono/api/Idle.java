package com.bono.api;

import com.bono.api.protocol.MPDStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 09/05/16.
 */
public class Idle {

    private ArrayList<ChangeListener> changeListeners = new ArrayList<>();

    private ClientExecutor clientExecutor;

    public Idle(ClientExecutor clientExecutor) {
        this.clientExecutor = clientExecutor;
    }

    public void addListener(ChangeListener listener) {
        changeListeners.add(listener);
    }

    public void removeListeners() {
        changeListeners.clear();
    }

    public void runIdle() throws Exception {
        List<String> feedback;

        while (true) {
            feedback = clientExecutor.execute(new DefaultCommand(MPDStatus.IDLE));

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
