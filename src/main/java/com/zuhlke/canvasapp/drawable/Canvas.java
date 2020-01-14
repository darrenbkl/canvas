package com.zuhlke.canvasapp.drawable;

import com.zuhlke.canvasapp.drawable.shape.Shape;
import com.zuhlke.canvasapp.exception.InvalidCoordinates;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Canvas {

    private final Map<Point, Color> canvasData;
    private final int width;
    private final int height;

    public Canvas(int width, int height) {
        if (width < 1 || width > 1000 || height < 1 || height > 1000) {
            throw new InvalidCoordinates("Invalid canvas dimension");
        }

        this.width = width;
        this.height = height;
        this.canvasData = new HashMap<>(width * height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                canvasData.put(new Point(x, y), new Color());
            }
        }
    }

    private Canvas(Canvas canvas) {
        this.canvasData = deepCopy(canvas.canvasData);
        this.width = canvas.width;
        this.height = canvas.height;
    }

    public static <T, U> Map<T, U> deepCopy(Map<T, U> original) {
        return original.entrySet()
                       .stream()
                       .collect(Collectors.toMap(Map.Entry::getKey,
                                                 Map.Entry::getValue));
    }

    public Canvas draw(Shape shape, Color color) {
        Iterable<Point> endPoints = shape.getEndPoints();

        endPoints.forEach(point -> checkCanvasBoundary(this, point));

        Canvas newCanvas = new Canvas(this);

        Iterable<Point> points = shape.getPoints();
        points.forEach(p -> newCanvas.canvasData.put(p, color));

        return newCanvas;
    }

    public Canvas fill(Point point, Color color) {
        checkCanvasBoundary(this, point);

        Color target = canvasData.get(point);

        if (target.equals(color)) {
            return this;
        }

        Canvas newCanvas = new Canvas(this);

        return doFloodFill(newCanvas, point, target, color);
    }

    private static Canvas doFloodFill(Canvas canvas, Point point, Color target, Color color) {
        Queue<Point> queue = new LinkedList<>();

        Color curr = canvas.canvasData.get(point);

        if (curr.equals(target)) {
            canvas.canvasData.put(point, color);
        } else {
            return canvas;
        }

        queue.add(point);

        while (!queue.isEmpty()) {

            Point currentPoint = queue.remove();

            checkNeighbour(canvas, queue, currentPoint.left(), target, color);
            checkNeighbour(canvas, queue, currentPoint.right(), target, color);
            checkNeighbour(canvas, queue, currentPoint.down(), target, color);
            checkNeighbour(canvas, queue, currentPoint.up(), target, color);
        }

        return canvas;
    }

    private static Canvas checkNeighbour(Canvas canvas, Queue<Point> queue, Point point, Color target,
                                         Color color) {
        if (!isPointWithinCanvas(canvas, point)) {
            return canvas;
        }

        Color curr = canvas.canvasData.get(point);

        if (curr.equals(target)) {
            canvas.canvasData.put(point, color);
            queue.add(point);
        }

        return canvas;
    }

    private static void checkCanvasBoundary(Canvas canvas, Point point) {
        if (!isPointWithinCanvas(canvas, point)) {
            throw new InvalidCoordinates("Coordinates must be within canvas dimension");
        }
    }

    private static boolean isPointWithinCanvas(Canvas canvas, Point point) {
        Point smallest = new Point(0, 0);
        Point largest = new Point(canvas.width - 1, canvas.height - 1);

        return point.isBetweenInclusive(smallest, largest);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = -1; y <= height; y++) {
            for (int x = -1; x <= width; x++) {
                if (y == -1 || y == height) {
                    sb.append("-");
                } else if (x == -1 || x == width) {
                    sb.append("|");
                } else {
                    Color color = canvasData.get(new Point(x, y));
                    sb.append(color == null ? ' ' : color.toString());
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}