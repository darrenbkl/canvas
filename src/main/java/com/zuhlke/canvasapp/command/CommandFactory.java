package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.DrawingContext;
import com.zuhlke.canvasapp.exception.InvalidInputFormat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {

    private Map<String, AbstractCommand> commandMap;

    public CommandFactory() {
        commandMap = new HashMap<>();
        commandMap.put("C", new CreateCanvasCommand());
        commandMap.put("L", new DrawLineCommand());
        commandMap.put("R", new DrawRectCommand());
        commandMap.put("B", new FillCommand());
        commandMap.put("Q", new QuitCommand());
    }

    public Command createCommand(String[] input, DrawingContext context) {
        String commandKey;

        if (input != null && input.length > 0) {
            commandKey = input[0];
            commandKey = commandKey.toUpperCase();
        } else {
            throw new InvalidInputFormat("Missing command");
        }

        String[] parameters = {};

        if (input.length > 1) {
            parameters = Arrays.copyOfRange(input, 1, input.length);
        }

        AbstractCommand command = lookupCommandClassByKey(commandKey);
        command.setContext(context);
        command.setParameters(parameters);

        return command;
    }


    private AbstractCommand lookupCommandClassByKey(String commandKey) {
        return Optional.ofNullable(commandMap.get(commandKey))
                       .orElseThrow(() -> new InvalidInputFormat("Invalid command"));
    }
}