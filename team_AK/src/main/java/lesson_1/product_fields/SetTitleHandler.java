package lesson_1.product_fields;

import internet_store.domain.Product;

import java.util.Scanner;

public class SetTitleHandler extends InputHandler {
    public SetTitleHandler() {
        super();
    }

    @Override
    public void getInput(Product product) {
        System.out.println(ProductFieldsResources.TITLE.getText());
        String userInput = new Scanner(System.in).nextLine();
        product.setTitle(userInput);
    }
}
