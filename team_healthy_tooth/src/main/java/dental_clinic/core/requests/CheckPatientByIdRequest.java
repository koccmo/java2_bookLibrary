package dental_clinic.core.requests;


public class CheckPatientByIdRequest {

    private Long id;

    public CheckPatientByIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

}
