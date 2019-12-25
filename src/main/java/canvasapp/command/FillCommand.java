package canvasapp.command;

import canvasapp.Canvas;
import canvasapp.exception.IllegalCanvasStateException;

public class FillCommand implements Command {

    private final int x;
    private final int y;
    private final char color;

    public FillCommand(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public Canvas execute(Canvas canvas) {
        if (canvas == null) throw new IllegalCanvasStateException("Canvas must be created");

        return canvas.fill(x, y, color);
    }
}