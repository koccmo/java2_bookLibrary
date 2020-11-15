package online_schedule.database;

import online_schedule.domain.PersonalData;

public class ManicureMaster {

    private PersonalData personalData;

    public ManicureMaster(PersonalData personalData){
        this.personalData = personalData;

    }

    public PersonalData getPersonalData() {
        return personalData;
    }
}
