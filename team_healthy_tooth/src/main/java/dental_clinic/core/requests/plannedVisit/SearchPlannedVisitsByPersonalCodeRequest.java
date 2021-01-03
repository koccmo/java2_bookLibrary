package dental_clinic.core.requests.plannedVisit;

public class SearchPlannedVisitsByPersonalCodeRequest {

    private String personalCode;

    public SearchPlannedVisitsByPersonalCodeRequest(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getPersonalCode() {
        return personalCode;
    }
}
