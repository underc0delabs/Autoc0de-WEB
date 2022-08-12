package com.core;

import org.openqa.selenium.By;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocatorBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocatorBuilder.class);

    public static By getBy(String elementPath, String ... value) {
        try {
            String[] locator = elementPath.split(":");
            return getLocatorByType(locator, value);
        } catch (NullPointerException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(String.format("Locator %s was not found", elementPath));
        }
    }

    private static By getLocatorByType(String[] locator, String ... valueArgs) {
        try {
            String type;
            String value;
            if (locator[0].length()>5) {
                type = "XPATH";
                if (locator.length!=1) {
                    value=String.format(String.join(":",locator),valueArgs);
                } else {
                    value = String.format(locator[0], valueArgs);
                }
            } else {
                type = locator[0].toUpperCase();
                value = String.format(locator[1], valueArgs);
            }
            return LocatorsTypes.get(type).getBy(value);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException("Locator format is invalid. Example: xpath://*[@id='signup']");
        }
    }
}
