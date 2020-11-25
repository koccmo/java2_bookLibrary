package internet_store.core.service.add_product.product_items_service;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.add_product.product_items.AddProductTitleRequest;
import internet_store.core.response.add_product.product_items.AddProductTitleResponse;
import internet_store.core.validate.StringTypeValidator;

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