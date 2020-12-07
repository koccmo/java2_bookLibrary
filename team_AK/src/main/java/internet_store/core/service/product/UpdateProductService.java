package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.product.UpdateProductRequest;
import internet_store.core.response.product.UpdateProductResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.product_database.InnerProductDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

import java.util.List;
@DIComponent
public class UpdateProductService {
    @DIDependency
    InnerProductDatabase productDatabase;

    public UpdateProductResponse execute(UpdateProductRequest updateProductRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(updateProductRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

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