package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.drawable.Color;
import com.zuhlke.canvasapp.drawable.shape.Rect;
import com.zuhlke.canvasapp.util.Validator;

public class DrawRectCommand extends AbstractCommand {

    private Rect rect;
    private static final Color color = new Color('x');

    @Override
    public Canvas execute() {
        Canvas canvas = context.getCanvas();

        Validator.validateCanvas(canvas);
        return canvas.draw(rect, color);
    }

    @Override
    public void setParameters(String... params) {
        Validator.validateCommandParameters(params, 4);

        int x1 = Validator.parseInt(params[0]);
        int y1 = Validator.parseInt(params[1]);
        int x2 = Validator.parseInt(params[2]);
        int y2 = Validator.parseInt(params[3]);

        rect = new Rect(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
    }
}