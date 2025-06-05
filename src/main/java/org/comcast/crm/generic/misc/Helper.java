package org.comcast.crm.generic.misc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Random;


/**
 * A utility class that provides commonly used helper methods for random number generation,
 * date manipulation, formatting, and timestamp generation.
 * <p>
 * This class is not meant to be instantiated — all methods are static.
 * </p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>{@code
 * int random = Helper.getRandomNumber(4);
 * LocalDate today = Helper.getCurrentDate();
 * String formattedDate = Helper.formatDate(today, "yyyy-MM-dd");
 * }</pre>
 *
 * @author [Siddharth Malviya]
 * @version 1.0
 */
public class Helper {


    /**
     * Private constructor to prevent instantiation.
     */
    private Helper() {

    }

    /**
     * Generates a random number with the specified number of digits.
     *
     * @param digits the number of digits (must be between 1 and 9)
     * @return a random number of the specified digit length
     * @throws IllegalArgumentException if the digit count is not between 1 and 9
     */
    public static int getRandomNumber(int digits) {
        if (digits <= 0 || digits > 9) {
            throw new IllegalArgumentException("Digits must be between 1 and 9.");
        }

        int min = (int) Math.pow(10, digits - 1);
        int max = (int) Math.pow(10, digits) - 1;

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Generates a random number with a default of 5 digits.
     *
     * @return a random 5-digit number
     */
    public static int getRandomNumber() {
        return getRandomNumber(5);
    }

    /**
     * Returns the current system date.
     *
     * @return the current {@link LocalDate}
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * Returns the current date formatted according to the provided pattern.
     *
     * @param format the date format pattern (e.g., "yyyy-MM-dd")
     * @return the formatted date as a String, or an error message if the format is invalid
     */
    public static String getCurrentDate(String format) {
        return formatDate(LocalDate.now(), format);
    }

    /**
     * Adds a specified number of days to a given date.
     *
     * @param date the original date
     * @param days the number of days to add (can be negative)
     * @return the resulting {@link LocalDate}
     */
    public static LocalDate addDaysToDate(LocalDate date, int days) {
        LocalDate resultDate = date.plusDays(days);
        System.out.println("added date = " + resultDate);
        return resultDate;
    }


    /**
     * Formats the given date into a string based on the provided pattern.
     *
     * @param date the date to format
     * @param pattern the formatting pattern (e.g., "dd-MM-yyyy")
     * @return the formatted date string, or an error message if the pattern is invalid
     */
    public static String formatDate(LocalDate date, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return date.format(formatter);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            return "Invalid date format pattern: " + pattern;
        }
    }

    /**
     * Returns the current timestamp as a string, formatted for use in file names.
     * Spaces and colons are replaced with underscores.
     *
     * @return the current timestamp string
     */
    public static String getCurrentTimeStamp()
    {
        return new Date().toString().replace(" ", "_")
                .replace(":", "_");
    }
}

