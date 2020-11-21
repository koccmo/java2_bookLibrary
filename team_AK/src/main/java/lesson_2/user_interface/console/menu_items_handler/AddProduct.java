package lesson_2.user_interface.console.menu_items_handler;

import internet_store.domain.Product;
import lesson_1.product_fields.*;

import java.util.ArrayList;
import java.util.List;

public class AddProduct {
    private final List<InputHandler> productFields = new ArrayList<>();

    public AddProduct() {
        productFields.add(new SetTitleHandler());
        productFields.add(new SetDescriptionHandler());
        productFields.add(new SetQuantityHandler());
        productFields.add(new SetPriceHandler());
    }

    public Product addTo(Product product) {
        productFields.forEach(i -> i.getInput(product));
        return product;
    }
}