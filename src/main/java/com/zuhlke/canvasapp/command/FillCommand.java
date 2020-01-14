package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.drawable.Color;
import com.zuhlke.canvasapp.drawable.Point;
import com.zuhlke.canvasapp.util.Validator;

public class FillCommand implements Command {

    private final Point point;
    private final Color color;

    public FillCommand(int x, int y, char color) {
        this.point = new Point(x - 1, y - 1);
        this.color = new Color(color);
    }

    @Override
    public Canvas execute(Canvas canvas) {
        Validator.validateCanvas(canvas);
        return canvas.fill(point, color);
    }
}