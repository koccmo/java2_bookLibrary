package internet_store.lesson_3.core.services;

import internet_store.lesson_3.core.domain.Product;
import internet_store.lesson_3.core.requests.FindByProductNameRequest;
import internet_store.lesson_3.core.responses.CoreError;
import internet_store.lesson_3.core.responses.FindByProductNameResponse;
import internet_store.lesson_3.core.services.validators.FindProductValidator;
import internet_store.lesson_3.database.Database;

import java.util.List;

public class FindByProductNameService {

    private final Database database;
    private final FindProductValidator validator;

    public FindByProductNameService(Database database, FindProductValidator validator) {
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

}
