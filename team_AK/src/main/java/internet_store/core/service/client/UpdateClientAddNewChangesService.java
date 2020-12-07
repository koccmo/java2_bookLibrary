package internet_store.core.service.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Client;
import internet_store.core.request.client.AddClientRequest;
import internet_store.core.request.client.client_items.AddClientEmailRequest;
import internet_store.core.request.client.client_items.AddClientNameRequest;
import internet_store.core.request.client.client_items.AddClientPhoneRequest;
import internet_store.core.request.client.client_items.AddClientSurnameRequest;
import internet_store.core.response.client.AddClientResponse;
import internet_store.core.response.client.client_items.AddClientEmailResponse;
import internet_store.core.response.client.client_items.AddClientNameResponse;
import internet_store.core.response.client.client_items.AddClientPhoneResponse;
import internet_store.core.response.client.client_items.AddClientSurnameResponse;
import internet_store.database.client_database.InnerClientDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class UpdateClientAddNewChangesService implements ClientUpdate {
    @DIDependency
    InnerClientDatabase clientDatabase;

    public AddClientResponse execute(AddClientRequest addClientRequest) {
        List<CoreError> errors = new ArrayList<>();

        AddClientNameService nameService = new AddClientNameService();
        AddClientSurnameService surnameService = new AddClientSurnameService();
        AddClientPhoneService phoneService = new AddClientPhoneService();
        AddClientEmailService emailService = new AddClientEmailService();

        AddClientNameResponse nameResponse = nameService.execute(new AddClientNameRequest
                (addClientRequest.getClient().getName()));
        AddClientSurnameResponse surnameResponse = surnameService.execute(new AddClientSurnameRequest
                (addClientRequest.getClient().getSurname()));
        AddClientPhoneResponse phoneResponse = phoneService.execute(new AddClientPhoneRequest
                (addClientRequest.getClient().getPhoneNumber()));
        AddClientEmailResponse emailResponse = emailService.execute(new AddClientEmailRequest
                (addClientRequest.getClient().getEmail()));

        if (nameResponse.hasErrors()) {
            errors.add(new CoreError("Name input error: ", "Empty field"));
        }
        if (surnameResponse.hasErrors()) {
            errors.add(new CoreError("Surname input error: ", "Empty field"));
        }
        if (phoneResponse.hasErrors()) {
            errors.add(new CoreError("Phone number input error: ", "Phone number unsupported format"));
        }
        if (isPhoneNumberExist(addClientRequest)) {
            errors.add(new CoreError("Phone number input error: ", "Duplicate"));
        }
        if (emailResponse.hasErrors()) {
            errors.add(new CoreError("Email input error: ", "Email unsupported format"));
        }

        execute(errors, addClientRequest.getClient());

        return new AddClientResponse(errors);
    }

    @Override
    public void execute(List<CoreError> errors, Client client) {
        if (errors.isEmpty()) {
            int updateIndex = clientDatabase.findClientIndex(client.getId());
            clientDatabase.updateClient(updateIndex, client);
        }
    }

    private boolean isPhoneNumberExist(AddClientRequest request) {
        return clientDatabase.isClientPhoneNumber(request.getClient().getPhoneNumber());
    }
}