package team_VK.application.database;

import team_VK.application.core.domain.Client;

import java.util.ArrayList;
import java.util.List;
@DIComponent
public class DatabaseClientsInMemory implements DatabaseClients {

    private long idClientsCounter = 1L;
    List<Client> clients = new ArrayList<>();

    @Override
    public void addClient(Client client) {
        client.setClientID(idClientsCounter);
        idClientsCounter++;
        clients.add(client);
    }

    @Override
    public void deleteClient(Client client) {

    }

    @Override
    public List<Client> getListClients() {
        return clients;
    }
}
