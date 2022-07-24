package com.cognizant.product.query.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyLoader {

    private static String RESOURCE_FILENAME = "application.properties";
    public static String  USER_NAME_KEY = "mail.username";
    public static String  USER_PASSWORD_KEY = "mail.password";
    private static PropertyLoader instance;

    private Properties properties;


    private PropertyLoader() {
            try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(RESOURCE_FILENAME)) {
                properties = new Properties();
                properties.load(inputStream);
            } catch (IOException e) {

            }
    }

    public static synchronized PropertyLoader getInstance() {
        if (instance == null) {
            instance = new PropertyLoader();
        }
        return instance;
    }

    public static String getPropertyByKey(String key) {
        return getInstance().properties.getProperty(key);
    }

    public static Properties getProperty() {
        return getInstance().properties;
    }
}
