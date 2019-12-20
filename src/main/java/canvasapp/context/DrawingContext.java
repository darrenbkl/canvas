package canvasapp.context;

import canvasapp.Canvas;
import canvasapp.command.Command;

/**
 * Manages commands and the state of canvas
 */
public class DrawingContext implements Context {

    private Canvas canvas;

    public DrawingContext() {
        this.canvas = null;
    }

    public DrawingContext(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public String execute(Command command) {
        canvas = command.execute(canvas);
        return canvas.toString();
    }

    @Override
    public boolean isInitialized() {
        return this.canvas != null;
    }
}