package internet_store.core.service.add_product.product_items_service;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.add_product.product_items.AddProductDescriptionRequest;
import internet_store.core.response.add_product.product_items.AddProductDescriptionResponse;
import internet_store.core.validate.StringTypeValidator;

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