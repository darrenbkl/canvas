package canvasapp;

import lombok.Value;

@Value
public class Point {
    private int x;
    private int y;

    public boolean isSmallerThan(Point p) {
        return this.x < p.x || this.y < p.y;
    }
}