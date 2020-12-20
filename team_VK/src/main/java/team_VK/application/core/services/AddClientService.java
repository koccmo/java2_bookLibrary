package team_VK.application.core.services;

import team_VK.application.core.domain.Client;
import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.AddClientResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DIComponent;
import team_VK.application.database.DatabaseClients;

import java.util.List;
@DIComponent
public class AddClientService {

    @DIDependency private DatabaseClients databaseClient;
    @DIDependency private AddClientServiceValidator validator;


    public AddClientResponse addClient(AddClientRequest request){

        List<CoreError> errors = validator.validate(request);

        if (errors.size() == 0) {
            Client client = new Client(request.getClientFirstName(), request.getClientLastName(), request.getClientPersonalCode());
            databaseClient.addClient(client);
        }
        return new AddClientResponse(errors);

    }
}
