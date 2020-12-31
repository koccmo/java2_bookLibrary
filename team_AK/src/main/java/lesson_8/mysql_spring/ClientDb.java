package lesson_8.mysql_spring;

import lesson_8.mysql_spring.entity.Client;
import lesson_8.mysql_spring.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientDb {
    @Autowired
    private ClientRepository clientRepository;

    public void insertRecord(Client client) {
        clientRepository.save(client);
    }

    public void updateRecord(Long id, Client client) {
        Client clientDb = clientRepository.findById(id).orElse(null);
        assert clientDb != null;
        clientDb.setName(client.getName());
        clientDb.setSurname(client.getSurname());
        clientDb.setPhoneNumber(client.getPhoneNumber());
        clientDb.setEmail(client.getEmail());
        clientRepository.save(clientDb);
    }

    public Client findByName(String name) {
        return clientRepository.findByName(name);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void deleteRecord(Long id) {
        clientRepository.deleteById(id);
    }

    public boolean isRecordExist(Long id) {
        return clientRepository.existsById(id);
    }

    public List<Client> findAllRecords() {
        return clientRepository.findAll();
    }
}