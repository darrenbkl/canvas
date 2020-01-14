package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.drawable.Canvas;

public interface Command {

    Canvas execute(Canvas canvas);
}