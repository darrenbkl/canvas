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

    public Canvas draw(Shape shape, Color color) {
        Iterable<Point> endPoints = shape.getEndPoints();

        endPoints.forEach(this::checkCanvasBoundary);

        Canvas newCanvas = new Canvas(this);

        Iterable<Point> points = shape.getPoints();
        points.forEach(p -> newCanvas.canvasData.put(p, color));

        return newCanvas;
    }

    public Canvas fill(Point point, Color color) {
        checkCanvasBoundary(point);

        Color target = canvasData.get(point);

        if (target.equals(color)) {
            return this;
        }

        Canvas newCanvas = new Canvas(this);
        newCanvas.doFloodFill(point, target, color);
        return newCanvas;
    }

    private void doFloodFill(Point point, Color target, Color color) {
        Queue<Point> queue = new LinkedList<>();

        Color curr = canvasData.get(point);

        if (curr.equals(target)) {
            canvasData.put(point, color);
        } else {
            return;
        }

        queue.add(point);

        while (!queue.isEmpty()) {
            Point currentPoint = queue.remove();

            exploreNeighbour(queue, currentPoint.left(), target, color);
            exploreNeighbour(queue, currentPoint.right(), target, color);
            exploreNeighbour(queue, currentPoint.down(), target, color);
            exploreNeighbour(queue, currentPoint.up(), target, color);
        }
    }

    private void exploreNeighbour(Queue<Point> queue, Point point, Color target, Color color) {
        if (isOutOfCanvasBoundary(point)) {
            return;
        }

        Color currentColor = canvasData.get(point);

        if (currentColor.equals(target)) {
            canvasData.put(point, color);
            queue.add(point);
        }
    }

    private void checkCanvasBoundary(Point point) {
        if (isOutOfCanvasBoundary(point)) {
            throw new InvalidCoordinates("Coordinates must be within canvas dimension");
        }
    }

    private boolean isOutOfCanvasBoundary(Point point) {
        Point smallest = new Point(0, 0);
        Point largest = new Point(width - 1, height - 1);

        return !point.isBetweenInclusive(smallest, largest);
    }

    private Map<Point, Color> deepCopy(Map<Point, Color> original) {
        return original.entrySet()
                       .stream()
                       .collect(Collectors.toMap(Map.Entry::getKey,
                                                 Map.Entry::getValue));
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