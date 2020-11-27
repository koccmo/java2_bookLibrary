package internet_store.core.response.add_product.product_items;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

public class AddProductQuantityResponse extends CoreErrorResponse {
    @Getter
    private BigDecimal quantity;

    public AddProductQuantityResponse(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public AddProductQuantityResponse(List<CoreError> errors) {
        super(errors);
    }
}
