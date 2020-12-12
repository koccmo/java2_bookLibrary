package internet_store.core.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.BuyProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.BuyProductResponse;
import internet_store.core.services.product.validators.BuyProductRequestValidator;
import internet_store.database.product.ProductDatabase;
import internet_store.dependency_injection.DIDependency;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BuyProductService {

    @DIDependency private ProductDatabase productDatabase;
    @DIDependency private BuyProductRequestValidator buyProductRequestValidator;
    private Map<Product, Integer> shoppingCart = new HashMap<>();

    public BuyProductResponse execute (BuyProductRequest buyProductRequest) {
        List<CoreError> errors = buyProductRequestValidator.validate(buyProductRequest);

        if (!errors.isEmpty()) {
            return new BuyProductResponse(errors);
        }

        if (!productDatabase.containsId(buyProductRequest.getId())) {
            errors.add(new CoreError("database", "Database doesn't contain id " +
                    buyProductRequest.getId()));
            return new BuyProductResponse(errors);
        }

        return new BuyProductResponse(productDatabase.findById(buyProductRequest.getId()).get(), buyProductRequest.getQuantity(), buyProductRequest.getEndOfShopping());
    }
}
