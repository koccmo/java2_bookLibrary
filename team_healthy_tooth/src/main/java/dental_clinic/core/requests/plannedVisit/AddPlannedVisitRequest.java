package dental_clinic.core.requests.plannedVisit;

import dental_clinic.core.domain.PersonalData;

public class AddPlannedVisitRequest {

    private String visitData;
    private PersonalData personalData;

    public AddPlannedVisitRequest(String visitData, PersonalData personalData) {
        this.visitData = visitData;
        this.personalData = personalData;
    }

    public String getVisitData() {
        return visitData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }
}
