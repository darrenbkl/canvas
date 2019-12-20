package canvasapp.drawable;

import canvasapp.Canvas;
import canvasapp.Point;
import canvasapp.exception.InvalidCoordinates;

import java.util.ArrayList;
import java.util.List;

public class LineDrawable implements Drawable {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    public LineDrawable(int x1, int y1, int x2, int y2) {

        if ((x1 == x2) == (y1 == y2)) {
            throw new InvalidCoordinates("Coordinates must form a straight line");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public Canvas draw(Canvas canvas) {
        List<Point> points = new ArrayList<>();

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                if(!canvas.isWithinCanvas(x, y)) throw new InvalidCoordinates("Asdf");
                points.add(new Point(x, y));
            }
        }

        return canvas.draw(points, 'x');
    }
}