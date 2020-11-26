package internet_store.user_interface.administrator_menu.create_product_menu.add_product_menu;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddProductPrice {
    @Getter
    private BigDecimal userProductPriceInput = new BigDecimal("0.00");

    public void showMenuProductPrice() {
        boolean errorInput;
        System.out.println("Enter product price:");
        do {
            try {
                userProductPriceInput = new Scanner(System.in).nextBigDecimal();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                System.out.println("Enter product price:");
                errorInput = true;
            }
        } while (errorInput);
    }
}
