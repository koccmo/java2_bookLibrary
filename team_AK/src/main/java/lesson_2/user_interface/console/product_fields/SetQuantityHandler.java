package lesson_2.user_interface.console.product_fields;

import internet_store.core.domain.Product;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SetQuantityHandler extends InputHandler {

    @Override
    public void getInput(Product product) {
        BigDecimal userInput = new BigDecimal("0");
        boolean errorInput;
        System.out.println(ProductFieldsResources.QUANTITY.getText());
        do {
            try {
                userInput = new BigDecimal(String.valueOf(new Scanner(System.in).nextInt()));
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                System.out.println("Enter product Quantity:");
                errorInput = true;
            }
        } while (errorInput);
        product.setQuantity(userInput);
    }
}