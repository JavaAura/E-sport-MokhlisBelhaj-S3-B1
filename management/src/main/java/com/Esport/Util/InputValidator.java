package com.Esport.Util;

import java.util.Scanner;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;



public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    public static int validateMenuChoice(int min, int max) {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            LoggerUtil.info("Enter your choice (" + min + "-" + max + "): ");
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) {
                    validInput = true;
                } else {
                    LoggerUtil.info("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                LoggerUtil.error("Invalid input: Not a number", e);
                LoggerUtil.info("Invalid input. Please enter a valid number.");
            }
        }   

        return input;
    }

    // Jeux input validation
    public static String validateStringInput(){
        LoggerUtil.info("Enter a string: ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            LoggerUtil.error("Invalid input: String is empty");
            return validateStringInput();
        }
        // check if the string contains only letters
        if (!input.matches("[a-zA-Z]+")) {
            LoggerUtil.error("Invalid input: String contains non-letter characters");
            return validateStringInput();
        }
        return input;
    }

    public static long validateLongInput(){
        LoggerUtil.info("Enter a long: ");
        long input = 0;
        try {
            input = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            LoggerUtil.error("Invalid input: Not a number", e);
            return validateLongInput();
        }
        return input;
    }

    public static Duration validateDurationInput() {
        while (true) {
            LoggerUtil.info("Enter a duration (HH:MM:SS): ");
            String inputString = scanner.nextLine().trim();
            if (inputString.isEmpty()) {
                LoggerUtil.error("Invalid input: Duration is empty");
                continue;
            }

            try {
                String formattedInput = "PT" + inputString.replaceFirst(":", "H").replace(":", "M") + "S";
                return Duration.parse(formattedInput);
            } catch (DateTimeParseException e) {
                LoggerUtil.error("Invalid input: Unable to parse duration. Please use the format HH:MM:SS.", e);
            }
        }

    }

    public static int validateAgeInput() {
        final int MIN_AGE = 16;
        final int MAX_AGE = 100;

        while (true) {
            LoggerUtil.info("Enter an age (" + MIN_AGE + "-" + MAX_AGE + "): ");
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= MIN_AGE && input <= MAX_AGE) {
                    return input;
                } else {
                    LoggerUtil.error("Invalid input: Age must be between " + MIN_AGE + " and " + MAX_AGE);
                }
            } catch (NumberFormatException e) {
                LoggerUtil.error("Invalid input: Not a number", e);
            }
        }
    }

    public static int validateIntInput() {
        while (true) {
            LoggerUtil.info("Enter an int: ");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                LoggerUtil.error("Invalid input: Not a valid integer", e);
                LoggerUtil.info("Please enter a valid integer.");
            }
        }
    }

    public static LocalDate validateDateInput() {
        while (true) {
            LoggerUtil.info("Enter a date (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();
            
            try {
                LocalDate date = LocalDate.parse(input);
                
                // Ensure date is not in the past
                if (date.isBefore(LocalDate.now())) {
                    LoggerUtil.error("Invalid input: Date cannot be in the past");
                    continue;
                }
                
                return date;
            } catch (DateTimeParseException e) {
                LoggerUtil.error("Invalid input: Please enter a valid date in YYYY-MM-DD format", e);
            }
        }
    }
}
