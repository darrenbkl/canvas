package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.drawable.Canvas;

public class QuitCommand implements Command {

    @Override
    public Canvas execute(Canvas canvas) {
        System.exit(0);
        return null;
    }
}