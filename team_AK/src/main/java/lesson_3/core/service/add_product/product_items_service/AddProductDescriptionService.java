package lesson_3.core.service.add_product.product_items_service;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.request.add_product.product_items.AddProductDescriptionRequest;
import lesson_3.core.response.add_product.product_items.AddProductDescriptionResponse;
import lesson_3.core.validate.StringTypeValidator;

import java.util.List;

public class AddProductDescriptionService {
    public AddProductDescriptionResponse execute(AddProductDescriptionRequest addProductDescriptionRequest) {
        StringTypeValidator stringTypeValidator = new StringTypeValidator();
        List<CoreError> errors = stringTypeValidator.validate(addProductDescriptionRequest.getProductDescription());

        if (errors.isEmpty()) {
            return new AddProductDescriptionResponse(addProductDescriptionRequest.getProductDescription());
        }
        return new AddProductDescriptionResponse(errors);
    }
}