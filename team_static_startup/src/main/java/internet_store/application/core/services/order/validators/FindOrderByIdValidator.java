package internet_store.application.core.services.order.validators;

import internet_store.application.core.requests.order.FindOrderByIdRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindOrderByIdValidator {

    public List<CoreError> validate(FindOrderByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getId() == null || request.getId() <= 0) {
            errors.add(new CoreError("ID", "should be greater than zero"));
        }
        return errors;
    }

}
