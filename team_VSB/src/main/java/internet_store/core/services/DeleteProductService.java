package internet_store.core.services;

import internet_store.core.database.Database;
import internet_store.core.domain.Product;
import internet_store.core.requests.DeleteProductByNameDescriptionPriceRequest;
import internet_store.core.requests.DeleteProductByNameRequest;
import internet_store.core.responses.CoreError;
import internet_store.core.responses.DeleteProductByNameResponse;
import internet_store.core.responses.DeleteProductResponse;
import internet_store.core.services.validators.DeleteByProductValidator;
import internet_store.core.services.validators.DeleteProductByNameValidator;

import java.util.List;

public class DeleteProductService {

    private final Database database;
    private DeleteByProductValidator deleteProductValidator;
    private DeleteProductByNameValidator deleteProductByNameValidator;

    public DeleteProductService(Database database, DeleteByProductValidator deleteProductValidator, DeleteProductByNameValidator deleteProductByNameValidator) {
        this.database = database;
        this.deleteProductValidator = deleteProductValidator;
    }

    public DeleteProductService(Database database, DeleteProductByNameValidator deleteByNameValidator) {
        this.database = database;
        this.deleteProductByNameValidator = deleteByNameValidator;
    }

    public DeleteProductByNameResponse deleteProductByName(DeleteProductByNameRequest nameRequest) {
        List<CoreError> error = deleteProductByNameValidator.validate(nameRequest);
        if (!error.isEmpty()) {
            return new DeleteProductByNameResponse(error);
        }

        boolean isDeleted = database.deleteByProductName(nameRequest.getProductName());

        return new DeleteProductByNameResponse(isDeleted);
    }

    public DeleteProductResponse deleteProduct(DeleteProductByNameDescriptionPriceRequest request) {
        List<CoreError> errors = deleteProductValidator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductResponse(errors);
        }

        Product productToDelete = new Product(request.getProductName(), request.getProductDescription(),
                request.getProductPrice());
        database.delete(productToDelete);
        return new DeleteProductResponse(productToDelete);
    }


    public boolean delete(Product product) { return database.delete(product); }
}
