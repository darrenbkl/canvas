package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.util.Validator;

public class CreateCanvasCommand extends AbstractCommand {

    private int width;
    private int height;

    @Override
    public Canvas execute() {
        return new Canvas(width, height);
    }

    @Override
    public void setParameters(String... params) {
        Validator.validateCommandParameters(params, 2);

        width = Validator.parseInt(params[0]);
        height = Validator.parseInt(params[1]);
    }
}