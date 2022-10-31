package com.endava.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    static Properties properties = new Properties();
    private static final String PROPERTIES_FILE_PATH = "src/main/resources/config.properties";
    public static String getProperties(String key) {
        String value = null;
        try (InputStream input = new FileInputStream(PROPERTIES_FILE_PATH)){
            properties.load(input);
            value = properties.getProperty(key);

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return value;
    }
}
