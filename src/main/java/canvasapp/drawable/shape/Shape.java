package canvasapp.drawable.shape;

import canvasapp.drawable.Point;

public interface Shape {

    Iterable<Point> getEndPoints();

    Iterable<Point> getPoints();
}