package dental_clinic.core.requests.plannedVisit;

import dental_clinic.core.domain.PersonalData;

public class AddPlannedVisitRequest {

    private String visitDataText;
    private PersonalData personalData;

    public AddPlannedVisitRequest(String visitDataText, PersonalData personalData) {
        this.visitDataText = visitDataText;
        this.personalData = personalData;
    }

    public String getVisitDataText() {
        return visitDataText;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }
}
