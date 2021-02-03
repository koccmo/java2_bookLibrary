package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.product.UpdateProductRequest;
import internet_store.core.response.product.UpdateProductResponse;
import internet_store.core.validate.NumberValidator;
import internet_store.database.interfaces.ProductDatabase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateProductService {
    ProductDatabase productDatabase;

    public UpdateProductResponse execute(UpdateProductRequest updateProductRequest) {
        productDatabase = updateProductRequest.getProductDatabase();

        NumberValidator<?> numberValidator = new NumberValidator<>(updateProductRequest.getId());

        List<CoreError> errors = numberValidator.validate();

        if (!(isIdExist(updateProductRequest.getId()))) {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new UpdateProductResponse(updateProductRequest.getId());
        }
        return new UpdateProductResponse(errors);
    }

    private boolean isIdExist(long id) {
        return productDatabase.isIdExist(id);
    }
}