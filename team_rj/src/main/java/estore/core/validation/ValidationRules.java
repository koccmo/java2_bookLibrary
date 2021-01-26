package estore.core.validation;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationRules {

    public Boolean validateString(String userInput) {
        Pattern pattern = Pattern.compile("[A-Za-z]*");
        Matcher m = pattern.matcher(userInput);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    public Boolean validateLineWithWhitespaces(String userInput) {
        userInput = userInput.replaceAll("\\s+","");

        Pattern pattern = Pattern.compile("[A-Za-z_]*");
        Matcher m = pattern.matcher(userInput);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    public Boolean validateLineWithDigits(String userInput) {
        userInput = userInput.replaceAll("\\s+","");

        Pattern pattern = Pattern.compile("[A-Za-z_0-9]*");
        Matcher m = pattern.matcher(userInput);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    public boolean validatePositiveInteger(String userStringInput) {
        int choice;
        try {
            choice = Integer.valueOf(userStringInput);
        } catch (Exception e) {
            return false;
        }
        if (choice > 0) {
            return true;
        }
        return false;
    }

    public boolean validatePositiveLong(String userStringInput) {
        long choice;
        try {
            choice = Long.valueOf(userStringInput);
        } catch (Exception e) {
            return false;
        }
        if (choice > 0) {
            return true;
        }
        return false;
    }

    public boolean validatePositiveDouble(String userStringInput) {
        double choice;
        try {
            choice = Double.valueOf(userStringInput);
        } catch (Exception e) {
            return false;
        }
        if (choice >= 0) {
            return true;
        }
        return false;
    }
}
