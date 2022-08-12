package com.core.utility;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;
import java.text.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Utils {
    public Utils() {
    }

    public static String getRandomNumber(int amount) {
        return RandomStringUtils.randomNumeric(amount);
    }

    public static String getRandomLetters(int amount) {
        return RandomStringUtils.randomAlphabetic(amount);
    }

    public static String getRandom(int amount, boolean letters, boolean numbers) {
        return RandomStringUtils.random(amount, letters, numbers);
    }

    public static String removeFirstAndLastChar(String text) {
        return text.substring(0, text.length() - 1).substring(1);
    }

    public static BigDecimal replaceDollarWithNumber(String value) {
        String newValue = value.replaceAll(" ", "").replace("US$", "");
        return new BigDecimal(newValue);
    }

    public static String dateToString(LocalDate date, String dateFormat) {
        return date.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    public static LocalDate stringToDate(String date, String dateFormat) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
    }

    public static String convertDateToFormatMMDDYYYY(Date date) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
    }

    public static void setValueToNonEditableInput(WebDriver driver, String inputId, String valueToSet) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("document.getElementById('" + inputId + "').value='" + valueToSet + "'", new Object[0]);
        }

    }

    public static int getOnlyNumbersFromString(String stringToReplace) {
        return Integer.parseInt(stringToReplace.replaceAll("\\D+", ""));
    }

    public static void deleteFileIfExists(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (NoSuchFileException var2) {
            System.out.println("No such file/directory exists");
        } catch (DirectoryNotEmptyException var3) {
            System.out.println("Directory is not empty.");
        } catch (IOException var4) {
            System.out.println("Invalid permissions.");
        }

        System.out.println("Deletion successful.");
    }

    public static boolean isTextFieldEmpty(WebElement element, String placeholder) {
        return element.getText().equals(placeholder);
    }

    public static <T> List<T> parseArrayToList(T[] array) {
        return Arrays.asList(array);
    }
}
