package lesson_3.core.request.add_product_to_cart;

import lombok.Getter;

public class AddProductToCartRequest {
    @Getter
    private final long id;
    @Getter
    private final int quantity;

    public AddProductToCartRequest(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}