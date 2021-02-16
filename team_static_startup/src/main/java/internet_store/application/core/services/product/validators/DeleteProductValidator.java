package internet_store.application.core.services.product.validators;

import internet_store.application.core.requests.product.DeleteProductRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteProductValidator {

    public List<CoreError> validate(DeleteProductRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(e -> errors.add(e));
        return errors;
    }

    private Optional<CoreError> validateId(DeleteProductRequest request){
        return (request.getId() == null)
                ? Optional.of(new CoreError("id", "must not be empty!"))
                : Optional.empty();
    }

}
