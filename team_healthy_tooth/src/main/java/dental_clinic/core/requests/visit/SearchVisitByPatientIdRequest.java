package dental_clinic.core.requests.visit;

public class SearchVisitByPatientIdRequest {

    private Long id;

    public SearchVisitByPatientIdRequest() { }

    public SearchVisitByPatientIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
