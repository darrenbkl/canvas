package canvasapp.command;

import canvasapp.Canvas;

public class CreateCanvasCommand implements Command {

    private final int width;
    private final int height;

    public CreateCanvasCommand(int weight, int height) {
        this.width = weight;
        this.height = height;
    }

    @Override
    public Canvas execute(Canvas canvas) {
        return new Canvas(width, height);
    }
}