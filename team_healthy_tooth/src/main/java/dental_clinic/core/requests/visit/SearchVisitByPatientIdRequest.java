package dental_clinic.core.requests.visit;

public class SearchVisitByPatientIdRequest {

    private Long id;

    public SearchVisitByPatientIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
