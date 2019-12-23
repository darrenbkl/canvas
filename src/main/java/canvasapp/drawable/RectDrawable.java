package canvasapp.drawable;

import canvasapp.Canvas;
import canvasapp.exception.InvalidCoordinates;

public class RectDrawable implements Drawable {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    public RectDrawable(int x1, int y1, int x2, int y2) {

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
    }

    @Override
    public Canvas draw(Canvas canvas, char color) {
        return canvas.drawRect(x1, y1, x2, y2, color);
    }
}