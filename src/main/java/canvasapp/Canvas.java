package canvasapp;

import canvasapp.exception.InvalidCoordinates;
import lombok.Value;

import java.util.Arrays;
import java.util.List;

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

        for (char[] row: nodes) {
            Arrays.fill(row, ' ');
        }
    }

    private Canvas(char[][] nodes, int w, int h) {
        this.nodes = nodes;
        this.w = w;
        this.h = h;
    }

    public boolean isWithinCanvas(int x, int y) {
        if (x < 0 || x >= w || y < 0 || y >= h) return false;
        else return true;
    }

    public char getColor(int x, int y) {
        checkCanvasBoundary(x, y);
        return nodes[x][y];
    }

    public boolean isColor(int x, int y, char color) {
        checkCanvasBoundary(x, y);
        return nodes[x][y] == color;
    }


//    public Canvas drawRect()

    public Canvas draw(List<Point> points, char color) {

        char[][] newNodes = copy(nodes);

        for (Point p : points) {
            int x = p.getX();
            int y = p.getY();

            checkCanvasBoundary(x, y);

            newNodes[x][y] = color;
        }

        return new Canvas(newNodes, w, h);
    }

    public Canvas draw(Point point, char color) {

        int x = point.getX();
        int y = point.getY();

        checkCanvasBoundary(x, y);

        char[][] newNodes = copy(nodes);

        newNodes[x][y] = color;

        return new Canvas(newNodes, w, h);
    }

    private void checkCanvasBoundary(int x, int y) {
        if (x < 0 || x >= w || y < 0 || y >= h)
            throw new InvalidCoordinates("Coordinates must be within canvas dimension");
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

    public char[][] copy(char[][] original) {
        if (original == null) {
            return null;
        }

        final char[][] result = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}