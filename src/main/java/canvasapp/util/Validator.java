package canvasapp.util;

import canvasapp.drawable.Canvas;
import canvasapp.exception.ApplicationError;
import canvasapp.exception.IllegalCanvasStateException;
import canvasapp.exception.InvalidInputFormat;

public final class Validator {

    private Validator() {
    }

    public static Object parseStringToType(String arg, Class<?> clazz) {
        if (clazz.equals(int.class)) return parseInt(arg);
        else if (clazz.equals(char.class)) return parseChar(arg);
        else throw new ApplicationError("Unsupported parameter type");
    }

    private static int parseInt(String arg) {
        if (arg == null) throw new InvalidInputFormat("Invalid integer");

        int parsedResult;

        try {
            parsedResult = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new InvalidInputFormat("Invalid integer");
        }

        return parsedResult;
    }

    private static char parseChar(String arg) {
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
}