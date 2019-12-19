package canvasapp.command;

import canvasapp.Canvas;

public interface Command {
    Canvas execute(Canvas canvas);
}