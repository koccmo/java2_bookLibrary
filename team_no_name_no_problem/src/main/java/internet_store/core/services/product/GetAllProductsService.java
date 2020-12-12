package internet_store.core.services.product;

import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.validators.GetAllProductsValidator;
import internet_store.database.product.ProductDatabase;
import internet_store.core.domain.Product;
import internet_store.dependency_injection.DIDependency;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllProductsService {

    @DIDependency private ProductDatabase productDatabase;
    @DIDependency private GetAllProductsValidator getAllProductsValidator;

    public GetProductsResponse execute(GetProductsRequest getProductsRequest){
        List <CoreError> errors = getAllProductsValidator.validate(getProductsRequest);

        if (productDatabase.getProducts().isEmpty()){
            errors.add(new CoreError("database", "Database is empty"));
            return new GetProductsResponse(errors, new ArrayList<>());
        }
        List<Product>products = productDatabase.getProducts();
        return new GetProductsResponse(products);
    }
}