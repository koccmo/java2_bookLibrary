package lesson_3.user_interface.main_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    @Getter
    private int userInput;

    public void showMainMenu() {
        final int INCORRECT_USER_INPUT = 0;
        System.out.println("1 - Client menu");
        System.out.println("2 - Product menu");
        System.out.println("3 - Exit");
        try {
            userInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }
}