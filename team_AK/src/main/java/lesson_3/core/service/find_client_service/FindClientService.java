package lesson_3.core.service.find_client_service;

import lesson_3.core.domain.Client;

import java.util.List;
import java.util.stream.IntStream;

public class FindClientService {
    private final Client EMPTY_OBJECT = null;

    public boolean isIdExist(List<Client> clients, long id) {
        return clients.stream().anyMatch(pr -> pr.getId() == id);
    }

    public Client findById(List<Client> clients, long id) {
        return clients.stream().filter(pr -> pr.getId() == id).findFirst().orElse(EMPTY_OBJECT);
    }

    public int findClientIndex(List<Client> clients, long id) {
        int LIST_FIRST_ELEMENT = 0;
        int NO_ID_FIND = -1;
        return IntStream.range(LIST_FIRST_ELEMENT, clients.size())
                .filter(i -> clients.get(i).getId() == id)
                .findFirst().orElse(NO_ID_FIND);
    }

    public boolean isClientPhoneNumber(List<Client> clients, String phoneNumber) {
        return clients.stream().anyMatch(i -> i.getPhoneNumber().equals(phoneNumber));
    }
}
