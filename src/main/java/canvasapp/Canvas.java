package canvasapp;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@Value
public class Canvas {

    private Node[][] nodes;
    private int w;
    private int h;

    public Canvas(int w, int h) {

        if (w < 1 || h < 1) throw new InvalidCoordinates("Invalid canvas dimension");

        this.w = w;
        this.h = h;
        this.nodes = new Node[w][h];

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                nodes[x][y] = new Node(x, y, Node.Status.EMPTY, ' ');
            }
        }
    }

    private Canvas(Node[][] nodes, int w, int h) {
        this.nodes = nodes;
        this.w = w;
        this.h = h;
    }

    public boolean isWithinCanvas(int x, int y) {
        if (x < 0 || x >= w || y < 0 || y >= h) return false;
        else return true;
    }

    public boolean isBorder(int x, int y) {

        checkCanvasBoundary(x, y);
        return nodes[x][y].getStatus() == Node.Status.BORDER;
    }

    public boolean isPaint(int x, int y, char color) {
        checkCanvasBoundary(x, y);
        Node n = nodes[x][y];
        return n.getStatus().equals(Node.Status.PAINT) && n.getColor() == color;
    }

    public Canvas draw(List<Point> points, char color) {

        Node[][] newNodes = copy(nodes, w, h);

        for (Point p : points) {
            int x = p.getX();
            int y = p.getY();

            checkCanvasBoundary(x, y);

            newNodes[x][y] = new Node(x, y, Node.Status.BORDER, color);
        }

        return new Canvas(newNodes, w, h);
    }

    public Canvas draw(Point point, char color) {

        int x = point.getX();
        int y = point.getY();

        checkCanvasBoundary(x, y);

        Node[][] newNodes = copy(nodes, w, h);

        newNodes[x][y] = new Node(x, y, Node.Status.BORDER, color);

        return new Canvas(newNodes, w, h);
    }

    public Canvas paint(List<Point> points, char color) {

        Node[][] newNodes = copy(nodes, w, h);

        for (Point p : points) {
            int x = p.getX();
            int y = p.getY();

            checkCanvasBoundary(x, y);

            newNodes[x][y] = new Node(x, y, Node.Status.PAINT, color);
        }

        return new Canvas(newNodes, w, h);
    }

    public Canvas paint(Point point, char color) {

        int x = point.getX();
        int y = point.getY();

        checkCanvasBoundary(x, y);

        Node[][] newNodes = copy(nodes, w, h);

        newNodes[x][y] = new Node(x, y, Node.Status.PAINT, color);

        return new Canvas(newNodes, w, h);
    }

    private Node[][] copy(Node[][] oriNodes, int w, int h) {

        Node[][] newNodes = new Node[w][h];

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                newNodes[x][y] = oriNodes[x][y];
            }
        }

        return newNodes;
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

                Node node = nodes[x][y];
                sb.append(node.getColor());
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Builder
    @Value
    @With
    private static class Node {

        private int x;
        private int y;
        private Status status;
        private char color;

        enum Status {
            EMPTY,
            BORDER,
            PAINT
        }
    }
}