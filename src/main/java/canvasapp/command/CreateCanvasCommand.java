package canvasapp.command;

import canvasapp.Canvas;

public class CreateCanvasCommand implements Command {

    private final int w;
    private final int h;

    public CreateCanvasCommand(int w, int h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public Canvas execute(Canvas canvas) {
        return new Canvas(w, h);
    }
}