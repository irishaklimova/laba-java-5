package com.java-5-Klimova.injectors;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private Properties properties;

    public void loadProperties(String path) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IOException("Error loading properties from " + path, e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}