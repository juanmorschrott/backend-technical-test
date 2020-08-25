package com.tui.proof.ws.exception;

public class AvailabilityExpiredException extends RuntimeException {

    public AvailabilityExpiredException() {
        super("The information of this flight has expired");
    }

}
