package canvasapp.command;

import canvasapp.drawable.Canvas;
import canvasapp.drawable.Color;
import canvasapp.drawable.Point;
import canvasapp.util.Validator;

public class FillCommand implements Command {

    private final Point point;
    private final Color color;

    public FillCommand(int x, int y, char color) {
        this.point = new Point(x - 1, y - 1);
        this.color = new Color(color);
    }

    @Override
    public Canvas execute(Canvas canvas) {
        Validator.validateCanvas(canvas);
        return canvas.fill(point, color);
    }
}