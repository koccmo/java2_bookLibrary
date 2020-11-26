package online_schedule.core.service;

import online_schedule.database.ClientDatabase;
import online_schedule.core.domain.PersonalData;

import java.util.Optional;
import java.util.function.Predicate;

public class AddClientService {

    private ClientDatabase clientDatabase;

    public AddClientService(ClientDatabase clientDatabase){
        this.clientDatabase = clientDatabase;
    }

    public boolean addClient(PersonalData personalData){
        if(containsDatabaseClientPersonalData(personalData)){
            return false;

        }else {
            clientDatabase.addClient(personalData);
            return true;
        }
    }
    private boolean containsDatabaseClientPersonalData(PersonalData personalData){
        Optional<PersonalData> result = clientDatabase.getClients().stream().map(client -> client.getPersonalData())
                .filter(Predicate.isEqual(clientDatabase)).findAny();
        return result.isPresent();

    }
}
