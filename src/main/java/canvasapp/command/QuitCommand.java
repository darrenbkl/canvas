package canvasapp.command;

import canvasapp.drawable.Canvas;

public class QuitCommand implements Command {

    @Override
    public Canvas execute(Canvas canvas) {
        System.exit(0);
        return null;
    }
}