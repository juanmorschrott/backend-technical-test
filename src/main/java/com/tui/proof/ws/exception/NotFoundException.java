package com.tui.proof.ws.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {}

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
