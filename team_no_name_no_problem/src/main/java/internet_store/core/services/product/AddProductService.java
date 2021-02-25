package internet_store.core.services.product;

import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.services.product.validators.AddProductRequestValidator;
import internet_store.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class  AddProductService {

    @Autowired private ProductDatabase productDatabase;
    @Autowired private AddProductRequestValidator addProductRequestValidator;

    public AddProductResponse execute(AddProductRequest addProductRequest){

        List<CoreError>errors = addProductRequestValidator.validate(addProductRequest);
        if (!errors.isEmpty()){
            return new AddProductResponse(errors);
        }

        if (productDatabase.containsProduct(addProductRequest.getProduct())){
            errors.add(new CoreError("database", "Database contains the same product"));
            return new AddProductResponse(errors);
        }

        productDatabase.add(addProductRequest.getProduct());
        return new AddProductResponse(addProductRequest.getProduct());
    }

}
