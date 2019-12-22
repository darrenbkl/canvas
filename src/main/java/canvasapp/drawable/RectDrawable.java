package canvasapp.drawable;

import canvasapp.Canvas;
import canvasapp.Point;
import canvasapp.exception.InvalidCoordinates;

import java.util.ArrayList;
import java.util.List;

public class RectDrawable implements Drawable {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final char color;

    public RectDrawable(int x1, int y1, int x2, int y2, char color) {

        if (x1 == x2 || y1 == y2) {
            throw new InvalidCoordinates("Coordinates must form a rectangle");
        }

        if (x2 < x1 || y2 < y1) {
            throw new InvalidCoordinates("Point 2 must be larger than point 1");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    @Override
    public Canvas draw(Canvas canvas) {

        List<Point> points = new ArrayList<>();

        for (int x = x1; x <= x2; x++) {
            points.add(new Point(x, y1));
            points.add(new Point(x, y2));
        }

        for (int y = y1 + 1; y < y2; y++) {
            points.add(new Point(x1, y));
            points.add(new Point(x2, y));
        }

        return canvas.draw(points, color);
    }
}