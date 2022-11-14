package com.endava.utils;

import java.io.File;

public class ResourcesManager {
    public static File getResourceAsFile(String resourceName) {
        String resourceFilePath = getClassLoader().getResource(resourceName).getFile();
        return new File(resourceFilePath);
    }

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
