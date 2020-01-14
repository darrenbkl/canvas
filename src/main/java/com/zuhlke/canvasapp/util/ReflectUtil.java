package com.zuhlke.canvasapp.util;

import com.zuhlke.canvasapp.exception.ApplicationError;
import com.zuhlke.canvasapp.exception.CanvasApplicationException;
import com.zuhlke.canvasapp.exception.InvalidInputFormat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class ReflectUtil {

    public static Constructor<?> getConstructorOfClass(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        if (constructors.length == 0) {
            throw new ApplicationError("Class has no public constructor");
        }

        return constructors[0];
    }

    public static Object[] resolveParameters(Class<?>[] parameterTypes, String[] arguments,
                                             BiFunction<String, Class<?>, Object> parseFunc) {
        List<Object> resolvedParams = new ArrayList<>();

        if (arguments.length != parameterTypes.length) {
            throw new InvalidInputFormat("Invalid parameters");
        } else {
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> c = parameterTypes[i];
                Object res = parseFunc.apply(arguments[i], c);
                resolvedParams.add(res);
            }
        }

        return resolvedParams.toArray();
    }

    public static <T> T createNewInstance(Constructor<T> clazzConstructor, Object[] paramValues) {
        T newInstance = null;

        try {
            newInstance = clazzConstructor.newInstance(paramValues);
        } catch (InvocationTargetException ite) {
            Throwable cause = ite.getCause();
            if (cause instanceof CanvasApplicationException) {
                throw new ApplicationError(cause.getMessage());
            }
        } catch (Exception e) {
            throw new ApplicationError("Error creating class instance");
        }

        return newInstance;
    }
}