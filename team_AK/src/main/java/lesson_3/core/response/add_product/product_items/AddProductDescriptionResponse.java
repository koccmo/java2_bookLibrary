package lesson_3.core.response.add_product.product_items;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class AddProductDescriptionResponse extends CoreErrorResponse {
    @Getter
    private String productDescription;

    public AddProductDescriptionResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddProductDescriptionResponse(String productDescription) {
        this.productDescription = productDescription;
    }
}
