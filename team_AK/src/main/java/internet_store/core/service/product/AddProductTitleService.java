package internet_store.core.service.product;

import org.springframework.stereotype.Component;
import internet_store.core.core_error.CoreError;
import internet_store.core.request.product.product_items.AddProductTitleRequest;
import internet_store.core.response.product.product_item.AddProductTitleResponse;
import internet_store.core.validate.StringTypeValidator;

import java.util.List;

@Component
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