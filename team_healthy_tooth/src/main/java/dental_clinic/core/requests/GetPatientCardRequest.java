package dental_clinic.core.requests;

public class GetPatientCardRequest {

    private Long id;

    public GetPatientCardRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
