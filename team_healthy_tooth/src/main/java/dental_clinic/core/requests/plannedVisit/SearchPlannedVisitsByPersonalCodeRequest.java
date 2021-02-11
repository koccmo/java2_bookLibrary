package dental_clinic.core.requests.plannedVisit;

public class SearchPlannedVisitsByPersonalCodeRequest {

    private String personalCode;

    public SearchPlannedVisitsByPersonalCodeRequest() { }

    public SearchPlannedVisitsByPersonalCodeRequest(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }
}
