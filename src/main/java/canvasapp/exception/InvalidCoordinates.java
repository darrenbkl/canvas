package canvasapp.exception;

public class InvalidCoordinates extends RuntimeException {
    public InvalidCoordinates(String message) {
        super(message);
    }
}