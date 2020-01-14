package com.zuhlke.canvasapp.drawable;

import java.util.Objects;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point right() {
        return new Point(x + 1, y);
    }

    public Point left() {
        return new Point(x - 1, y);
    }

    public Point up() {
        return new Point(x, y - 1);
    }

    public Point down() {
        return new Point(x, y + 1);
    }

    public boolean isLargerThan(Point anotherPoint) {
        boolean isNotEqual = !this.equals(anotherPoint);
        boolean xIsLarger = x >= anotherPoint.x;
        boolean yIsLarger = y >= anotherPoint.y;


        return isNotEqual && xIsLarger && yIsLarger;
    }

    public boolean isSmallerThan(Point anotherPoint) {
        boolean isNotEqual = !this.equals(anotherPoint);
        boolean xIsLarger = x <= anotherPoint.x;
        boolean yIsLarger = y <= anotherPoint.y;

        return isNotEqual && xIsLarger && yIsLarger;
    }

    public boolean isBetweenInclusive(Point pointA, Point pointB) {
        return this.equals(pointA) || this.equals(pointB) ||
                (this.isLargerThan(pointA) && this.isSmallerThan(pointB));
    }

    public boolean isEqualTo(Point anotherPoint) {
        return this.equals(anotherPoint);
    }

    public boolean onSameXAxisWith(Point anotherPoint) {
        return x == anotherPoint.x;
    }

    public boolean onSameYAxisWith(Point anotherPoint) {
        return y == anotherPoint.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}