package com.java-5-Klimova.injectors;
import com.java-5-Klimova.annotations.AutoInjectable;

import java.io.IOException;
import java.lang.reflect.Field;

public class Injector {

    public Injector() {}

    public <T> T inject(T object, String path) throws IOException {
        PropertyLoader propertyLoader = new PropertyLoader();
        propertyLoader.loadProperties(path);
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String fieldClassName = field.getType().getName();
                String injectedClassName = propertyLoader.getProperty(fieldClassName);

                if (injectedClassName != null) {
                    field.setAccessible(true);

                    try {
                        Class<?> injectedClass = Class.forName(injectedClassName);
                        Object classObject = injectedClass.getDeclaredConstructor().newInstance();
                        field.set(object, classObject);
                    } catch (ClassNotFoundException | InstantiationException |
                             IllegalAccessException | NoSuchMethodException |
                             java.lang.reflect.InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return object;
    }
}