package dental_clinic.core.requests.plannedVisit;

public class CancelPlannedVisitRequest {

    private Long id;

    public CancelPlannedVisitRequest() { }

    public CancelPlannedVisitRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
