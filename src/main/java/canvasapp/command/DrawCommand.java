package canvasapp.command;

import canvasapp.Canvas;
import canvasapp.drawable.Drawable;

public class DrawCommand implements Command {

    private Drawable drawable;
    private char color;

    public DrawCommand(Drawable drawable, char color) {
        this.drawable = drawable;
        this.color = color;
    }

    @Override
    public Canvas execute(Canvas canvas) {

        if (canvas == null) throw new IllegalStateException("Canvas must be created");

        return drawable.draw(canvas, color);
    }
}