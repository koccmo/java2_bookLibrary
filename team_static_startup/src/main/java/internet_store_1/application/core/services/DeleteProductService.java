package internet_store_1.application.core.services;

import internet_store_1.application.core.domain.Product;
import internet_store_1.application.core.requests.*;
import internet_store_1.application.core.responses.*;
import internet_store_1.application.database.Database;

import java.util.List;

public class DeleteProductService {

    private final Database database;
    private final DeleteByProductNameValidator validator;

    public DeleteProductService(Database database, DeleteByProductNameValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DeleteByProductNameResponse deleteByProductName(DeleteByProductNameRequest productNameRequest) {
        List<CoreError> errors = validator.validate(productNameRequest);
        if (!errors.isEmpty()){
            return new DeleteByProductNameResponse(errors);
        }

        boolean isRemoved = database.deleteByProductName(productNameRequest.getProductName());

        return new DeleteByProductNameResponse(isRemoved);
    }

    public boolean delete(Product product) {
        return database.delete(product);
    }

}
