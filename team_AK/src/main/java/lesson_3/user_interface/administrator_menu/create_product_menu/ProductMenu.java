package lesson_3.user_interface.administrator_menu.create_product_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductMenu {
    @Getter
    private int userInput;

    public void showMainMenu() {
        final int INCORRECT_USER_INPUT = 0;
        System.out.println("1 - Add product to list");
        System.out.println("2 - Delete product from list");
        System.out.println("3 - Update product from list");
        System.out.println("4 - Print products list");
        System.out.println("5 - Return");
        try {
            userInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }
}