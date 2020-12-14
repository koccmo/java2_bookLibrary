package internet_store.core.services.product.validators;

import internet_store.core.requests.product.BuyProductRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuyProductRequestValidator {

    public List<CoreError> validate (BuyProductRequest buyProductRequest) {
        List <CoreError> errors = new ArrayList<>();

        if (buyProductRequest.getId() == null || buyProductRequest.getId() < 1) {
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        if (buyProductRequest.getQuantity() == null || buyProductRequest.getQuantity() < 1) {
            errors.add(new CoreError("quantity", "Not valid input for quantity"));
        }

        return errors;
    }
}
