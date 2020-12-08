package internet_store.core.services.product;

import internet_store.core.requests.product.BuyProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.BuyProductResponse;
import internet_store.core.services.product.validators.BuyProductRequestValidator;
import internet_store.database.product.ProductDatabase;

import java.util.List;

public class BuyProductService {

    private final ProductDatabase productDatabase;
    private final BuyProductRequestValidator buyProductRequestValidator;

    public BuyProductService(ProductDatabase productDatabase, BuyProductRequestValidator buyProductRequestValidator) {
        this.productDatabase = productDatabase;
        this.buyProductRequestValidator = buyProductRequestValidator;
    }

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

        return new BuyProductResponse(buyProductRequest.getId(), buyProductRequest.getQuantity());
    }
}
