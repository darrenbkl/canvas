package canvasapp.drawable.shape;

import canvasapp.drawable.Point;
import canvasapp.exception.InvalidCoordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rect implements Shape {

    private final Point start;
    private final Point end;
    List<Point> points = null;

    public Rect(int x1, int y1, int x2, int y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public Rect(Point start, Point end) {
        if (start.isEqualTo(end)) {
            throw new InvalidCoordinates("Coordinates must form a rectangle");
        }

        if (start.onSameXAxisWith(end) || start.onSameYAxisWith(end)) {
            throw new InvalidCoordinates("Coordinates must form a rectangle");
        }

        if (start.isLargerThan(end)) {
            Point temp = start;
            start = end;
            end = temp;
        }

        this.start = start;
        this.end = end;
    }

    @Override
    public Iterable<Point> getEndPoints() {
        return Arrays.asList(start, end);
    }

    @Override
    public Iterable<Point> getPoints() {
        if (points != null) {
            return points;
        }

        points = new ArrayList<>();

        for (int x = start.getX(); x <= end.getX(); x++) {
            points.add(new Point(x, start.getY()));
            points.add(new Point(x, end.getY()));
        }

        for (int y = start.getY() + 1; y < end.getY(); y++) {
            points.add(new Point(start.getX(), y));
            points.add(new Point(end.getX(), y));
        }

        return points;
    }
}