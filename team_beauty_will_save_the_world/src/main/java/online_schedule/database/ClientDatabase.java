package online_schedule.database;


import online_schedule.core.domain.Client;
import online_schedule.core.domain.PersonalData;
import online_schedule.core.domain.Visit;

import java.util.List;
import java.util.Optional;

public interface ClientDatabase {


    List<Client> getClients();

     void addClient(PersonalData personalData);

     void deleteClient(String name, String surname, String mobileNumber, Long id);

     void addManicureMaster(PersonalData personalData);

     void addHairdresser(PersonalData personalData);

      void  addBrowMaster( PersonalData personalData);

    Optional<Client> getClientHistory(long id);

    List<Client> findClientByMobileNumber(String mobileNumber);

    List<Client> findClientByName(String name);

    void addVisit(long id, String master, String name, String surname, String mobileNumber);


    void addVisit(Visit visit);
}
