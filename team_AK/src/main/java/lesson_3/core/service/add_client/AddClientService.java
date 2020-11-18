package lesson_3.core.service.add_client;

import lesson_3.ProductListApplication;
import lesson_3.core.core_error.CoreError;
import lesson_3.core.domain.Client;
import lesson_3.core.request.add_client.AddClientRequest;
import lesson_3.core.request.add_client.client_items.AddClientEmailRequest;
import lesson_3.core.request.add_client.client_items.AddClientNameRequest;
import lesson_3.core.request.add_client.client_items.AddClientPhoneRequest;
import lesson_3.core.request.add_client.client_items.AddClientSurnameRequest;
import lesson_3.core.response.add_client.AddClientResponse;
import lesson_3.core.response.add_client.client_items.AddClientEmailResponse;
import lesson_3.core.response.add_client.client_items.AddClientNameResponse;
import lesson_3.core.response.add_client.client_items.AddClientPhoneResponse;
import lesson_3.core.response.add_client.client_items.AddClientSurnameResponse;
import lesson_3.core.service.add_client.client_items_service.AddClientEmailService;
import lesson_3.core.service.add_client.client_items_service.AddClientNameService;
import lesson_3.core.service.add_client.client_items_service.AddClientPhoneService;
import lesson_3.core.service.add_client.client_items_service.AddClientSurnameService;
import lesson_3.database.client_database.InnerClientDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddClientService implements ClientUpdate {
    private final InnerClientDatabase clientDatabase;

    public AddClientService(InnerClientDatabase clientDatabase) {
        this.clientDatabase = clientDatabase;
    }

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
            clientDatabase.addClient(client);
        }
    }

    private boolean isPhoneNumberExist(AddClientRequest request) {
        return ProductListApplication.findClientService.isClientPhoneNumber(clientDatabase.getAllClients(),
                request.getClient().getPhoneNumber());
    }
}