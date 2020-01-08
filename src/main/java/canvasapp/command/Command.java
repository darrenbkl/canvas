package canvasapp.command;

import canvasapp.drawable.Canvas;

public interface Command {

    Canvas execute(Canvas canvas);
}