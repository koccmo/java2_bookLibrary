package internet_store.core.service.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.UpdateClientRequest;
import internet_store.core.response.client.UpdateClientResponse;
import internet_store.core.validate.NumberValidator;
import internet_store.database.interfaces.ClientDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class UpdateClientService {
    @Autowired
    ClientDatabase clientDatabase;

    public UpdateClientResponse execute(UpdateClientRequest updateClientRequest) {
        NumberValidator<?> numberValidator = new NumberValidator<>(updateClientRequest.getId());

        List<CoreError> errors = numberValidator.validate();

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