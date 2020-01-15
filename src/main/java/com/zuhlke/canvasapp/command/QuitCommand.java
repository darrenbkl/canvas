package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.util.Validator;

public class QuitCommand extends AbstractCommand {

    @Override
    public Canvas execute() {
        context.exit();
        return null;
    }

    @Override
    public void setParameters(String... params) {
        Validator.validateCommandParameters(params, 0);
    }
}