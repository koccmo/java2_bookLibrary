package lesson_2.user_interface.console.product_fields;

import internet_store.core.domain.Product;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SetPriceHandler extends InputHandler {
    public SetPriceHandler() {
        super();
    }

    @Override
    public void getInput(Product product) {
        BigDecimal userInput = new BigDecimal("0.00");
        boolean errorInput;
        System.out.println(ProductFieldsResources.PRICE.getText());
        do {
            try {
                userInput = new Scanner(System.in).nextBigDecimal();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                System.out.println("Enter product price:");
                errorInput = true;
            }
        } while (errorInput);
        product.setPrice(userInput);
    }
}