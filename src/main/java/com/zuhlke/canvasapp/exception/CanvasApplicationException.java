package com.zuhlke.canvasapp.exception;

public abstract class CanvasApplicationException extends RuntimeException {
    public CanvasApplicationException(String message) {
        super(message);
    }
}