package lesson_3.user_interface.main_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    @Getter
    private int userInput;

    public void showMainMenu() {
        final int INCORRECT_USER_INPUT = 0;
        System.out.println("--------------------------");
        System.out.println("Administrator menu");
        System.out.println("--------------------------");
        System.out.println("1 - Create Client menu");
        System.out.println("2 - Create Product menu");
        System.out.println("--------------------------");
        System.out.println("Client menu");
        System.out.println("--------------------------");
        System.out.println("3 - Add product to cart");
        System.out.println("4 - Delete product from cart");
        System.out.println("5 - View cart");
        System.out.println("6 - View products list");
        System.out.println("7 - Make order");
        System.out.println("--------------------------");
        System.out.println("8 - Exit");
        try {
            userInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }
}