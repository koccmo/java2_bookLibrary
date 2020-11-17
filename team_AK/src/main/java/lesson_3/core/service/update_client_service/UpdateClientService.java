package lesson_3.core.service.update_client_service;

import lesson_3.ProductListApplication;
import lesson_3.core.core_error.CoreError;
import lesson_3.core.domain.Client;
import lesson_3.core.request.update_client.UpdateClientRequest;
import lesson_3.core.response.update_client.UpdateClientResponse;
import lesson_3.core.validate.NegativeNumberValidator;
import lesson_3.database.client_database.InnerClientDatabase;

import java.util.List;

public class UpdateClientService {
    private final InnerClientDatabase clientDatabase;

    public UpdateClientService(InnerClientDatabase clientDatabase) {
        this.clientDatabase = clientDatabase;
    }

    public UpdateClientResponse execute(UpdateClientRequest updateClientRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(updateClientRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        List<Client> clients = clientDatabase.getAllClients();

        if (!(isIdExist(clients, updateClientRequest.getId()))) {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new UpdateClientResponse(updateClientRequest.getId());
        }
        return new UpdateClientResponse(errors);
    }

    private boolean isIdExist(List<Client> clients, long id) {
        return ProductListApplication.findClientService.isIdExist(clients, id);
    }
}