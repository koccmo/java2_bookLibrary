package team_VK.application.core.services.client_services;

import team_VK.application.core.domain.Client;
import team_VK.application.database.database_Clients.DatabaseClients;

public class AddClientService {

    private final DatabaseClients databaseClient;

    public AddClientService(DatabaseClients databaseClient) {
        this.databaseClient = databaseClient;
    }

    public void addClient(String clientName){
        Client client = new Client( clientName);
        databaseClient.addClient(client);

    }
}
