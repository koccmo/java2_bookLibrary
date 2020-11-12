package team_VK.application.database.database_Clients;

import team_VK.application.Book;
import team_VK.application.Client;

import java.util.List;

public interface DatabaseClients {

    void addClient(Client client);
    void deleteClient(Client client);
    List<Client> getListClients();

}
