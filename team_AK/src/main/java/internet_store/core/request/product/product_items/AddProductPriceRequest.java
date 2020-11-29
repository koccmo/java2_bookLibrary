package internet_store.core.request.product.product_items;

import lombok.Getter;

import java.math.BigDecimal;

public class AddProductPriceRequest {
    @Getter
    private final BigDecimal price;

    public AddProductPriceRequest(BigDecimal price) {
        this.price = price;
    }
}
