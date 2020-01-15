package com.zuhlke.canvasapp.util;

import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.exception.IllegalCanvasStateException;
import com.zuhlke.canvasapp.exception.InvalidInputFormat;

public final class Validator {

    private Validator() {
    }

    public static int parseInt(String arg) {
        if (arg == null) throw new InvalidInputFormat("Invalid integer");

        int parsedResult;

        try {
            parsedResult = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new InvalidInputFormat("Invalid integer");
        }

        return parsedResult;
    }

    public static char parseChar(String arg) {
        if (arg == null || arg.length() != 1) {
            throw new InvalidInputFormat("Invalid character");
        }

        return arg.charAt(0);
    }

    public static void validateCanvas(Canvas canvas) {
        if (canvas == null) {
            throw new IllegalCanvasStateException("Canvas must be created");
        }
    }

    public static void validateCommandParameters(String[] array, int expectedLength) {
        if (array == null || array.length != expectedLength) {
            throw new InvalidInputFormat("Invalid parameters");
        }
    }
}