package canvasapp.drawable;

import canvasapp.Canvas;
import canvasapp.exception.InvalidCoordinates;

public class LineDrawable implements Drawable {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    public LineDrawable(int x1, int y1, int x2, int y2) {

        int deltaX = x2 - x1;
        int deltaY = y2 - y1;

        if((deltaX == 0 && deltaY == 0) || (deltaX != 0 && deltaY != 0)) {
            throw new InvalidCoordinates("Coordinates must form a straight line");
        }

        if(x2 < x1 || y2 < y1) {
            throw new InvalidCoordinates("Point 2 must be larger than point 1");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public Canvas draw(Canvas canvas, char color) {
        return canvas.drawLine(x1, y1, x2, y2, color);
    }
}