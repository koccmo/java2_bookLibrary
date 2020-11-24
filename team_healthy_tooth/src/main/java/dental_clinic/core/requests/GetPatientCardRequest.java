package dental_clinic.core.requests;

public class GetPatientCardRequest {

    private long id;

    public GetPatientCardRequest(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
