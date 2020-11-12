package estore.ui;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {
    public int validateUserMenuChoice(String userStringInput) {
        int choice;
        try {
            choice = Integer.valueOf(userStringInput);
        } catch (Exception e) {
            return -1;
        }
        for (int i = 0; i < 7; i++) {
            if (choice == i) {
                return choice;
            }
        }
        return -1;
    }

    public int validatePositiveInteger(String userStringInput) {
        int choice;
        try {
            choice = Integer.valueOf(userStringInput);
        } catch (Exception e) {
            return -1;
        }
        if (choice > 0) {
            return choice;
        }
        return -1;
    }

    public Boolean validateString(String userInput) {
        if (userInput == null || userInput.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile("[A-Za-z]*");
        Matcher m = pattern.matcher(userInput);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    public Boolean validateLine(String userInput) {
        userInput = userInput.replaceAll("\\s+","");
        if (userInput == null || userInput.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile("[A-Za-z_0-9]*");
        Matcher m = pattern.matcher(userInput);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    public String getLine(String description) {
        Scanner sc = new Scanner(System.in);
        String inputLine = "";
        while (true) {
            System.out.println(description);
            inputLine = sc.nextLine();
            if (validateLine(inputLine)) {
                break;
            }
            System.out.println("Invalid input.");
        }
        return inputLine;
    }

    public String getString(String description) {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String inputString = "";
        while (true) {
            System.out.println(description);
            inputString = sc.nextLine();
            if (validateString(inputString)) {
                break;
            }
            System.out.println("Invalid input.");
        }
        return inputString;
    }

    public int getPositiveInteger(String description) {
        Scanner sc = new Scanner(System.in);
        int userInput;
        while (true) {
            System.out.println(description);
            String userStringInput = sc.nextLine();
            userInput = validatePositiveInteger(userStringInput);
            if (userInput != -1) {
                break;
            }
            System.out.println("Invalid input.");
        }
        return userInput;
    }

    public int getUserInputOfMenuItem() {
        Scanner sc = new Scanner(System.in);
        int userInput;
        while (true) {
            System.out.print("Choice: ");
            String userStringInput = sc.nextLine();
            userInput = validateUserMenuChoice(userStringInput);
            if (userInput != -1) {
                break;
            }
            System.out.println("Invalid input.");
        }
        return userInput;
    }
}
