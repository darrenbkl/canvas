package canvasapp;

import canvasapp.exception.InvalidCoordinates;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Canvas {

    private final char[][] canvasData;
    private final int width;
    private final int height;

    public Canvas(int width, int height) {

        if (width < 1 || height < 1) {
            throw new InvalidCoordinates("Invalid canvas dimension");
        }

        this.width = width;
        this.height = height;
        this.canvasData = new char[width][height];

        for (char[] row : canvasData) {
            Arrays.fill(row, ' ');
        }
    }

    private Canvas(char[][] canvasData, int width, int height) {
        this.canvasData = canvasData;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Canvas drawLine(int x1, int y1, int x2, int y2, char color) {

        checkCanvasBoundary(canvasData, x1, y1);
        checkCanvasBoundary(canvasData, x2, y2);

        char[][] newCanvasData = copy(canvasData);

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                newCanvasData[x][y] = color;
            }
        }

        return new Canvas(newCanvasData, width, height);
    }

    public Canvas drawRect(int x1, int y1, int x2, int y2, char color) {

        checkCanvasBoundary(canvasData, x1, y1);
        checkCanvasBoundary(canvasData, x2, y2);

        char[][] newCanvasData = copy(canvasData);

        for (int x = x1; x <= x2; x++) {
            newCanvasData[x][y1] = color;
            newCanvasData[x][y2] = color;
        }

        for (int y = y1 + 1; y < y2; y++) {
            newCanvasData[x1][y] = color;
            newCanvasData[x2][y] = color;
        }

        return new Canvas(newCanvasData, width, height);
    }

    public Canvas fill(int x, int y, char color) {

        checkCanvasBoundary(canvasData, x, y);

        char target = canvasData[x][y];

        if (target == color) {
            return this;
        }

        char[][] newCanvasData = doFill(canvasData, x, y, target, color);

        return new Canvas(newCanvasData, width, height);
    }

    // TODO explain how this works
    private static char[][] doFill(char[][] canvasData, int x, int y, char target, char color) {

        char[][] newCanvasData = copy(canvasData);

        Queue<Point> queue = new LinkedList<>();

        // if is wall or same color
        if (newCanvasData[x][y] != target) {
            return canvasData;
        } else {
            newCanvasData[x][y] = color;
        }

        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {

            Point currPoint = queue.remove();
            int curX = currPoint.getX();
            int curY = currPoint.getY();

            newCanvasData = checkNeighbour(newCanvasData, queue, curX - 1, curY, target, color);
            newCanvasData = checkNeighbour(newCanvasData, queue, curX + 1, curY, target, color);
            newCanvasData = checkNeighbour(newCanvasData, queue, curX, curY - 1, target, color);
            newCanvasData = checkNeighbour(newCanvasData, queue, curX, curY + 1, target, color);
        }

        return newCanvasData;
    }

    // TODO explain this
    private static char[][] checkNeighbour(char[][] canvasData, Queue<Point> queue, int x, int y, char target,
                                           char color) {

        if (!isPointWithinCanvas(canvasData, x, y)) {
            return canvasData;
        }

        char[][] newCanvasData = copy(canvasData);

        if (newCanvasData[x][y] == target) {
            newCanvasData[x][y] = color;
            queue.add(new Point(x, y));
        }

        return newCanvasData;
    }

    private static void checkCanvasBoundary(char[][] canvasData, int x, int y) {
        if (!isPointWithinCanvas(canvasData, x, y)) {
            throw new InvalidCoordinates("Coordinates must be within canvas dimension");
        }
    }

    private static boolean isPointWithinCanvas(char[][] canvasData, int x, int y) {
        return x >= 0
                && x < canvasData.length
                && y >= 0
                && y < canvasData[x].length;
    }

    private static char[][] copy(char[][] original) {
        if (original == null) {
            return null;
        }

        final char[][] copyOfOriginal = new char[original.length][];

        for (int i = 0; i < original.length; i++) {
            copyOfOriginal[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copyOfOriginal;
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
                    sb.append(canvasData[x][y]);
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static class Point {
        private int x;
        private int y;

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
    }
}