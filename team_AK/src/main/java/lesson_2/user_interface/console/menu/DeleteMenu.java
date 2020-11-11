package lesson_2.user_interface.console.menu;

import lesson_1.user_handler.UserInput;
import lesson_1.user_handler.UserInputValue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteMenu implements UserInput {
    private long userInput;

    public void showDeleteMenu() {
        final int INCORRECT_USER_INPUT = 0;
        System.out.println("Delete product");
        System.out.println("Enter ID number");
        try {
            userInput = new Scanner(System.in).nextLong();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }

    @Override
    public UserInputValue<?> getUserInputValue() {
        return new UserInputValue<>(userInput);
    }
}
