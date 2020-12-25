package internet_store.lesson_6.core.services;

import internet_store.lesson_6.core.database.Database;
import internet_store.lesson_6.core.requests.ChangeProductNameRequest;
import internet_store.lesson_6.core.responses.ChangeProductNameResponse;
import internet_store.lesson_6.core.responses.CoreError;
import internet_store.lesson_6.core.services.validators.ChangeProductNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChangeProductNameService {

    @Autowired
    private Database database;
    @Autowired
    private ChangeProductNameValidator validator;

    public ChangeProductNameResponse execute(ChangeProductNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = request.getProductId();
        String newName = request.getProductNewName();

        if (!errors.isEmpty()) {
            return new ChangeProductNameResponse(errors);
        } else return new ChangeProductNameResponse(database.changeProductName(id, newName));
    }


}
