package com.core.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyFileReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFileReader.class);
    private static final String PROPERTY_FILE_NAME = "/config.properties";

    private static ThreadLocal<Properties> properties = new ThreadLocal<>();

    private PropertyFileReader() {
    }

    private static Properties getProperties() {
        if (properties.get() == null) {
            loadData();
        }
        return properties.get();
    }

    public static String getProperty(String propertyKey) {
        return getProperties().getProperty(propertyKey);
    }


    private static void loadData() {
        Properties prop = new Properties();
        try (InputStream resourceAsStream = PropertyFileReader.class.getResourceAsStream(PROPERTY_FILE_NAME)) {
            prop.load(resourceAsStream);
        } catch (FileNotFoundException e) {
            LOGGER.error("File '"+PROPERTY_FILE_NAME+"' not found - Exception: ".concat(e.getMessage()));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        properties.set(prop);
    }

}