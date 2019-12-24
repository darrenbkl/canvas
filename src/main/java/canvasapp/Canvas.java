package canvasapp;

import canvasapp.exception.InvalidCoordinates;
import lombok.Value;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@Value
public class Canvas {

    private char[][] nodes;
    private int w;
    private int h;

    public Canvas(int w, int h) {

        if (w < 1 || h < 1) throw new InvalidCoordinates("Invalid canvas dimension");

        this.w = w;
        this.h = h;
        this.nodes = new char[w][h];

        for (char[] row : nodes) {
            Arrays.fill(row, ' ');
        }
    }

    private Canvas(char[][] nodes, int w, int h) {
        this.nodes = nodes;
        this.w = w;
        this.h = h;
    }


//    private void checkCanvasBoundary(int x, int y) {
//        if (!isWithinCanvas(x, y))
//            throw new InvalidCoordinates("Coordinates must be within canvas dimension");
//    }
//
//    private boolean isWithinCanvas(int x, int y) {
//        return x >= 0 && x < w && y >= 0 && y < h;
//    }
//
//    private boolean isColor(char[][] n, int x, int y, char color) {
//        checkCanvasBoundary(x, y);
//        return n[x][y] == color;
//    }

    private static void checkCanvasBoundary(char[][] n, int x, int y) {
        if (!isWithinCanvas(n, x, y))
            throw new InvalidCoordinates("Coordinates must be within canvas dimension");
    }

    private static boolean isWithinCanvas(char[][] n, int x, int y) {

//        int w = n.length;
//        if (x >= w) return false;
//
//        int h = n[x].length;
//        if (y >= h) return false;

        // shortcircuit evaluation
        return x >= n.length && y >= n[x].length;

//        return true;

//        return x >= 0 && x < n && y >= 0 && y < n.getH();
    }

    private static boolean isColor(char[][] n, int x, int y, char color) {
        checkCanvasBoundary(x, y);
        return n[x][y] == color;
    }

    public Canvas drawLine(int x1, int y1, int x2, int y2, char color) {

        checkCanvasBoundary(x1, y1);
        checkCanvasBoundary(x2, y1);

        char[][] newNodes = copy(nodes);

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                newNodes[x][y] = color;
            }
        }

        return new Canvas(newNodes, w, h);
    }

    public Canvas drawRect(int x1, int y1, int x2, int y2, char color) {

        char[][] newNodes = copy(nodes);

        for (int x = x1; x <= x2; x++) {
            newNodes[x][y1] = color;
            newNodes[x][y2] = color;
        }

        for (int y = y1 + 1; y < y2; y++) {
            newNodes[x1][y] = color;
            newNodes[x2][y] = color;
        }

        return new Canvas(newNodes, w, h);
    }

    public Canvas fill(int x, int y, char replacement) {

        checkCanvasBoundary(x, y);

        char target = nodes[x][y];

        if (target == replacement) return this;

//        char[][] newN = doFillUsingStack(nodes, x, y, target, replacement);
        char[][] newNodes = doFillUsingQueue(nodes, x, y, target, replacement);

        return new Canvas(newNodes, w, h);
    }

    private static char[][] doFillUsingStack(char[][] n, int x, int y, char target, char replacement) {

        char[][] newNodes = copy(n);

        // base case for recursion
        if (!isWithinCanvas(this, x, y)) return n;

        if (isColor(n, x, y, target)) return n;
        else {
            newNodes[x][y] = replacement;
        }

        newNodes = doFillUsingStack(newNodes, x, y + 1, target, replacement);
        newNodes = doFillUsingStack(newNodes, x, y - 1, target, replacement);
        newNodes = doFillUsingStack(newNodes, x - 1, y, target, replacement);
        newNodes = doFillUsingStack(newNodes, x + 1, y, target, replacement);

        return newNodes;
    }

    private static char[][] doFillUsingQueue(char[][] n, int x, int y, char target, char replacement) {

        char[][] newNodes = copy(n);

        Queue<Point> q = new LinkedList<>();

        // if is wall or same color
        if (newNodes[x][y] != target) return n;
        else {
            newNodes[x][y] = replacement;
        }

        q.add(new Point(x, y));

        while (!q.isEmpty()) {

            Point currNode = q.remove();
            int curX = currNode.getX();
            int curY = currNode.getY();

            newNodes = checkNeighbour(newNodes, q, curX - 1, curY, target, replacement);
            newNodes = checkNeighbour(newNodes, q, curX + 1, curY, target, replacement);
            newNodes = checkNeighbour(newNodes, q, curX, curY - 1, target, replacement);
            newNodes = checkNeighbour(newNodes, q, curX, curY + 1, target, replacement);
        }

        return newNodes;
    }

    private static char[][] checkNeighbour(char[][] n, Queue<Point> q, int x, int y, char target, char replacement) {

        if (!isWithinCanvas(canvas, x, y)) return n;

        char[][] newNodes = copy(n);

        if (newNodes[x][y] == target) {
            newNodes[x][y] = replacement;
            q.add(new Point(x, y));
        }

        return newNodes;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int y = -1; y <= h; y++) {
            for (int x = -1; x <= w; x++) {

                if (y == -1 || y == h) {
                    sb.append("-");
                    continue;
                }

                if (x == -1 || x == w) {
                    sb.append("|");
                    continue;
                }

                sb.append(nodes[x][y]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static char[][] copy(char[][] original) {
        if (original == null) {
            return null;
        }

        final char[][] result = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    @Value
    private static class Point {
        private int x;
        private int y;
    }
}