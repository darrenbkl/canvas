package canvasapp.handler;

import canvasapp.command.*;
import canvasapp.context.Context;
import canvasapp.drawable.FillDrawable;
import canvasapp.drawable.LineDrawable;
import canvasapp.drawable.RectDrawable;

public class Handler {

    private static final char LINE_COLOR = 'x';

    private Context context;

    public Handler(Context context) {
        this.context = context;
    }

    public String create(String[] input) {

        if (input.length != 3) throw new IllegalArgumentException("Input length should be 3");

        int w;
        int h;

        try {
            w = Integer.parseInt(input[1]);
            h = Integer.parseInt(input[2]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid integer");
        }

        Command command = new CreateCanvasCommand(w, h);
        return context.execute(command);
    }

    public String draw(String[] input) {

        if (input.length != 5) throw new IllegalArgumentException("Input length should be 5");
        if (!context.isInitialized()) throw new IllegalStateException("Canvas must be created");

        String action = input[0];

        int x1;
        int x2;
        int y1;
        int y2;

        try {
            x1 = Integer.parseInt(input[1]);
            y1 = Integer.parseInt(input[2]);
            x2 = Integer.parseInt(input[3]);
            y2 = Integer.parseInt(input[4]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid integer");
        }

        Command command;
        if (action.equals("L")) command = new DrawCommand(new LineDrawable(x1 - 1, y1 - 1, x2 - 1, y2 - 1));
        else if (action.equals("R")) command = new DrawCommand(new RectDrawable(x1 - 1, y1 - 1, x2 - 1, y2 - 1, LINE_COLOR));
//        if (action.equals("L")) command = new DrawLineCommand(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
//        else if (action.equals("R")) command = new DrawRectangleCommand(x1 - 1, y1 - 1, x2 - 1, y2 - 1, LINE_COLOR);
        else throw new IllegalArgumentException("Invalid command");

        return context.execute(command);
    }

    public String paint(String[] input) {

        if (input.length != 4) throw new IllegalArgumentException("Input length should be 4");
        if (!context.isInitialized()) throw new IllegalStateException("Canvas must be created");

        int x;
        int y;

        try {
            x = Integer.parseInt(input[1]);
            y = Integer.parseInt(input[2]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid integer");
        }

        if (input[3].length() > 1) {
            throw new IllegalArgumentException("Color must be a single character");
        }

        char c = input[3].charAt(0);

        Command command = new DrawCommand(new FillDrawable(x - 1, y - 1, c));
        return context.execute(command);
    }
}