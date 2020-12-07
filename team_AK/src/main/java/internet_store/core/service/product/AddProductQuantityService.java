package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.product.product_items.AddProductQuantityRequest;
import internet_store.core.response.product.product_item.AddProductQuantityResponse;
import internet_store.core.validate.NegativeNumberValidator;
import dependency.annotation.DIComponent;

import java.util.List;

@DIComponent
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