package internet_store.lesson_6.core.services;

import internet_store.lesson_6.core.database.Database;
import internet_store.lesson_6.core.requests.FindByIdRequest;
import internet_store.lesson_6.core.responses.CoreError;
import internet_store.lesson_6.core.responses.FindByIdResponse;
import internet_store.lesson_6.core.services.validators.FindByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindByIdService {

    @Autowired
    private Database database;
    @Autowired
    private FindByIdValidator validator;

    public FindByIdResponse execute(FindByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindByIdResponse(errors);
        }

        Long id = Long.parseLong(request.getProductId());
        return new FindByIdResponse(database.findById(id));
    }
}

