package dental_clinic.core.requests.manipulation;

public class DeactivateManipulationRequest {

    private Long id;

    public DeactivateManipulationRequest() { }

    public DeactivateManipulationRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
