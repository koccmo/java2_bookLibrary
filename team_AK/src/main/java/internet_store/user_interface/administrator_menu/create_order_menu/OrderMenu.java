package internet_store.user_interface.administrator_menu.create_order_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderMenu {
    @Getter
    private int userInput;

    public void showOrderMenu() {
        final int INCORRECT_USER_INPUT = -1;
        System.out.println("1 - Delete order");
        System.out.println("2 - Update order status");
        System.out.println("3 - View order list");
        System.out.println("4 - Return");
        try {
            userInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }
}