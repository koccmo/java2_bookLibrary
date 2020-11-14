package internet_store.core.services.product;

import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.AddProductResponse;
import internet_store.database.product.ProductDatabase;
import internet_store.core.domain.Product;

import java.util.List;

public class AddProductService {

    private final ProductDatabase productDatabase;
    private final AddProductValidator addProductValidator;

    public AddProductService(ProductDatabase productDatabase, AddProductValidator addProductValidator) {
        this.productDatabase = productDatabase;
        this.addProductValidator = addProductValidator;
    }

    public AddProductResponse execute(AddProductRequest addProductRequest){

        List<CoreError>errors = addProductValidator.validate(addProductRequest);
        if (!errors.isEmpty()){
            return new AddProductResponse(errors);
        }

        if (productDatabase.getProducts().contains(addProductRequest.getProduct())){
            errors.add(new CoreError("database", "Database contains the same product"));
            return new AddProductResponse(errors);
        }

        productDatabase.add(addProductRequest.getProduct());
        return new AddProductResponse(addProductRequest.getProduct());
    }

}
