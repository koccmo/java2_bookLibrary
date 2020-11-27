package internet_store.database.client_database;

import internet_store.core.domain.Client;

public interface InnerClientDatabase {
    void addClient(Client client);

    void deleteClient(Client client);

    void updateClient(int index, Client client);

    void showReport();

    void showReport(Client client);

    boolean isIdExist(long id);

    Client findById(long id);

    int findClientIndex(long id);

    boolean isClientPhoneNumber(String phoneNumber);

    boolean isEmpty();
}