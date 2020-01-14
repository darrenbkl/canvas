package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.exception.InvalidInputFormat;
import com.zuhlke.canvasapp.util.ReflectUtil;
import com.zuhlke.canvasapp.util.Validator;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {

    private Map<String, Class<? extends Command>> commandMap;

    {
        commandMap = new HashMap<>();
        commandMap.put("C", CreateCanvasCommand.class);
        commandMap.put("L", DrawLineCommand.class);
        commandMap.put("R", DrawRectCommand.class);
        commandMap.put("B", FillCommand.class);
        commandMap.put("Q", QuitCommand.class);
    }

    public Command createCommand(String[] input) {
        String commandKey;

        if (input != null && input.length > 0) {
            commandKey = input[0];
            commandKey = commandKey.toUpperCase();
        } else {
            throw new InvalidInputFormat("Missing command");
        }

        String[] parametersString = {};

        if (input.length > 1) {
            parametersString = Arrays.copyOfRange(input, 1, input.length);
        }

        Class<? extends Command> commandClazz = lookupCommandClassByKey(commandKey);
        return createClassInstanceFromParameters(commandClazz, parametersString);
    }

    private Class<? extends Command> lookupCommandClassByKey(String commandKey) {
        return Optional.ofNullable(commandMap.get(commandKey))
                       .orElseThrow(() -> new InvalidInputFormat("Invalid command"));
    }

    private static <T> T createClassInstanceFromParameters(Class<T> clazz, String[] parametersString) {
        Constructor<?> constructor = ReflectUtil.getConstructorOfClass(clazz);
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] parameters = ReflectUtil.resolveParameters(parameterTypes, parametersString,
                                                            Validator::parseStringToType);

        return ReflectUtil.createNewInstance((Constructor<T>) constructor, parameters);
    }
}