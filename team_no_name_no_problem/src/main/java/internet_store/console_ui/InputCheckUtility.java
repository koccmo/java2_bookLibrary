package internet_store.console_ui;

import java.util.Scanner;

public class InputCheckUtility {

    public int inputValidInteger(String message) {
        int input;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println(message);
            while (true) {
                try {
                    input = Integer.parseInt(in.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("It's not valid number! Please input valid number!");
                }
            }

            if (input >= 0) {
                break;
            } else {
                System.out.println("Please enter valid value!");
            }
        }
        return input;
    }
}
