package com.endava.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertiesManager {
    static Properties properties = new Properties();
    private static String PROPERTIES_FILE = "config.properties";

    public static String getProperties(String key) {
        String value = null;
        ClassLoader classLoader = ResourcesManager.getClassLoader();
        URL resource = classLoader.getResource(PROPERTIES_FILE);
        try {
            InputStream input = new FileInputStream(resource.getFile());
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
