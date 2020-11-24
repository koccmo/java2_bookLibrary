package online_schedule.request;

import online_schedule.domain.PersonalData;
import online_schedule.domain.Visit;

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
