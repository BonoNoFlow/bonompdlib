package com.bono.api;

import java.io.IOException;

/**
 * Created by hendriknieuwenhuis on 22/04/16.
 *
 * When reading or writing to the server fails by the
 * server or a false command was send the server returns
 * 'ACK' and a hint.
 * This exception can be used to catch this.
 */
public class ACKException extends IOException {

    public ACKException() {
        super();
    }

    public ACKException(String message) {
        super(message);
    }
}
