package dental_clinic.core.requests.plannedVisit;

import dental_clinic.core.domain.PersonalData;

public class AddPlannedVisitRequest {

    private boolean isNewPatient;
    private String visitDataText;
    private PersonalData personalData;
    private Long doctorsId;

    public AddPlannedVisitRequest(boolean isNewPatient, String visitDataText, PersonalData personalData, Long doctorsId) {
        this.isNewPatient = isNewPatient;
        this.visitDataText = visitDataText;
        this.personalData = personalData;
        this.doctorsId = doctorsId;
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

    public Long getId() {
        return doctorsId;
    }
}
