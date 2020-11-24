package online_schedule.database;


import online_schedule.core.domain.Client;
import online_schedule.core.domain.PersonalData;

import java.util.List;
import java.util.Optional;

public interface ClientDatabase {


    List<Client> getClients();

    public void addClient(PersonalData personalData);

    public void deleteClient(String name, String surname, String mobileNumber, Long id);

    public void addManicureMaster(PersonalData personalData);

    public void addHairdresser(PersonalData personalData);

    public  void  addBrowMaster( PersonalData personalData);

    Optional<Client> getClientHistory(long id);

    List<Client> findClientByMobileNumber(String mobileNumber);

    List<Client> findClientByName(String name);

    void addVisit(long id, String master, String name, String surname, String mobileNumber);





}
