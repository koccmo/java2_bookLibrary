package dental_clinic.core.requests.plannedVisit;

import dental_clinic.core.domain.PersonalData;

public class AddPlannedVisitRequest {

    private boolean isNewPatient;
    private String visitDataText;
    private PersonalData personalData;

    public AddPlannedVisitRequest(boolean isNewPatient, String visitDataText, PersonalData personalData) {
        this.isNewPatient = isNewPatient;
        this.visitDataText = visitDataText;
        this.personalData = personalData;
    }

    public boolean getIsNewPatient() {
        return isNewPatient;
    }

    public String getVisitDataText() {
        return visitDataText;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }
}
