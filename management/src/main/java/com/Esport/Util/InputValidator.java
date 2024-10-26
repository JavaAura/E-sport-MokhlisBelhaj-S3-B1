package com.Esport.Util;

import java.util.Scanner;



public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    public static int validateMenuPrincipalInput() {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            LoggerUtil.info("Enter your choice (1-5): ");
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= 1 && input <= 5) {
                    validInput = true;
                } else {
                    LoggerUtil.info("Invalid input. Please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                LoggerUtil.error("Invalid input: Not a number", e);
                LoggerUtil.info("Invalid input. Please enter a valid number.");
            }
        }   

        return input;
    }
}
