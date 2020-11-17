package lesson_3.core.service.delete_client;

import lesson_3.ProductListApplication;
import lesson_3.core.core_error.CoreError;
import lesson_3.core.domain.Client;
import lesson_3.core.request.delete_client.DeleteClientRequest;
import lesson_3.core.response.delete_client.DeleteClientResponse;
import lesson_3.core.validate.NegativeNumberValidator;
import lesson_3.database.client_database.InnerClientDatabase;

import java.util.List;

public class DeleteClientService {
    private final InnerClientDatabase clientDatabase;

    public DeleteClientService(InnerClientDatabase clientDatabase) {
        this.clientDatabase = clientDatabase;
    }

    public DeleteClientResponse execute(DeleteClientRequest deleteClientRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(deleteClientRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        List<Client> clients = clientDatabase.getAllClients();

        if (isIdExist(clients, deleteClientRequest.getId())) {
            Client deletedClient = findProductById(clients, deleteClientRequest.getId());
            ProductListApplication.clientDatabase.deleteClient(deletedClient);
        } else {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new DeleteClientResponse(deleteClientRequest.getId());
        }
        return new DeleteClientResponse(errors);
    }

    private boolean isIdExist(List<Client> clients, long id) {
        return ProductListApplication.findClientService.isIdExist(clients, id);
    }

    private Client findProductById(List<Client> clients, long id) {
        return ProductListApplication.findClientService.findById(clients, id);
    }
}
