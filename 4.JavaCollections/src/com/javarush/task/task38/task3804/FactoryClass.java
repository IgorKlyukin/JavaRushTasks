package com.javarush.task.task38.task3804;

public class FactoryClass {
    static Throwable exceptionFactoryClass(Enum factoryClass) {
        if (factoryClass != null) {
            String s = factoryClass.toString().replace('_', ' ');
            String message = s.substring(0, 1) + s.substring(1).toLowerCase();

            if (factoryClass instanceof ApplicationExceptionMessage)
                return new Exception(message);

            if (factoryClass instanceof DatabaseExceptionMessage)
                return new RuntimeException(message);

            if (factoryClass instanceof UserExceptionMessage)
                return new Error(message);
        }
        return new IllegalArgumentException();
    }
}
