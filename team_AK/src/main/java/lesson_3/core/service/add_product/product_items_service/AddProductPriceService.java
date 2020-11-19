package lesson_3.core.service.add_product.product_items_service;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.request.add_product.product_items.AddProductPriceRequest;
import lesson_3.core.response.add_product.product_items.AddProductPriceResponse;
import lesson_3.core.validate.NegativeNumberValidator;

import java.util.List;

public class AddProductPriceService {
    public AddProductPriceResponse execute(AddProductPriceRequest addProductPriceRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(addProductPriceRequest.getPrice());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (errors.isEmpty()) {
            return new AddProductPriceResponse(addProductPriceRequest.getPrice());
        }
        return new AddProductPriceResponse(errors);
    }
}
