package internet_store.core.service.add_product.product_items_service;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.add_product.product_items.AddProductPriceRequest;
import internet_store.core.response.add_product.product_items.AddProductPriceResponse;
import internet_store.core.validate.NegativeNumberValidator;

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