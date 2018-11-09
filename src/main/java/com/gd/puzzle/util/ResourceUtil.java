package com.gd.puzzle.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.gd.puzzle.exception.ResourceException;

public class ResourceUtil {
    public static final String DEFAULT_BUNDLE = "application";
    private static ResourceBundle resourceBundle;

    public static ResourceBundle getResourceBundle(String bundleName) {
        if (null == resourceBundle) {
            synchronized (ResourceUtil.class) {
                resourceBundle = ResourceBundle.getBundle(bundleName, Locale.ENGLISH);
            }
        }
        return resourceBundle;
    }

    public static ResourceBundle getResourceFromPath(File f) throws IOException {
        try (FileInputStream fis = new FileInputStream(f)) {
            return new PropertyResourceBundle(fis);
        }
    }

    public static String getMessage(String key) {
        return getResourceBundle(DEFAULT_BUNDLE).getString(key);
    }

    public static String getMessage(String key, ResourceBundle bundle) throws ResourceException {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            throw new ResourceException(e.getMessage(), e);
        }
    }


    public static File[] getResourceFiles(String folder) {
        ClassLoader loader = Thread.currentThread()
                                   .getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        return new File(path).listFiles();
    }

    public static String[] getResourceFolders(String folder) {
        ClassLoader loader = Thread.currentThread()
                                   .getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        return new File(path).list();
    }

    private ResourceUtil() {
    }
}
