package lesson_3.core.service.add_product.product_items_service;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.request.add_product.product_items.AddProductTitleRequest;
import lesson_3.core.response.add_product.product_items.AddProductTitleResponse;
import lesson_3.core.validate.StringTypeValidator;

import java.util.List;

public class AddProductTitleService {
    public AddProductTitleResponse execute(AddProductTitleRequest addProductTitleRequest) {
        StringTypeValidator stringTypeValidator = new StringTypeValidator();
        List<CoreError> errors = stringTypeValidator.validate(addProductTitleRequest.getProductTitle());

        if (errors.isEmpty()) {
            return new AddProductTitleResponse(addProductTitleRequest.getProductTitle());
        }
        return new AddProductTitleResponse(errors);
    }
}
