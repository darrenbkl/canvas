package com.zuhlke.canvasapp.drawable.shape;

import com.zuhlke.canvasapp.drawable.Point;

public interface Shape {

    Iterable<Point> getEndPoints();

    Iterable<Point> getPoints();
}