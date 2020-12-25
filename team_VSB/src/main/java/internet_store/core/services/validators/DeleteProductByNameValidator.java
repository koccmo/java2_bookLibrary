package internet_store.core.services.validators;

import internet_store.core.requests.DeleteProductByNameRequest;
import internet_store.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteProductByNameValidator {

    public List<CoreError> validate(DeleteProductByNameRequest nameRequest) {
        List<CoreError> error = new ArrayList<>();

        String productName = nameRequest.getProductName();
        if (productName == null || productName.isEmpty()) {
            error.add(new CoreError("Product name", "Product name can't be empty."));
        }
        return error;
    }
}
