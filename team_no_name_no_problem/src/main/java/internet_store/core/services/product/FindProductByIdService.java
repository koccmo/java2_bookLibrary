package internet_store.core.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.FindByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.FindByIdResponse;
import internet_store.database.product.ProductDatabase;

import java.util.List;
import java.util.Optional;

public class FindProductByIdService {

    private final ProductDatabase productDatabase;
    private final FindByIdValidator findByIdValidator;

    public FindProductByIdService(ProductDatabase productDatabase, FindByIdValidator findByIdValidator) {
        this.productDatabase = productDatabase;
        this.findByIdValidator = findByIdValidator;
    }

    public FindByIdResponse execute (FindByIdRequest findByIdRequest){

        List<CoreError> errors = findByIdValidator.validate(findByIdRequest);

        if (!errors.isEmpty()){
            return new FindByIdResponse(errors);
        }

        Optional<Product> expectedProduct = productDatabase.findById(findByIdRequest.getId());

        if (expectedProduct.isEmpty()) {
            errors.add(new CoreError("database", "Database doesn't contain product with id "
                    + findByIdRequest.getId()));
            return new FindByIdResponse(errors);
        }

        return new FindByIdResponse(productDatabase.findById(findByIdRequest.getId()).get());

    }
}
