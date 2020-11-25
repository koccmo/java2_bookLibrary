package internet_store_1.core.services.product;

import internet_store_1.core.requests.product.AddProductRequest;
import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.product.AddProductResponse;
import internet_store_1.database.product.ProductDatabase;

import java.util.List;

public class AddProductService {

    private final ProductDatabase productDatabase;
    private final AddProductRequestValidator addProductRequestValidator;

    public AddProductService(ProductDatabase productDatabase, AddProductRequestValidator addProductRequestValidator) {
        this.productDatabase = productDatabase;
        this.addProductRequestValidator = addProductRequestValidator;
    }

    public AddProductResponse execute(AddProductRequest addProductRequest){

        List<CoreError>errors = addProductRequestValidator.validate(addProductRequest);
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
