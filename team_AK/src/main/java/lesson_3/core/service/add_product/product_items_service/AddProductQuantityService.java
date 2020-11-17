package lesson_3.core.service.add_product.product_items_service;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.request.add_product.product_items.AddProductQuantityRequest;
import lesson_3.core.response.add_product.product_items.AddProductQuantityResponse;
import lesson_3.core.validate.NegativeNumberValidator;

import java.util.List;

public class AddProductQuantityService {

    public AddProductQuantityResponse execute(AddProductQuantityRequest addProductQuantityRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(addProductQuantityRequest.getQuantity());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (errors.isEmpty()) {
            return new AddProductQuantityResponse(addProductQuantityRequest.getQuantity());
        }
        return new AddProductQuantityResponse(errors);
    }
}