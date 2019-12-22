package canvasapp.command;

import canvasapp.Canvas;
import canvasapp.drawable.Drawable;

public class DrawCommand implements Command {

    private Drawable drawable;

    public DrawCommand(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public Canvas execute(Canvas canvas) {

        if (canvas == null) throw new IllegalStateException("Canvas must be created");

        shape.draw(canvas);
        return drawable.draw(canvas);
    }
}