package com.bono.api;

/**
 * Created by hendriknieuwenhuis on 14/08/15.
 */
public interface Command {

    byte[] getCommandBytes();

    // verwijderen is niet vereist!
    String getCommandString();

}
