package canvasapp.command;

import canvasapp.Canvas;
import canvasapp.exception.InvalidCoordinates;
import canvasapp.Point;

import java.util.ArrayList;
import java.util.List;

public class DrawRectangleCommand implements Command {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final char color;

    public DrawRectangleCommand(int x1, int y1, int x2, int y2, char color) {

        if (x1 == x2 || y1 == y2) {
            throw new InvalidCoordinates("Coordinates must form a rectangle");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    @Override
    public Canvas execute(Canvas canvas) {

        if (canvas == null) throw new IllegalStateException("Canvas must be created");

        List<Point> points = new ArrayList<>();

        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                if (x == x1 || x == x2 || y == y1 || y == y2) {
                    points.add(new Point(x, y));
                }
            }
        }

        return canvas.draw(points, color);
    }
}