package internet_store.lesson_6.core.services;

import internet_store.lesson_6.core.database.Database;
import internet_store.lesson_6.core.requests.DeleteByProductNameRequest;
import internet_store.lesson_6.core.responses.CoreError;
import internet_store.lesson_6.core.responses.DeleteByProductNameResponse;
import internet_store.lesson_6.core.services.validators.DeleteByProductNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteByProductNameService {

    @Autowired
    private Database database;
    @Autowired
    private DeleteByProductNameValidator deleteByNameValidator;

    public DeleteByProductNameResponse execute(DeleteByProductNameRequest productNameRequest) {
        List<CoreError> errors = deleteByNameValidator.validate(productNameRequest);
        if (!errors.isEmpty()) {
            return new DeleteByProductNameResponse(errors);
        }
        boolean isRemoved = database.deleteByProductName(productNameRequest.getProductName());
        return new DeleteByProductNameResponse(isRemoved);
    }

}
