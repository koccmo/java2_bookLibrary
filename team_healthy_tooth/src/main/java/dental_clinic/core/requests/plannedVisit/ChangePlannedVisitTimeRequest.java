package dental_clinic.core.requests.plannedVisit;

public class ChangePlannedVisitTimeRequest {

    private Long id;
    private String visitTime;

    public ChangePlannedVisitTimeRequest() { }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
