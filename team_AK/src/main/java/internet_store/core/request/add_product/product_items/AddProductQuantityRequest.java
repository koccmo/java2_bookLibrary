package internet_store.core.request.add_product.product_items;

import lombok.Getter;

import java.math.BigDecimal;

public class AddProductQuantityRequest {
    @Getter
    private final BigDecimal quantity;

    public AddProductQuantityRequest(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
