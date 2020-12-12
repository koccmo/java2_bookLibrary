package internet_store.core.service.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.UpdateClientRequest;
import internet_store.core.response.client.UpdateClientResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.client_database.InnerClientDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class UpdateClientService {
    @Autowired
    InnerClientDatabase clientDatabase;

    public UpdateClientResponse execute(UpdateClientRequest updateClientRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(updateClientRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (!(isIdExist(updateClientRequest.getId()))) {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new UpdateClientResponse(updateClientRequest.getId());
        }
        return new UpdateClientResponse(errors);
    }

    private boolean isIdExist(long id) {
        return clientDatabase.isIdExist(id);
    }
}