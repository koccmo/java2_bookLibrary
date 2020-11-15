package internet_store.core.services.product;

import internet_store.core.requests.product.FindAnyByTitleRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.FindAnyByTitleResponse;
import internet_store.database.product.ProductDatabase;
import internet_store.core.domain.Product;

import java.util.List;
import java.util.Optional;

public class FindAnyByTitleService {

    private final ProductDatabase productDatabase;
    private final FindAnyByTitleRequestValidator findAnyByTitleRequestValidator;

    public FindAnyByTitleService(ProductDatabase productDatabase,
                                 FindAnyByTitleRequestValidator findAnyByTitleRequestValidator) {
        this.productDatabase = productDatabase;
        this.findAnyByTitleRequestValidator = findAnyByTitleRequestValidator;
    }

    public FindAnyByTitleResponse execute(FindAnyByTitleRequest findAnyByTitleRequest){

        List<CoreError> errors = findAnyByTitleRequestValidator.validate(findAnyByTitleRequest);

        if (!errors.isEmpty()){
            return new FindAnyByTitleResponse(errors);
        }

        if (productDatabase.findAnyByTitle(findAnyByTitleRequest.getTitle()).isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain product with title "
                    + findAnyByTitleRequest.getTitle()));
            return new FindAnyByTitleResponse(errors);
        }

        return new FindAnyByTitleResponse(productDatabase.findAnyByTitle(findAnyByTitleRequest.getTitle()));
    }
}
