package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.drawable.Color;
import com.zuhlke.canvasapp.drawable.Point;
import com.zuhlke.canvasapp.util.Validator;

public class FillCommand extends AbstractCommand {

    private Point point;
    private Color color;

    @Override
    public Canvas execute() {
        Canvas canvas = context.getCanvas();

        Validator.validateCanvas(canvas);
        return canvas.fill(point, color);
    }

    @Override
    public void setParameters(String... params) {
        Validator.validateCommandParameters(params, 3);

        int x = Validator.parseInt(params[0]);
        int y = Validator.parseInt(params[1]);
        char c = Validator.parseChar(params[2]);

        this.point = new Point(x - 1, y - 1);
        this.color = new Color(c);
    }
}