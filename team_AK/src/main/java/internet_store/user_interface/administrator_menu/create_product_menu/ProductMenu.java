package internet_store.user_interface.administrator_menu.create_product_menu;

import dependency.annotation.DIComponent;
import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;
@DIComponent
public class ProductMenu {
    @Getter
    private int userInput;

    public void showMainMenu() {
        final int INCORRECT_USER_INPUT = -1;
        System.out.println("1 - Add product to list");
        System.out.println("2 - Delete product from list");
        System.out.println("3 - Update product from list");
        System.out.println("4 - View products list");
        System.out.println("5 - Return");
        try {
            userInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }
}