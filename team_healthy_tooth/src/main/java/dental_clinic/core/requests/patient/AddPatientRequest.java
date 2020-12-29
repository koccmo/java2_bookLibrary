package dental_clinic.core.requests.patient;

import dental_clinic.core.domain.PersonalData;

public class AddPatientRequest {

    private PersonalData personalData;

    public AddPatientRequest(PersonalData personalData){
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

}
