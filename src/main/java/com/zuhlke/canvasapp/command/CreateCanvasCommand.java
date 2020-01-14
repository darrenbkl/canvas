package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.drawable.Canvas;

public class CreateCanvasCommand implements Command {

    private final int width;
    private final int height;

    public CreateCanvasCommand(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Canvas execute(Canvas canvas) {
        return new Canvas(width, height);
    }
}