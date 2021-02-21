package dental_clinic.core.requests.patient;

import dental_clinic.core.domain.PersonalData;

public class AddPatientRequest {

    private PersonalData personalData;

    public AddPatientRequest() { }

    public AddPatientRequest(PersonalData personalData){
        this.personalData = personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public String getPersonalCode() {
        return personalData.getPersonalCode();
    }

    public void setPersonalCode(String personalCode) {
        personalData.setPersonalCode(personalCode.replace("-", ""));
    }

}
