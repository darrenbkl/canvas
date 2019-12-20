package canvasapp.command;

import canvasapp.Canvas;
import canvasapp.Point;

public class PaintCommand implements Command {
    private final int x;
    private final int y;
    private final char color;

    public PaintCommand(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public Canvas execute(Canvas canvas) {
        if (canvas == null) throw new IllegalStateException("Canvas must be created");

        return doFillUsingStack(canvas, x, y, color);
    }

    private Canvas doFillUsingStack(Canvas canvas, int x, int y, char color) {

        // base case for recursion
        if(!canvas.isWithinCanvas(x, y)) return canvas;

        // if is wall or same color
        if (canvas.isPaint(x, y, color) || canvas.isBorder(x, y)) return canvas;
        else {
            canvas = canvas.paint(new Point(x, y), color);
        }

        canvas = doFillUsingStack(canvas, x, y + 1, color);
        canvas = doFillUsingStack(canvas, x, y - 1, color);
        canvas = doFillUsingStack(canvas, x - 1, y, color);
        canvas = doFillUsingStack(canvas, x + 1, y, color);

        return canvas;
    }
}