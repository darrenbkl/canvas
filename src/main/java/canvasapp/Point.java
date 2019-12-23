package canvasapp;

import lombok.Value;

// do i need this?
@Value
public class Point {
    private int x;
    private int y;

    public boolean isSmallerThan(Point p) {
        return this.x < p.x || this.y < p.y;
    }
}