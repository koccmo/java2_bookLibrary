package dental_clinic.core.requests.plannedVisit;

public class ChangePlannedVisitTimeRequest {

    private Long id;
    private String visitTime;

    public ChangePlannedVisitTimeRequest(Long id, String visitTime) {
        this.id = id;
        this.visitTime = visitTime;
    }

    public Long getId() {
        return id;
    }

    public String getVisitTime() {
        return visitTime;
    }
}
