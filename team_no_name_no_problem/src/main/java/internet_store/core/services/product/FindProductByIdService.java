package internet_store.core.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.FindByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.FindByIdResponse;
import internet_store.core.services.product.validators.FindByIdRequestValidator;
import internet_store.database.product.ProductDatabase;
import internet_store.dependency_injection.DIComponent;
import internet_store.dependency_injection.DIDependency;

import java.util.List;
import java.util.Optional;

@DIComponent
public class FindProductByIdService {

    @DIDependency private ProductDatabase productDatabase;
    @DIDependency private FindByIdRequestValidator findByIdRequestValidator;

    public FindByIdResponse execute (FindByIdRequest findByIdRequest){

        List<CoreError> errors = findByIdRequestValidator.validate(findByIdRequest);

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
