package internet_store.application.core.services;

import internet_store.application.core.database.Database;
import internet_store.application.core.requests.FindByIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.FindByIdResponse;
import internet_store.application.core.services.validators.FindByIdValidator;
import internet_store.application.dependency_injection.DIDependency;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindByIdService {

    @DIDependency private Database database;
    @DIDependency private FindByIdValidator validator;

    public FindByIdResponse execute(FindByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindByIdResponse(errors);
        }

        Long id = Long.parseLong(request.getProductId());
        return new FindByIdResponse(database.findById(id));
    }
}

