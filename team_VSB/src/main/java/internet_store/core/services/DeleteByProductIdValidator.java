package internet_store.core.services;

import internet_store.core.requests.DeleteProductByIdRequest;
import internet_store.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteByProductIdValidator {

    public List<CoreError> validate(DeleteProductByIdRequest request) {
        List<CoreError> error = new ArrayList<>();

        Long productId = request.getProductId();
        if (productId == null) {
            error.add(new CoreError("Product ID", "Product ID can't be empty!"));
        }
        return error;
    }
}
