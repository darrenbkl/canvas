package canvasapp.handler;

import canvasapp.command.Command;
import canvasapp.command.CreateCanvasCommand;
import canvasapp.command.DrawCommand;
import canvasapp.command.FillCommand;
import canvasapp.context.Context;
import canvasapp.drawable.Drawable;
import canvasapp.drawable.LineDrawable;
import canvasapp.drawable.RectDrawable;
import canvasapp.exception.IllegalCanvasStateException;
import canvasapp.exception.InvalidInputFormat;

public class Handler {

    private static final char LINE_COLOR = 'x';

    private Context context;

    public Handler(Context context) {
        this.context = context;
    }

    public String create(String[] input) {

        if (input.length != 3) throw new InvalidInputFormat("Input length should be 3");

        int w;
        int h;

        try {
            w = Integer.parseInt(input[1]);
            h = Integer.parseInt(input[2]);
        } catch (NumberFormatException ex) {
            throw new InvalidInputFormat("Invalid integer");
        }

        Command command = new CreateCanvasCommand(w, h);
        return context.execute(command);
    }

    public String draw(String[] input) {

        if (input.length != 5) throw new InvalidInputFormat("Input length should be 5");
        if (!context.isInitialized()) throw new IllegalCanvasStateException("Canvas must be created");

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
            throw new InvalidInputFormat("Invalid integer");
        }

        Drawable drawable;

        if (action.equals("L")) drawable = new LineDrawable(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
        else if (action.equals("R")) drawable = new RectDrawable(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
        else throw new InvalidInputFormat("Invalid command");

        Command command = new DrawCommand(drawable, LINE_COLOR);
        return context.execute(command);
    }

    public String fill(String[] input) {

        if (input.length != 4) throw new InvalidInputFormat("Input length should be 4");
        if (!context.isInitialized()) throw new IllegalCanvasStateException("Canvas must be created");

        int x;
        int y;

        try {
            x = Integer.parseInt(input[1]);
            y = Integer.parseInt(input[2]);
        } catch (NumberFormatException ex) {
            throw new InvalidInputFormat("Invalid integer");
        }

        if (input[3].length() > 1) {
            throw new InvalidInputFormat("Color must be a single character");
        }

        char color = input[3].charAt(0);

        Command command = new FillCommand(x - 1, y - 1, color);
        return context.execute(command);
    }
}