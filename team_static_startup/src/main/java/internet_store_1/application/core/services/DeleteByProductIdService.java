package internet_store_1.application.core.services;

import internet_store_1.application.core.requests.DeleteByProductIdRequest;
import internet_store_1.application.core.responses.*;
import internet_store_1.application.database.Database;

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
