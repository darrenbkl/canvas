package canvasapp.command;

import canvasapp.Canvas;

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
        return canvas.fill(x, y, color);
    }
}