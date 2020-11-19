package lesson_3.core.request.add_product.product_items;

import lombok.Getter;

public class AddProductQuantityRequest {
    @Getter
    private final int quantity;

    public AddProductQuantityRequest(int quantity) {
        this.quantity = quantity;
    }
}
