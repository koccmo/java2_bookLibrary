package internet_store.lesson_3.core.services;

import internet_store.lesson_3.core.requests.DeleteByProductIdRequest;
import internet_store.lesson_3.core.responses.CoreError;
import internet_store.lesson_3.core.responses.DeleteByProductIdResponse;
import internet_store.lesson_3.core.services.validators.DeleteByProductIdValidator;
import internet_store.lesson_3.database.Database;

import java.util.List;

public class DeleteByProductIdService {

    private final Database database;
    private final DeleteByProductIdValidator validator;

    public DeleteByProductIdService(Database database, DeleteByProductIdValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DeleteByProductIdResponse deleteByProductId(DeleteByProductIdRequest productIdRequest) {
        List<CoreError> errors = validator.validate(productIdRequest);
        Long id = Long.parseLong(productIdRequest.getProductId());

        if (!errors.isEmpty()){
            return new DeleteByProductIdResponse(errors);
        } else return new DeleteByProductIdResponse(database.deleteByProductId(id));
    }

}
