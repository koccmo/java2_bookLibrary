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
        for (int i = 0; i < 6; i++) {
            if (choice == i) {
                return choice;
            }
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
}
