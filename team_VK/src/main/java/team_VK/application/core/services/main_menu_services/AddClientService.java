package team_VK.application.core.services.main_menu_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.domain.Client;
import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.AddClientResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.validators.AddClientServiceValidator;
import team_VK.application.database.DatabaseClients;

import java.util.List;
@Component
public class AddClientService {

    @Autowired
    private DatabaseClients databaseClient;
    @Autowired private AddClientServiceValidator validator;


    public AddClientResponse addClient(AddClientRequest request){

        List<CoreError> errors = validator.validate(request);

        if (errors.size() == 0) {
            Client client = new Client(request.getClientFirstName(), request.getClientLastName(), request.getClientPersonalCode());
            databaseClient.addClient(client);
        }
        return new AddClientResponse(errors);

    }
}
