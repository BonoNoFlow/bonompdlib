package com.bono.api;

import java.util.EventObject;

/**
 * Created by hendriknieuwenhuis on 23/05/16.
 */
public class ChangeEvent extends EventObject {

    public ChangeEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
