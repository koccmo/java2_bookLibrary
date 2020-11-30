package internet_store.core.service.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.UpdateClientRequest;
import internet_store.core.response.client.UpdateClientResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.client_database.InnerClientDatabase;

import java.util.List;

public class UpdateClientService {
    private final InnerClientDatabase clientDatabase;

    public UpdateClientService(InnerClientDatabase clientDatabase) {
        this.clientDatabase = clientDatabase;
    }

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