package canvasapp.drawable;

import canvasapp.Canvas;
import canvasapp.Point;

import java.util.LinkedList;
import java.util.Queue;

public class FillDrawable implements Drawable {

    private final int x;
    private final int y;
    private final char replacement;

    public FillDrawable(int x, int y, char replacement) {
        this.x = x;
        this.y = y;
        this.replacement = replacement;
    }

    @Override
    public Canvas draw(Canvas canvas) {
        char target = canvas.getColor(x, y);
        if(target == replacement) return canvas;

//        return doFillUsingStack(canvas, x, y, target, replacement);
        return doFillUsingQueue(canvas, x, y, target, replacement);
    }

    private Canvas doFillUsingStack(Canvas canvas, int x, int y, char target, char replacement) {

        // base case for recursion
        if(!canvas.isWithinCanvas(x, y)) return canvas;

        // if is wall or same color
        if (!canvas.isColor(x, y, target)) return canvas;
        else {
            canvas = canvas.draw(new Point(x, y), replacement);
        }

        canvas = doFillUsingStack(canvas, x, y + 1, target, replacement);
        canvas = doFillUsingStack(canvas, x, y - 1, target, replacement);
        canvas = doFillUsingStack(canvas, x - 1, y, target, replacement);
        canvas = doFillUsingStack(canvas, x + 1, y, target, replacement);

        return canvas;
    }

    private Canvas doFillUsingQueue(Canvas canvas, int x, int y, char target, char replacement) {

        Queue<Point> q = new LinkedList<>();

        // if is wall or same color
        if (!canvas.isColor(x, y, target)) return canvas;
        else {
            canvas = canvas.draw(new Point(x, y), replacement);
        }

        q.add(new Point(x, y));

        while (!q.isEmpty()) {

            Point currNode = q.remove();
            int curX = currNode.getX();
            int curY = currNode.getY();

            canvas = checkNeighbour(canvas, q, curX - 1, curY, target, replacement);
            canvas = checkNeighbour(canvas, q, curX + 1, curY, target, replacement);
            canvas = checkNeighbour(canvas, q, curX, curY - 1, target, replacement);
            canvas = checkNeighbour(canvas, q, curX, curY + 1, target, replacement);
        }

        return canvas;
    }

    private Canvas checkNeighbour(Canvas canvas, Queue<Point> q, int x, int y, char target, char replacement) {

        if (x < 0 || x >= canvas.getW() || y < 0 || y >= canvas.getH()) return canvas;

        if (canvas.isColor(x, y, target)) {
            canvas = canvas.draw(new Point(x, y), replacement);
            q.add(new Point(x, y));
        }

        return canvas;
    }
}
