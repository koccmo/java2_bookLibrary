package lesson_3.database.client_database;

import lesson_3.core.domain.Client;

import java.util.ArrayList;
import java.util.List;

public class InnerClientDatabaseImpl implements InnerClientDatabase {
    private final List<Client> clients = new ArrayList<>();

    private Long id = 1L;

    @Override
    public void addClient(Client client) {
        client.setId(id);
        clients.add(client);
        id++;
    }

    @Override
    public void deleteClient(Client client) {
        clients.remove(client);
    }

    @Override
    public void updateClient(int index, Client client) {
        clients.set(index, client);
    }

    @Override
    public void showReport() {
        if (isClientListEmpty(clients)) {
            System.out.println("No records");
            return;
        }
        System.out.println("Products list:");
        clients.forEach(client -> System.out.println("ID: " + client.getId() + " " +
                "Name: " + client.getName() + " " + "Surname: " + client.getSurname() + " " +
                "Phone number: " + client.getPhoneNumber() + " " + "Email: " + client.getEmail()));
    }

    @Override
    public List<Client> getAllClients() {
        return clients;
    }

    private boolean isClientListEmpty(List<Client> products) {
        return products.size() == 0;
    }
}