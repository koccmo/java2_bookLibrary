package internet_store.user_interface.main_menu;

import org.springframework.stereotype.Component;
import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;
@Component
public class MainMenu {
    @Getter
    private int userInput;

    public MainMenu() {}

    public void showMainMenu() {
        final int INCORRECT_USER_INPUT = -1;
        System.out.println("--------------------------");
        System.out.println("Administrator menu");
        System.out.println("--------------------------");
        System.out.println("1 - Client menu");
        System.out.println("2 - Product menu");
        System.out.println("3 - Order menu");
        System.out.println("4 - View client's list");
        System.out.println("--------------------------");
        System.out.println("Client menu");
        System.out.println("--------------------------");
        System.out.println("5 - Cart menu");
        System.out.println("6 - View cart's list");
        System.out.println("7 - View product's list");
        System.out.println("8 - Make order");
        System.out.println("--------------------------");
        System.out.println("9 - Synchronize database in memory and MySql database");
        System.out.println("--------------------------");
        System.out.println("0 - Exit");
        try {
            userInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }
}