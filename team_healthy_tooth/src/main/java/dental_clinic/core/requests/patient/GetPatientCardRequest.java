package dental_clinic.core.requests.patient;

public class GetPatientCardRequest {

    private Long id;

    public GetPatientCardRequest() { }

    public GetPatientCardRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
