package internet_store.core.service.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Client;
import internet_store.core.request.client.DeleteClientRequest;
import internet_store.core.response.client.DeleteClientResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.client_database.InnerClientDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class DeleteClientService {
    @Autowired
    InnerClientDatabase clientDatabase;

    public DeleteClientService(InnerClientDatabase clientDatabase) {
        this.clientDatabase = clientDatabase;
    }

    public DeleteClientResponse execute(DeleteClientRequest deleteClientRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(deleteClientRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (isIdExist(deleteClientRequest.getId())) {
            Client deletedClient = findProductById(deleteClientRequest.getId());
            clientDatabase.deleteClient(deletedClient);
        } else {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new DeleteClientResponse(deleteClientRequest.getId());
        }
        return new DeleteClientResponse(errors);
    }

    private boolean isIdExist(long id) {
        return clientDatabase.isIdExist(id);
    }

    private Client findProductById(long id) {
        return clientDatabase.findById(id);
    }
}