package internet_store.core.service.add_product.product_items_service;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.add_product.product_items.AddProductQuantityRequest;
import internet_store.core.response.add_product.product_items.AddProductQuantityResponse;
import internet_store.core.validate.NegativeNumberValidator;

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