package internet_store.application.core.services;

import internet_store.application.core.database.Database;
import internet_store.application.core.requests.ChangeProductNameRequest;
import internet_store.application.core.responses.ChangeProductNameResponse;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.services.validators.ChangeProductNameValidator;
import internet_store.application.dependency_injection.DIDependency;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChangeProductNameService {

    @DIDependency private Database database;
    @DIDependency private ChangeProductNameValidator validator;

    public ChangeProductNameResponse execute(ChangeProductNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = request.getProductId();
        String newName = request.getProductNewName();

        if (!errors.isEmpty()){
            return new ChangeProductNameResponse(errors);
        } else return new ChangeProductNameResponse(database.changeProductName(id, newName));
    }


}
