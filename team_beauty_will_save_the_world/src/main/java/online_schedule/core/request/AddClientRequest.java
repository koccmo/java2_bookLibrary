package online_schedule.core.request;

import online_schedule.core.domain.PersonalData;
import online_schedule.core.domain.Visit;

import java.util.List;

public class AddClientRequest {

    private PersonalData personalData;
    private List<Visit> visits;

    public AddClientRequest(PersonalData personalData){
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }
}
