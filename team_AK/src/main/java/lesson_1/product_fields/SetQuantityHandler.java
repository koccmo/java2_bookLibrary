package lesson_1.product_fields;

import internet_store.domain.Product;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SetQuantityHandler extends InputHandler {
    public SetQuantityHandler() {
        super();
    }

    @Override
    public void getInput(Product product) {
        int userInput = 0;
        boolean errorInput;
        System.out.println(ProductFieldsResources.QUANTITY.getText());
        do {
            try {
                userInput = new Scanner(System.in).nextInt();
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
