package lesson_3.core.response.add_product.product_items;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class AddProductTitleResponse extends CoreErrorResponse {
    @Getter
    private String productTitle;

    public AddProductTitleResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddProductTitleResponse(String productTitle) {
        this.productTitle = productTitle;
    }
}
