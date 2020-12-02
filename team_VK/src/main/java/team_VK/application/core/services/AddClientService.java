package team_VK.application.core.services;

import team_VK.application.core.domain.Client;
import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.AddClientResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DatabaseClients;

import java.util.List;

public class AddClientService {

    private final DatabaseClients databaseClient;
    private AddClientServiceValidator validator;



    public AddClientService(DatabaseClients databaseClient, AddClientServiceValidator validator) {
        this.databaseClient = databaseClient;this.validator = validator;
    }

    public AddClientResponse addClient(AddClientRequest request){

        List<CoreError> errors = validator.validate(request);

        if (errors.size() == 0) {
            Client client = new Client(request.getClientFirstName(), request.getClientLastName(), request.getClientPersonalCode());
            databaseClient.addClient(client);
        }
        return new AddClientResponse(errors);

    }
}
