package dental_clinic.core.requests.plannedVisit;

public class AddPlannedVisitRequest {

    private String visitDataText;
    private String personalCode;
    private Long doctorsId;

    public AddPlannedVisitRequest() { }

    public AddPlannedVisitRequest(String visitDataText, String personalCode, Long doctorsId) {
        this.visitDataText = visitDataText;
        this.personalCode = personalCode;
        this.doctorsId = doctorsId;
    }

    public String getVisitDataText() {
        return visitDataText;
    }

    public void setVisitDataText(String visitDataText) {
        this.visitDataText = visitDataText;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public Long getDoctorsId() {
        return doctorsId;
    }

    public void setDoctorsId(Long doctorsId) {
        this.doctorsId = doctorsId;
    }
}
