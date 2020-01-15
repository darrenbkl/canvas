package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.DrawingContext;

public abstract class AbstractCommand implements Command {

    protected DrawingContext context;

    @Override
    public void setContext(DrawingContext context) {
        this.context = context;
    }
}
