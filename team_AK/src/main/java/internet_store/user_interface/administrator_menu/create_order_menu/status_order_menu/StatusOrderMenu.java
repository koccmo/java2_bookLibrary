package internet_store.user_interface.administrator_menu.create_order_menu.status_order_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StatusOrderMenu {
    @Getter
    private int userInput;

    public void showStatusOrderMenu() {
        final int INCORRECT_USER_INPUT = -1;
        System.out.println("1 - ORDER_RECEIVED");
        System.out.println("2 - ITEM_ORDERED_TO_STOCK");
        System.out.println("3 - ORDER_WAITING_OFFICE");
        System.out.println("4 - CLIENT_INFO_BY_PHONE");
        System.out.println("5 - DELIVERED");
        System.out.println("6 - Return");
        try {
            userInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }
}