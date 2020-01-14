package com.zuhlke.canvasapp.drawable.shape;

import com.zuhlke.canvasapp.drawable.Point;
import com.zuhlke.canvasapp.exception.InvalidCoordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line implements Shape {

    private final Point start;
    private final Point end;
    private List<Point> points = null;

    public Line(int x1, int y1, int x2, int y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public Line(Point start, Point end) {
        if (start.isEqualTo(end)) {
            throw new InvalidCoordinates("Coordinates must form a straight line");
        }

        if (!start.onSameXAxisWith(end) && !start.onSameYAxisWith(end)) {
            throw new InvalidCoordinates("Coordinates must form a straight line");
        }

        if (start.isLargerThan(end)) {
            Point temp = start;
            start = end;
            end = temp;
        }

        this.start = start;
        this.end = end;
    }

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
            for (int y = start.getY(); y <= end.getY(); y++) {
                points.add(new Point(x, y));
            }
        }
        return points;
    }
}