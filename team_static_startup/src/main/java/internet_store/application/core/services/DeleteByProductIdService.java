package internet_store.application.core.services;

import internet_store.application.core.database.Database;
import internet_store.application.core.requests.DeleteByProductIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.DeleteByProductIdResponse;
import internet_store.application.core.services.validators.DeleteByProductIdValidator;
import internet_store.application.dependency_injection.DIDependency;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteByProductIdService {

    @DIDependency private Database database;
    @DIDependency private DeleteByProductIdValidator validator;

    public DeleteByProductIdResponse execute(DeleteByProductIdRequest productIdRequest) {
        List<CoreError> errors = validator.validate(productIdRequest);
        Long id = productIdRequest.getProductId();

        if (!errors.isEmpty()){
            return new DeleteByProductIdResponse(errors);
        } else return new DeleteByProductIdResponse(database.deleteByProductId(id));
    }

}
