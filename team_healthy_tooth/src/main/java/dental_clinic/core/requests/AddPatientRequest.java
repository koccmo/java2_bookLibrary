package dental_clinic.core.requests;

import dental_clinic.core.domain.Jowl;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.Visit;

import java.util.List;

public class AddPatientRequest {

    private PersonalData personalData;
    private Jowl jowl;
    private List<Visit> visits;

    public AddPatientRequest(PersonalData personalData){
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public Jowl getJowl() {
        return jowl;
    }

    public List<Visit> getVisits() {
        return visits;
    }
}
