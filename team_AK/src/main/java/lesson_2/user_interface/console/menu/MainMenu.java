package lesson_2.user_interface.console.menu;

import lesson_1.user_handler.UserInput;
import lesson_1.user_handler.UserInputValue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu implements UserInput {
    private int userInput;

    public void showMainMenu() {
        final int INCORRECT_USER_INPUT = 0;
        System.out.println("1 - Add product to list");
        System.out.println("2 - Delete product from list");
        System.out.println("3 - Update product from list");
        System.out.println("4 - Print products list");
        System.out.println("5 - Exit");
        try {
            userInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }

    @Override
    public UserInputValue<?> getUserInputValue() {
        return new UserInputValue<>(userInput);
    }
}