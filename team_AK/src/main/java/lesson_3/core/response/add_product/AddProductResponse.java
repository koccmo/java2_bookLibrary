package lesson_3.core.response.add_product;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.core_error.CoreErrorResponse;

import java.util.List;

public class AddProductResponse extends CoreErrorResponse {

    public AddProductResponse(List<CoreError> errors) {
        super(errors);
    }
}
