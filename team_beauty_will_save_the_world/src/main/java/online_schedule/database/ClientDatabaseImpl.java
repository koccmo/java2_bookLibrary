package online_schedule.database;

import online_schedule.domain.Client;
import online_schedule.domain.PersonalData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientDatabaseImpl implements ClientDatabase{


    private Long id = 1L;
    private List<Client> clientList = new ArrayList<>();
    private List<ManicureMaster> manicureMasters = new ArrayList<>();



    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void addManicureMaster(PersonalData personalData) {
        personalData.getName();

    }

    @Override
    public void addHairdresser(PersonalData personalData) {

    }

    @Override
    public void addBrowMaster(PersonalData personalData) {

    }

    @Override
    public List<Client> getClients() {
        return null;
    }

    @Override
    public void addClient(PersonalData personalData) {
        personalData.setId(id);
        clientList.add(new Client(personalData));
        id++;


    }

    @Override
    public void deleteClient(String name, String surname, String mobileNumber, Long id) {
        for (int i = 0; i < clientList.size(); i++){
            if (clientList.get(i).getPersonalData().getId() == id){
                clientList.remove(i);
            }
        }

    }


    @Override
    public Optional<Client> getClientHistory(long id) {
        for (int i = 0; i < clientList.size(); i++){
            if (isSpecificClient(i, id)){
                return Optional.of(clientList.get(i));
            }
        }
        return Optional.empty();
    }

    private boolean isSpecificClient(int index, long id) {
        return clientList.get(index).getPersonalData().getId() == id;
    }
    @Override
    public List<Client> findClientByName(String name) {
        return clientList.stream().filter(client -> client.getPersonalData()
                .getName().toLowerCase().startsWith(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> findClientByMobileNumber(String mobileNumber) {
        return clientList.stream()
                .filter(client -> client.getPersonalData().getMobileNumber()
                        .equals(mobileNumber)).collect(Collectors.toList());
    }

    @Override
    public void addVisit(long id, String master, String name, String surname, String mobileNumber) {

    }
}
