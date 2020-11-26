package internet_store.core.response.add_product.product_items;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

public class AddProductPriceResponse extends CoreErrorResponse {
    @Getter
    private BigDecimal price;

    public AddProductPriceResponse(BigDecimal quantity) {
        this.price = quantity;
    }

    public AddProductPriceResponse(List<CoreError> errors) {
        super(errors);
    }
}
