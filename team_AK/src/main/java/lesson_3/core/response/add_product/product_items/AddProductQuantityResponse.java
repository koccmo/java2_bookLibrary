package lesson_3.core.response.add_product.product_items;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class AddProductQuantityResponse extends CoreErrorResponse {
    @Getter
    private int quantity;

    public AddProductQuantityResponse(int quantity) {
        this.quantity = quantity;
    }

    public AddProductQuantityResponse(List<CoreError> errors) {
        super(errors);
    }
}
