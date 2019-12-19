package canvasapp;

public class InvalidCoordinates extends RuntimeException {
    public InvalidCoordinates() {
        super();
    }

    public InvalidCoordinates(String message) {
        super(message);
    }
}