package internet_store.application.core.services;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.FindByProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.FindByProductNameResponse;
import internet_store.application.database.Database;

import java.util.List;
import java.util.Optional;

public class FindProductService {

    private final Database database;
    private final FindProductValidator validator;

    public FindProductService(Database database, FindProductValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public FindByProductNameResponse findByProductName(FindByProductNameRequest productNameRequest) {
        List<CoreError> errors = validator.validate(productNameRequest);
        if (!errors.isEmpty()){
            FindByProductNameResponse responseErrors = new FindByProductNameResponse
                    .ResponseBuilder().withListOfErrors(errors).build();
            return responseErrors ;
        }

        List<Product> byProductName = database.findByProductName(productNameRequest.getProductName());

        FindByProductNameResponse response = new FindByProductNameResponse
                .ResponseBuilder().withListOfFoundProducts(byProductName).build();
        return response;
    }

    public Optional<Product> findById(Long productId) {
        return database.findById(productId);
    }

}
