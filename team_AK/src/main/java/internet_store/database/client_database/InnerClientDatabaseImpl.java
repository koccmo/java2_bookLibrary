package internet_store.database.client_database;

import internet_store.core.domain.Client;
import dependency.annotation.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@DIComponent
public class InnerClientDatabaseImpl implements InnerClientDatabase {
    private final List<Client> clients = new ArrayList<>();
    private final Client EMPTY_OBJECT = null;

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
        clients.forEach(client -> System.out.println("ID: " + client.getId() + " " +
                "Name: " + client.getName() + " " + "Surname: " + client.getSurname() + " " +
                "Phone number: " + client.getPhoneNumber() + " " + "Email: " + client.getEmail()));
    }

    @Override
    public void showReport(Client client) {
        System.out.println("ID: " + client.getId() + " " +
                "Name: " + client.getName() + " " + "Surname: " + client.getSurname() + " " +
                "Phone number: " + client.getPhoneNumber() + " " + "Email: " + client.getEmail());
    }

    @Override
    public boolean isIdExist(long id) {
        return clients.stream().anyMatch(pr -> pr.getId() == id);
    }

    @Override
    public Client findById(long id) {
        return clients.stream().filter(pr -> pr.getId() == id).findFirst().orElse(EMPTY_OBJECT);
    }

    @Override
    public int findClientIndex(long id) {
        int LIST_FIRST_ELEMENT = 0;
        int NO_ID_FIND = -1;
        return IntStream.range(LIST_FIRST_ELEMENT, clients.size())
                .filter(i -> clients.get(i).getId() == id)
                .findFirst().orElse(NO_ID_FIND);
    }

    @Override
    public boolean isClientPhoneNumber(String phoneNumber) {
        return clients.stream().anyMatch(i -> i.getPhoneNumber().equals(phoneNumber));
    }

    @Override
    public boolean isEmpty() {
        return clients.size() == 0;
    }
}