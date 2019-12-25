package canvasapp.handler;

import canvasapp.command.Command;
import canvasapp.command.CreateCanvasCommand;
import canvasapp.command.DrawCommand;
import canvasapp.command.FillCommand;
import canvasapp.context.Context;
import canvasapp.drawable.Drawable;
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

        Drawable drawable;

        if (action.equals("L")) drawable = new LineDrawable(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
        else if (action.equals("R")) drawable = new RectDrawable(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
        else throw new IllegalArgumentException("Invalid command");

        Command command = new DrawCommand(drawable, LINE_COLOR);
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

        char color = input[3].charAt(0);

        Command command = new FillCommand(x - 1, y - 1, color);
        return context.execute(command);
    }
}