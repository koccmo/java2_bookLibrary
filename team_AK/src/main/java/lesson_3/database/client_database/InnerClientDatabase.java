package lesson_3.database.client_database;

import lesson_3.core.domain.Client;

import java.util.List;

public interface InnerClientDatabase {
    void addClient(Client client);

    void deleteClient(Client client);

    void updateClient(int index, Client client);

    void showReport();

    List<Client> getAllClients();
}
