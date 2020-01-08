class Scratch {
    public static void main(String[] args) {
    }

        //    public Command createCommand(String[] input) {
//
//        String commandKey;
//
//        if (input != null && input.length > 0) {
//            commandKey = input[0];
//            commandKey = commandKey.toUpperCase();
//        } else {
//            throw new InvalidInputFormat("Missing command");
//        }
//
//        Class<? extends Command> commandClazz =
//                Optional.ofNullable(commandMap.get(commandKey))
//                        .orElseThrow(() -> new InvalidInputFormat("Invalid command"));
//
//        String[] arguments = {};
//
//        if (input.length > 1) {
//            arguments = Arrays.copyOfRange(input, 1, input.length);
//        }
//
//        ArgumentInfo argumentInfo = convertStringArrayToArgumentInfo(arguments);
//
//        Constructor clazzConstructor = getConstructorByParameterClass(commandClazz, argumentInfo.getClazzesArray());
//
//        return buildCommand(clazzConstructor, argumentInfo);
//    }


    //    private Constructor<? extends Command> getConstructorByParameterClass(Class commandClazz,
//                                                                          Class[] parameterClasses) {
//        Constructor<? extends Command> constructor;
//
//        try {
//            constructor = commandClazz.getConstructor(parameterClasses);
//        } catch (NoSuchMethodException e) {
//            throw new InvalidInputFormat("Invalid command arguments");
//        } catch (SecurityException e) {
//            throw new ApplicationError("Internal error");
//        }
//
//        return constructor;
//    }
//
//
//    private Command buildCommand(Constructor<? extends Command> clazzConstructor, ArgumentInfo argumentInfo) {
//        Command command = null;
//
//        try {
//            command = clazzConstructor.newInstance(argumentInfo.getValuesArray());
//        } catch (Exception e) {
//            // TODO log?
//            System.err.println(e.getMessage());
//            throw new ApplicationError("Internal error");
//        }
//
//        return command;
//    }


    private ArgumentInfo convertStringArrayToArgumentInfo(String[] args) {

        ArgumentInfo argumentInfo = new ArgumentInfo();

        // TODO refactor
        for (String arg : args) {
            try {
                Object res = Validator.parseInt(arg);
                argumentInfo.add(res, int.class);
                continue;
            } catch (InvalidInputFormat e) {
            }

            try {
                Object res = Validator.parseChar(arg);
                argumentInfo.add(res, char.class);
                continue;
            } catch (InvalidInputFormat ee) {
            }

            throw new InvalidInputFormat("cant convert bitch");
        }

        return argumentInfo;
    }

    private static class ArgumentInfo {
        private final List<Object> values;
        private final List<Class> clazzes;

        public ArgumentInfo() {
            values = new ArrayList<>();
            clazzes = new ArrayList<>();
        }

        public void add(Object value, Class clazz) {
            values.add(value);
            clazzes.add(clazz);
        }

        public Object[] getValuesArray() {
            return values.toArray(new Object[0]);
        }

        public Class[] getClazzesArray() {
            return clazzes.toArray(new Class[0]);
        }
    }
}