package internet_store.core.services.product;

import internet_store.core.requests.product.FindAllByTitleRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.FindAllByTitleResponse;
import internet_store.database.product.ProductDatabase;
import internet_store.core.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class FindAllByTitleService {

    private final ProductDatabase productDatabase;
    private final FindAllProductsByTitleRequestValidator findAllProductsByTitleRequestValidator;

    public FindAllByTitleService(ProductDatabase productDatabase,
                                 FindAllProductsByTitleRequestValidator findAllProductsByTitleRequestValidator) {
        this.productDatabase = productDatabase;
        this.findAllProductsByTitleRequestValidator = findAllProductsByTitleRequestValidator;
    }

    public FindAllByTitleResponse execute(FindAllByTitleRequest findAllByTitleRequest){

        List <CoreError> errors = findAllProductsByTitleRequestValidator.validate(findAllByTitleRequest);

        if (!errors.isEmpty()){
            return new FindAllByTitleResponse(errors, new ArrayList<>());
        }

        if (productDatabase.findAllByTitle(findAllByTitleRequest.getTitle()).isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain product with title " +
                    findAllByTitleRequest.getTitle()));
            return new FindAllByTitleResponse(errors, new ArrayList<>());
        }
        return new FindAllByTitleResponse(productDatabase.findAllByTitle(findAllByTitleRequest.getTitle()));
    }
}
