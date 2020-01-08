package canvasapp.command;

import canvasapp.drawable.Canvas;
import canvasapp.drawable.Color;
import canvasapp.drawable.shape.Line;
import canvasapp.util.Validator;

public class DrawLineCommand implements Command {

    private static final Color color = new Color('x');
    private final Line line;

    public DrawLineCommand(int x1, int y1, int x2, int y2) {
        this.line = new Line(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
    }

    @Override
    public Canvas execute(Canvas canvas) {
        Validator.validateCanvas(canvas);
        return canvas.draw(line, color);
    }
}