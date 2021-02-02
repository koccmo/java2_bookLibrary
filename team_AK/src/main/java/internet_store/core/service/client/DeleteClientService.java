package internet_store.core.service.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Client;
import internet_store.core.request.client.DeleteClientRequest;
import internet_store.core.response.client.DeleteClientResponse;
import internet_store.core.validate.NumberValidator;
import internet_store.database.client_database.ClientDatabaseImpl;
import internet_store.persistence.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeleteClientService {

    public DeleteClientResponse execute(DeleteClientRequest request) {
        NumberValidator<?> numberValidator = new NumberValidator<>(request.getId());

        List<CoreError> errors = numberValidator.validate();

        Object databases = request.getClientDatabase();

        if (databases instanceof ClientRepository) {
            ((ClientRepository) databases).delete(request.getClient());
            return new DeleteClientResponse(new ArrayList<>());
        }

        if (databases instanceof ClientDatabaseImpl) {
            if (((ClientDatabaseImpl) databases).isIdExist(request.getId())) {
                Client deletedClient = ((ClientDatabaseImpl) databases).findById(request.getId());
                ((ClientDatabaseImpl) databases).deleteClient(deletedClient);
            } else {
                errors.add(new CoreError("Id error ", "wrong ID"));
            }
        }

        if (errors.isEmpty()) {
            return new DeleteClientResponse(request.getId());
        }
        return new DeleteClientResponse(errors);
    }
}