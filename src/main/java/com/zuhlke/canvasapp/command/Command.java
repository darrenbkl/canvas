package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.DrawingContext;
import com.zuhlke.canvasapp.drawable.Canvas;

public interface Command {
    Canvas execute();

    void setContext(DrawingContext context);

    void setParameters(String... params);
}