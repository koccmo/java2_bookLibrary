package internet_store.core.request.product.product_items;

import lombok.Getter;

public class AddProductQuantityRequest {
    @Getter
    private final Long quantity;

    public AddProductQuantityRequest(Long quantity) {
        this.quantity = quantity;
    }
}
