package com.core;

import org.openqa.selenium.By;

public enum LocatorsTypes {

    XPATH {
        public By getBy(String value) {
            return By.xpath(value);
        }
    },
    CSS {
        public By getBy(String value) {
            return By.cssSelector(value);
        }
    },
    ID {
        public By getBy(String value) {
            return By.id(value);
        }
    };

    public abstract By getBy(String value);

    public static LocatorsTypes get(String key) {
        try {
            return Enum.valueOf(LocatorsTypes.class, key);

        } catch (IllegalArgumentException e) {
            throw new RuntimeException(String.format("Invalid value for enum LocatorsTypes: %s", key));
        }
    }
}