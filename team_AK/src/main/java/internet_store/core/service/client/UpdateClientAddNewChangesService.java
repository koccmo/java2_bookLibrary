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
import internet_store.database.client_database.ClientDatabaseImpl;
import internet_store.database.interfaces.ClientDatabase;
import internet_store.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateClientAddNewChangesService implements ClientUpdate {
    @Autowired
    ClientDatabase clientDatabase;

    public AddClientResponse execute(AddClientRequest request) {
        List<CoreError> errors = new ArrayList<>();

        AddClientNameService nameService = new AddClientNameService();
        AddClientSurnameService surnameService = new AddClientSurnameService();
        AddClientPhoneService phoneService = new AddClientPhoneService();
        AddClientEmailService emailService = new AddClientEmailService();

        AddClientNameResponse nameResponse = nameService.execute(new AddClientNameRequest
                (request.getClient().getName()));
        AddClientSurnameResponse surnameResponse = surnameService.execute(new AddClientSurnameRequest
                (request.getClient().getSurname()));
        AddClientPhoneResponse phoneResponse = phoneService.execute(new AddClientPhoneRequest
                (request.getClient().getPhoneNumber()));
        AddClientEmailResponse emailResponse = emailService.execute(new AddClientEmailRequest
                (request.getClient().getEmail()));

        if (nameResponse.hasErrors()) {
            errors.add(new CoreError("Name input error: ", "Empty field"));
        }
        if (surnameResponse.hasErrors()) {
            errors.add(new CoreError("Surname input error: ", "Empty field"));
        }
        if (phoneResponse.hasErrors()) {
            errors.add(new CoreError("Phone number input error: ", "Phone number unsupported format"));
        }
        if (isPhoneNumberExist(request)) {
            errors.add(new CoreError("Phone number input error: ", "Duplicate"));
        }
        if (emailResponse.hasErrors()) {
            errors.add(new CoreError("Email input error: ", "Email unsupported format"));
        }

        execute(errors, request);

        return new AddClientResponse(errors);
    }

    @Override
    public void execute(List<CoreError> errors, AddClientRequest request) {
        Client client = request.getClient();
        Object databases = request.getClientDatabase();
        ClientDatabaseImpl innerDatabase;
        ClientRepository clientRepository;

        if (databases instanceof ClientDatabaseImpl) {
            innerDatabase = (ClientDatabaseImpl) databases;
            if (errors.isEmpty()) {
                int updateIndex = innerDatabase.findClientIndex(client.getId());
                innerDatabase.updateClient(updateIndex, client);
            }
        }

        if (databases instanceof ClientRepository) {
            clientRepository = (ClientRepository) databases;
            if (errors.isEmpty()) {
                clientRepository.save(client);
            }
        }
    }

    private boolean isPhoneNumberExist(AddClientRequest request) {
        return clientDatabase.isClientPhoneNumber(request.getClient().getPhoneNumber());
    }
}